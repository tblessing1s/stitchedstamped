#!/usr/bin/env bash

docker stop stitchedstamped-migration
docker rm stitchedstamped-migration

docker run \
    -e 'ACCEPT_EULA=Y' \
    -e 'SA_PASSWORD=password1E!' \
    -p 1437:1433 \
    --name stitchedstamped-migration \
    -d mcr.microsoft.com/mssql/rhel/server:2019-CU1-rhel-8

sleep 15
docker cp stitchedstampedsetup.sql stitchedstamped-migration:/stitchedstampedsetup.sql
sleep 15
winpty docker exec -it stitchedstamped-migration sh -c "./opt/mssql-tools/bin/sqlcmd -s localhost -U SA -P password1E! -i stitchedstampedsetup.sql"
