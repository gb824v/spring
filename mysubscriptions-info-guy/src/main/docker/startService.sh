#!/bin/bash

nodeName=${KUBE_ID}_@project.artifactId@_@project.version@_$(cat /proc/self/cgroup | grep docker | sed s/\\//\\n/g | tail -1)

#Get envinronment variables prefixed with '_JVM_ARG_'
arglist=$(env | grep "^_JVM_ARG_")
for entry in $arglist;
do
  jvmarglist+="$(echo $entry | awk -F= '{ st = index($0,"=");print substr($0,st+1)}') "
done

touch /app.jar

if [ "$1" == "true" ]
then
	java $jvmarglist -Djava.security.egd=file:/dev/./urandom -javaagent:/opt/appserveragent/javaagent.jar -Dappdynamics.node.name=$nodeName -Xms1024m -Xmx1024m -jar /app.jar
else
	java $jvmarglist -Djava.security.egd=file:/dev/./urandom -Xms1024m -Xmx1024m -jar /app.jar
fi
