#!/usr/bin/env bash

java -jar ./bin/plantuml.jar ./src/main/analysis/test.sd
open ./src/main/analysis/test.png &

