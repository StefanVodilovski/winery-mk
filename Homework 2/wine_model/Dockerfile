FROM openjdk:18-oracle
COPY target/*.jar winemodel.jar
EXPOSE 7070

ENTRYPOINT ["java", "-jar", "winemodel.jar"]