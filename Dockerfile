FROM maven:openjdk AS MAVEN
WORKDIR /tmp/
COPY /src/ ./src
COPY /pom.xml ./
RUN mvn package

FROM node AS REACT
WORKDIR /tmp
COPY /web/package.json  ./
COPY /web/src ./src
COPY /web/public ./public
RUN yarn install
RUN yarn build

FROM openjdk:14-alpine
WORKDIR /tmp
COPY --from=MAVEN /tmp/target ./
COPY --from=REACT /tmp/build ./src/main/webapp/

CMD ["java", "-jar","/tmp/Heroku01.jar"]