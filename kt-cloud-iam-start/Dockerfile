FROM openjdk:17-oraclelinux8
WORKDIR /app
COPY ./target/kt-cloud-iam.jar /app
EXPOSE 8080
ENV SYS_OPT=""
ENTRYPOINT java $SYS_OPT -jar /app/kt-cloud-iam.jar