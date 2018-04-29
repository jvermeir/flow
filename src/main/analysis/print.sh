#!/usr/bin/env bash

java -jar ./bin/plantuml.jar /tmp/flow_test.sd
open /tmp/flow_test.png &

