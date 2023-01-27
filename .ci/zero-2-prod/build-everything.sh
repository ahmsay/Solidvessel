#!/bin/bash
# create cluster
perl -i -pe"s/<aws_account_id>/$AWS_ACCOUNT_ID/" .ci/zero-2-prod/cluster.yaml
eksctl create cluster --config-file=.ci/zero-2-prod/cluster.yaml
eksctl create iamidentitymapping --cluster solidvessel --region $AWS_REGION --arn arn:aws:iam::"$AWS_ACCOUNT_ID":user/"$AWS_USER" --group system:masters --no-duplicate-arns --username admin-user
aws eks update-kubeconfig --region $AWS_REGION --name solidvessel

# install argocd
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
NEW_ARGO_CD_PASSWORD=$(python3 -c "import bcrypt; print(bcrypt.hashpw(b'$ARGO_CD_PASSWORD', bcrypt.gensalt()).decode())")
kubectl -n argocd patch secret argocd-secret -p '{"stringData": {"admin.password": "'$(echo $NEW_ARGO_CD_PASSWORD)'","admin.passwordMtime": "'$(date +%FT%T%Z)'"}}'
kubectl -n argocd patch deploy argocd-server --type=json -p='[{ "op": "add", "path": "/spec/template/spec/containers/0/command/-", "value": "--insecure" }]'
sleep 10

# create applications
kubectl create -f .kubernetes/argocd/root/Root.yaml

# create route53 records
load_balancer_arn=None
until [[ $load_balancer_arn != 'None' ]]; do echo 'Waiting for load balancer to be generated...'; sleep 10; export load_balancer_arn=$(aws resourcegroupstaggingapi get-resources --tag-filters Key=ingress.k8s.aws/resource,Values=LoadBalancer Key=ingress.k8s.aws/stack,Values=common-ingress-group --tags-per-page 100 --query "ResourceTagMappingList[0].ResourceARN" --output text); done
LOAD_BALANCER_DNS_NAME=dualstack.$(aws elbv2 describe-load-balancers --load-balancer-arns $load_balancer_arn --query "LoadBalancers[0].DNSName" --output text)
LOAD_BALANCER_HOSTED_ZONE_ID=$(aws elbv2 describe-load-balancers --load-balancer-arns $load_balancer_arn --query "LoadBalancers[0].CanonicalHostedZoneId" --output text)
perl -i -pe"s/<load-balancer-dns-name>/$LOAD_BALANCER_DNS_NAME/g" .ci/zero-2-prod/dns-records.json
perl -i -pe"s/<load-balancer-hosted-zone-id>/$LOAD_BALANCER_HOSTED_ZONE_ID/g" .ci/zero-2-prod/dns-records.json
route_53_dns_hosted_zone_id=$(aws route53 list-hosted-zones --query "HostedZones[?(@.Name=='solidvessel.com.')].Id" --output text | tr -d '/hostedzone/')
aws route53 change-resource-record-sets --hosted-zone-id $route_53_dns_hosted_zone_id --change-batch file://.ci/zero-2-prod/dns-records.json
