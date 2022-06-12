FROM maven:3.8.1-openjdk-17-slim AS maven-build
WORKDIR /tmp/
COPY ./pom.xml ./
COPY ./booking-service/pom.xml ./booking-service/pom.xml
COPY ./movie-theater-service/pom.xml ./movie-theater-service/pom.xml
RUN mvn verify --fail-never

COPY ./booking-service ./booking-service
COPY ./movie-theater-service ./movie-theater-service
RUN mvn package

FROM openjdk:17-alpine
ARG BOOKING_SERVER_APP_JAR=booking-service-server.jar
ARG BOOKING_CLIENT_JAR=booking-service-client.jar
ARG MT_APP_JAR=movie-theater-service.jar
WORKDIR /opt/
COPY --from=maven-build /tmp/booking-service/booking-client/target/${BOOKING_CLIENT_JAR} ./booking-service/
COPY --from=maven-build /tmp/booking-service/booking-server/target/${BOOKING_SERVER_APP_JAR} ./booking-service/
COPY --from=maven-build /tmp/movie-theater-service/target/${MT_APP_JAR} ./movie-theater-service/
COPY start.sh /opt/

 CMD ["sh", "/opt/start.sh"]
