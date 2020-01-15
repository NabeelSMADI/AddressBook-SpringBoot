FROM java:8
WORKDIR /
ADD target/AddressBook-0.0.1-SNAPSHOT.jar //
EXPOSE 8085
ENTRYPOINT [ "java", "-Dspring.profiles.active=mysql", "-jar", "/AddressBook-0.0.1-SNAPSHOT.jar"]




