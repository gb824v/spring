FROM docker-registry.aeg.cloud/common-platform/jre-base-image:1.0.10

VOLUME /tmp
ENV APPDYNAMICS_AGENT_TIER_NAME=@functional.domain@-@service.domain@-@project.artifactId@

ADD appagent-@appd.version@.zip /opt/appserveragent/
RUN unzip /opt/appserveragent/appagent-@appd.version@.zip -d /opt/appserveragent/ && \
    rm /opt/appserveragent/appagent-@appd.version@.zip

ADD @project.build.finalName@.jar app.jar
ADD startService.sh /startService.sh
RUN chmod 700 /startService.sh
ADD appinfo.sh /appinfo.sh
RUN chmod 700 /appinfo.sh

ENTRYPOINT ./startService.sh false
