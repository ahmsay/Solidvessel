# create cluster
curl https://raw.githubusercontent.com/ahmsay/AWS-Experiments/main/EKS/cluster.yaml | yq '.metadata.name = "microshop"' > cluster.yaml
eksctl create cluster --config-file=cluster.yaml

# install argocd
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
argo_cd_password=adminadmin
new_argo_cd_password=$(python3 -c "import bcrypt; print(bcrypt.hashpw(b'$argo_cd_password', bcrypt.gensalt()).decode())")
kubectl -n argocd patch secret argocd-secret -p '{"stringData": {"admin.password": "'$(echo $new_argo_cd_password)'","admin.passwordMtime": "'$(date +%FT%T%Z)'"}}'
kubectl delete secret argocd-initial-admin-secret -n argocd
kubectl -n argocd patch deploy argocd-server --type=json -p='[{ "op": "add", "path": "/spec/template/spec/containers/0/command/-", "value": "--insecure" }]'

# install serviceaccount for alb controller
aws_account_id=$(aws sts get-caller-identity --query "Account" --output text)
eksctl create iamserviceaccount --cluster=microshop --namespace=kube-system --name=aws-load-balancer-controller --role-name "AmazonEKSLoadBalancerControllerRole" --attach-policy-arn=arn:aws:iam::$aws_account_id:policy/AWSLoadBalancerControllerIAMPolicy --approve
kubectl apply -k "github.com/aws/eks-charts/stable/aws-load-balancer-controller/crds?ref=master"

# create applications
kubectl create -f ../../argocd/root/Root.yaml

# create route53 records
route_53_dns_name=ahmetsay.com
load_balancer_arn=$(aws resourcegroupstaggingapi get-resources --tag-filters Key=ingress.k8s.aws/resource,Values=LoadBalancer Key=ingress.k8s.aws/stack,Values=common-ingress-group --tags-per-page 100 --query "ResourceTagMappingList[0].ResourceARN" --output text)
load_balancer_dns_name=dualstack.$(aws elbv2 describe-load-balancers --load-balancer-arns $load_balancer_arn --query "LoadBalancers[0].DNSName" --output text)
load_balancer_hosted_zone_id=$(aws elbv2 describe-load-balancers --load-balancer-arns $load_balancer_arn --query "LoadBalancers[0].CanonicalHostedZoneId" --output text)
auth_dns_record=$(echo '{"Action":"UPSERT","ResourceRecordSet":{"Name":"auth.'$(echo $route_53_dns_name)'.","Type":"A","AliasTarget":{"DNSName":"'$(echo $load_balancer_dns_name)'","EvaluateTargetHealth":false,"HostedZoneId":"'$(echo $load_balancer_hosted_zone_id)'"}}}')
account_dns_record=$(echo '{"Action":"UPSERT","ResourceRecordSet":{"Name":"account.'$(echo $route_53_dns_name)'.","Type":"A","AliasTarget":{"DNSName":"'$(echo $load_balancer_dns_name)'","EvaluateTargetHealth":false,"HostedZoneId":"'$(echo $load_balancer_hosted_zone_id)'"}}}')
inventory_dns_record=$(echo '{"Action":"UPSERT","ResourceRecordSet":{"Name":"inventory.'$(echo $route_53_dns_name)'.","Type":"A","AliasTarget":{"DNSName":"'$(echo $load_balancer_dns_name)'","EvaluateTargetHealth":false,"HostedZoneId":"'$(echo $load_balancer_hosted_zone_id)'"}}}')
order_dns_record=$(echo '{"Action":"UPSERT","ResourceRecordSet":{"Name":"order.'$(echo $route_53_dns_name)'.","Type":"A","AliasTarget":{"DNSName":"'$(echo $load_balancer_dns_name)'","EvaluateTargetHealth":false,"HostedZoneId":"'$(echo $load_balancer_hosted_zone_id)'"}}}')
payment_dns_record=$(echo '{"Action":"UPSERT","ResourceRecordSet":{"Name":"payment.'$(echo $route_53_dns_name)'.","Type":"A","AliasTarget":{"DNSName":"'$(echo $load_balancer_dns_name)'","EvaluateTargetHealth":false,"HostedZoneId":"'$(echo $load_balancer_hosted_zone_id)'"}}}')
argocd_dns_record=$(echo '{"Action":"UPSERT","ResourceRecordSet":{"Name":"argocd.'$(echo $route_53_dns_name)'.","Type":"A","AliasTarget":{"DNSName":"'$(echo $load_balancer_dns_name)'","EvaluateTargetHealth":false,"HostedZoneId":"'$(echo $load_balancer_hosted_zone_id)'"}}}')
echo '{"Comment":"Create hosted zones","Changes":[]}' \
| jq ".Changes += ["$auth_dns_record"]" \
| jq ".Changes += ["$account_dns_record"]" \
| jq ".Changes += ["$inventory_dns_record"]" \
| jq ".Changes += ["$order_dns_record"]" \
| jq ".Changes += ["$payment_dns_record"]" \
| jq ".Changes += ["$argocd_dns_record"]" \
> dns-records.json
route_53_dns_hosted_zone_id=$(aws route53 list-hosted-zones --query "HostedZones[?(@.Name=='$(echo $route_53_dns_name).')].Id" --output text | tr -d '/hostedzone/')
aws route53 change-resource-record-sets --hosted-zone-id $route_53_dns_hosted_zone_id --change-batch file://dns-records.json

# configure ssl and remove --insecure from argocd