FROM adoptopenjdk/openjdk11
COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar /build/libs/demo-0.0.1-SNAPSHOT.jar
#ENV    PROFILE dev
#CMD ["java","-jar","/build/libs/demo-0.0.1-SNAPSHOT.jar","--spring.profiles.active=dev"]
CMD ["java","-jar","-Dspring.profiles.active=dev","/build/libs/demo-0.0.1-SNAPSHOT.jar"]