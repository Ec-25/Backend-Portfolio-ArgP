FROM amazoncorretto:8-al2-jdk
MAINTAINER EstefanoChicco
COPY target/ec-1.2.jar ec-app-portfolio.jar
ENTRYPOINT [ "java","-jar","/ec-app-portfolio.jar" ]
