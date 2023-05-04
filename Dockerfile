FROM adoptopenjdk/openjdk11
COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar /build/libs/demo-0.0.1-SNAPSHOT.jar
ENV    PROFILE dev
CMD ["java","-Dspring.profiles.active=${PROFILE}","-jar","/build/libs/demo-0.0.1-SNAPSHOT.jar"]