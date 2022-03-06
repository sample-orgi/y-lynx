FROM harbor.trssllc.com/trss-base-image/trss-ubuntu-20.04-java8:stable

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS=""

WORKDIR /home/trss

USER root
ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown trss:trss entrypoint.sh
USER trss

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8083 5701/udp

ADD *.war app.war

