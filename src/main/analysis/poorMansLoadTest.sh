#!/usr/bin/env bash

for i in `seq 1 10`; do
   curl -X POST "http://localhost:8082/first" -H "accept: */*" -H "Content-Type: application/json" -d "{\"uuid\": \"first_id$i\", \"item\": $i}"
   curl "http://localhost:8082/first/first_id$i"
   curl -X POST "http://localhost:8082/second" -H "accept: */*" -H "Content-Type: application/json" -d "{\"uuid\": \"second_id$i\", \"item\": $i}"
   curl "http://localhost:8082/second/second_id$i"
done