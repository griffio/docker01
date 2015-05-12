# docker01

Use gradle plugin "com.bmuschko.docker" to package and deploy to local docker repository.

It requires the docker remote api (tcp) to be enabled via http (127.0.0.1).

## setup

For Linux

Docker service setup 
~~~
sudo systemctl enable docker
~~~
Add current user to docker group to avoid use of sudo
~~~
sudo gpasswd -a griffio docker
~~~
Restart the system, check docker commands run without sudo
~~~
docker images
~~~

## docker java chat

### Java base images

https://registry.hub.docker.com/u/azul/zulu-openjdk/

Docker File
~~~
FROM azul/zulu-openjdk:7
MAINTAINER griffio "griffio@users.noreply.github.com"
ADD docker01-0.1.0.tar /
ENTRYPOINT ["/docker01-0.1.0/bin/docker01"]
EXPOSE 8080
~~~

Application image tagged
~~~
docker build -t="griffio/docker01" build/docker
~~~

Docker run command - localhost:8080 on actual host will expose the docker run port 8080
~~~
docker run --publish="8080:8080" --hostname="docker01" -it griffio/docker01 docker01 8080
~~~

Docker ps command in another terminal
~~~
docker ps
~~~

Create file /etc/systemd/system/docker-tcp.socket 
~~~
[Unit]
Description=Docker Socket for the API

[Socket]
ListenStream=2375
BindIPv60Only=both
Service=docker.service

[Install]
WantedBy=sockets.target
~~~

commands
~~~
systemctl enable docker-tcp.socket
sudo netstat -tunlp
~~~

Restart system

