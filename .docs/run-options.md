# Run Options

## Development

This mode will start the databases, Redis and RabbitMQ as containers in Docker. You can start the microservices with your
IDE.

```shell
docker-compose -f .docker/docker-compose.yaml up -d
```

## Test

This mode will start the whole application in K8S cluster. You can test it on your own computer if you enable Kubernetes
on Docker Desktop.

```shell
kubectl create ns solidvessel
kubectl apply -k ./.kubernetes/base
```

## Production
This mode will start the entire application on AWS. Everything will be created from the scratch (including servers).
Follow these steps to run this application:
1. Create an AWS account.
2. Run this script to create an IAM policy to use the ALB Controller.
```shell
curl -o iam_policy.json https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/v2.5.4/docs/install/iam_policy.json
aws iam create-policy --policy-name AWSLoadBalancerControllerIAMPolicy --policy-document file://iam_policy.json
```
3. Create a CodeBuild project containing the pipeline at <a href="../.ci/zero-2-prod/buildspec.yaml">.ci/zero-2-prod/buildspec.yaml<a/>. You can set the environment variables according
to your own needs.
4. Use your own domain name to create DNS records in dns-records.json file. You also need to update hostnames in ingress 
resource files in .kubernetes folder as well.
5. Make sure you gave necessary permissions to role you use to run the pipeline. The pipeline uses CloudFormaiton, EKS, EC2, EBS, VPC, IAM,
Route 53 services. This may seem a lot, but if you encounter an error, AWS will tell you about which permission you are missing.
6. Run the pipeline. The environment will be ready in approximately 25 minutes.

## NOTE

- The steps above are need to be done **only once** (except the last step, obviously). After that, you can create the whole environment with a single click.
- To delete the environment, you need to delete the related Cloudformation stacks, or use ``eksctl delete cluster`` command.