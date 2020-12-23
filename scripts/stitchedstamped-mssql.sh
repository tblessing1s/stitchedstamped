#!/usr/bin/env bash

docker stop stitchedstamped
docker rm stitchedstamped

docker run \
    -e 'ACCEPT_EULA=Y' \
    -e 'SA_PASSWORD=password1E!' \
    -p 1437:1433 \
    --name stitchedstamped \
    -d \
    --restart unless-stopped \
    microsoft/mssql-server-linux:2017-latest

sleep 15
docker cp stitchedstampedsetup.sql stitchedstamped:/stitchedstampedsetup.sql
sleep 15
winpty docker exec -it stitchedstamped sh -c "./opt/mssql-tools/bin/sqlcmd -s localhost -U SA -P password1E! -i stitchedstampedsetup.sql"
