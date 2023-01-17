FROM python:3.9.16-slim-bullseye
# Install util packages that will be used later
RUN apt-get update
RUN apt-get install -y curl wget jq zip unzip
RUN wget -qO /usr/local/bin/yq https://github.com/mikefarah/yq/releases/latest/download/yq_linux_arm64
RUN chmod a+x /usr/local/bin/yq
RUN pip install bcrypt
# Install kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/arm64/kubectl"
RUN install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
# Install aws cli
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-aarch64.zip" -o "awscliv2.zip"
RUN unzip awscliv2.zip && ./aws/install
RUN rm awscliv2.zip
# Install eksctl
RUN curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_arm64.tar.gz" | tar xz -C /tmp
RUN mv /tmp/eksctl /usr/local/bin
# Set default shell that will be used in the pipeline
SHELL ["/bin/bash", "-c"]