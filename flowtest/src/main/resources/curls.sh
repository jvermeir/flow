#!/usr/bin/env bash

# POST
curl -X POST "http://localhost:8082/first" -H "accept: */*" -H "Content-Type: application/json" -d "{\"uuid\": \"first_id1\", \"item\": 1}"
curl -X POST "http://localhost:8082/first" -H "accept: */*" -H "Content-Type: application/json" -d "{\"uuid\": \"first_id2\", \"item\": 2}"
curl -X POST "http://localhost:8082/second" -H "accept: */*" -H "Content-Type: application/json" -d "{\"uuid\": \"second_id1\", \"item\": 1}"

# GET by id
curl "http://localhost:8082/first/first_id1"
echo
curl "http://localhost:8082/first/first_id2"
echo
curl "http://localhost:8082/second/second_id1"
echo