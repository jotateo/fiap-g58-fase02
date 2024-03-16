## Steps de criação dos serviços no Kubernetes Local

### Usando Docker images do Dockerhub
- Crie um **config.json** com **docker login**. https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/#log-in-to-docker-hub
  e insira nesse json um token de leitura do Dockerhub
- Crie um secret do Kubernetes com o config.json (usando o comando em create_secret.txt). 
- Crie as imagens do **pedidos-cadastro-api**, **pedidos-pagamento-api** e **pedidos-webhook-api**: docker build -t {tag} .
- Adicione tag nas imagens: docker tag image USER/repository:tag-imagem e push pro dockerhub

### Usando o Docker local
  - Inicie o minikube (minikube start)
  - use **eval $(minikube docker-env)** ou **minikube docker-env** && **minikube -p minikube docker-env --shell powershell | Invoke-Expression** no Windows
  - Build a imagem usando o terminal onde o comando anterior foi executado (O build será feito pelo Docker Daemon do minikube)
  - Set a **imagePullPolicy** no deployment para **Never**

### Manifestos do Kubernetes
- Substituia o caminho das imagens e **imagePullPolicy** nos deployments (Com registry remoto ou local)
- **minikube start**
- **kubectl apply -k {folder_com_manifestos}**
- Para acessar as aplicações:
  - Encontre os serviços de cada app com load balancer: **kubectl get svc**
  - Abra uma conexão pelo minikube: **minikube service {service_name} --url**
- **kubectl delete -k {folder_com_manifestos}** para deletar os resources