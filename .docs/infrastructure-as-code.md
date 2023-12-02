# Infrastructure as Code (IaC)

This approach relies on managing your environment using a declarative approach. You use configuration files
instead of shell scripts to keep track your environment easier. 

In this project AWS Cloudformation uses this approach. The cluster configuration file is converted to AWS Cloudformation templates
(YAML written configuration files) and AWS create servers, network, load balancers and other resources by using those templates.

The production environment has many components and it must be immune to human made errors like accidental deletions.
If you delete a resource, such as a load balancer or a server, or even the cluster itself, Cloudformation will automatically
recreate that resource to make sure your environment stays same with the configuration you provided.
