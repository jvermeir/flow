#!/bin/bash

curl -L http://sourceforge.net/projects/plantuml/files/plantuml.jar/download > plantuml.jar

mvn install:install-file \
   -Dfile=./bin/aspectj-maven-plugin-1.12.1.jar \
   -DgroupId=com.nickwongdev \
   -DartifactId=aspectj-maven-plugin \
   -Dversion=1.12.1 \
   -Dpackaging=jar \
   -DgeneratePom=true
