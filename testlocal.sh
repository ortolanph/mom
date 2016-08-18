#!/usr/bin/env bash

export SPRING_APP_PORT=9090

mvn clean install
clear
java -Dserver.port=$SPRING_APP_PORT -jar target/MoM.jar
