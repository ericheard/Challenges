This folder contains the Reverse Geocoding Challenge code, Intellij IDEA project and Gradle build. It buil

Requirements to build:

Java 1.8
Gradle 3.1

How to build (windows): gradlew build  OR  build.cmd

How to run: java -jar build\libs\reversegeocoding-1.0-SNAPSHOT.jar  OR  run.cmd

Rest Calls:

Query (GET)
http://localhost:8080/geocoding/reverse/query?lat=33.969601&long=-84.100033
http://localhost:8080/geocoding/reverse/query?lat=33.969601&long=-84.100033&provider=google

NOTE: Parameter "provider" is optional and has the following values google and other (Default is google). 
In this code "other" also uses google it is just a example of delegating to multiple implementations.

History (GET)
http://localhost:8080/geocoding/reverse/history

Example rest calls:

Duluth NCR: http://localhost:8080/geocoding/reverse/query?lat=33.969601&long=-84.100033
Arc de Triomphe: http://localhost:8080/geocoding/reverse/query?lat=48.8737952&long=2.2928335
Buckingham Palace: http://localhost:8080/geocoding/reverse/query?lat=51.5013673&long=-0.144084
Great Pyramid: http://localhost:8080/geocoding/reverse/query?lat=29.9792391&long=31.1320079
The Westin St. John: http://localhost:8080/geocoding/reverse/query?lat=18.3253051&long=-64.7970157
The Peterhof: http://localhost:8080/geocoding/reverse/query?lat=59.8863068&long=29.9064036
The Empire State Building: http://localhost:8080/geocoding/reverse/query?lat=40.7484445&long=-73.9878584
Sydney Opera House: http://localhost:8080/geocoding/reverse/query?lat=-33.8567799&long=151.2131027
Parthenon Greece: http://localhost:8080/geocoding/reverse/query?lat=37.9715327&long=23.7245226
Canary Islands: http://localhost:8080/geocoding/reverse/query?lat=27.9581072&long=-15.8773237
Panama Canal: http://localhost:8080/geocoding/reverse/query?lat=9.1428462&long=-80.0049452
Hubbard Glacier: http://localhost:8080/geocoding/reverse/query?lat=60.3138652&long=-139.5112594

http://localhost:8080/geocoding/reverse/history

