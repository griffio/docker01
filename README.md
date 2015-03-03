# docker01
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
sudo docker build -t="griffio/docker01" build/docker
~~~

Docker run command - localhost:8080 on actual host will expose the docker run port 8080
~~~
 sudo docker run --publish="8080:8080" --hostname="docker01" -it --rm griffio/docker01 docker01 8080
~~~
