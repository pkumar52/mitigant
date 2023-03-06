FROM openjdk:8
VOLUME /servicetmp
ADD target/itemservice-v1.0.jar itemservice-v1.0.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","itemservice-v1.0.jar"]