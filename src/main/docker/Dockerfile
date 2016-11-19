FROM java:8
VOLUME /tmp
COPY rabbitmq-test-0.0.1-SNAPSHOT.jar api.jar
RUN sh -c 'touch /api.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar", "--spring.config.location=file:/conf/application.yml", "--logging.config=/conf/logback.xml"]
