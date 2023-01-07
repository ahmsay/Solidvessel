aws_account_id=
argo_cd_password=adminadmin

curl https://raw.githubusercontent.com/ahmsay/AWS-Experiments/main/EKS/cluster.yaml | yq '.metadata.name = "microshop"' > cluster.yaml
eksctl create cluster --config-file=cluster.yaml
rm cluster.yaml

kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
new_argo_cd_password=$(python3 -c "import bcrypt; print(bcrypt.hashpw(b'$argo_cd_password', bcrypt.gensalt()).decode())")
kubectl -n argocd patch secret argocd-secret -p '{"stringData": {"admin.password": "'$(echo $new_argo_cd_password)'","admin.passwordMtime": "'$(date +%FT%T%Z)'"}}'
kubectl delete secret argocd-initial-admin-secret -n argocd

eksctl create iamserviceaccount --cluster=microshop --namespace=kube-system --name=aws-load-balancer-controller --role-name "AmazonEKSLoadBalancerControllerRole" --attach-policy-arn=arn:aws:iam::$aws_account_id:policy/AWSLoadBalancerControllerIAMPolicy --approve
kubectl apply -k "github.com/aws/eks-charts/stable/aws-load-balancer-controller/crds?ref=master"

kubectl create -f .kubernetes/argocd/root/Root.yaml

# fetch aws account id
# add --insecure parameter to argocd yaml
# configure route 53 records
# configure ssl and remove --insecure from argocd