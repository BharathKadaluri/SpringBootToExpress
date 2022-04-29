FROM maven:3.8-openjdk-8
#COPY . .
#RUN mvn clean package
COPY target/expresssample-0.0.1-SNAPSHOT.jar expresssample-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","./expresssample-0.0.1-SNAPSHOT.jar"]