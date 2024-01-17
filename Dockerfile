FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
COPY /out/artifacts/test_issue_jar/test_issue.jar test_issue.jar
COPY src/main/resources/tickets.json tickets.json