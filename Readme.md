#Database

## Create database
Run `createdb -T template0 sensordb`

## Create database structure
Use sql_dump.sql to create database structure and fill in 
all needed data. For this run `psql -d sensordb -f sql_dump.sql`.

##Configure datasource
Go to `web/src/main/resources/application.properties` and
change datasource variables according to yours.

#Project

##Build project
Run `./gradlew build`

##Run project
Run `java -jar web/build/libs/web-0.0.1-SNAPSHOT.war`


