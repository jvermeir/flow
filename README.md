# Flow

Identify the main code paths through a Spring Boot application.
 
## Import in IntelliJ

```
Create a new project by importing pom.xml from the root of this folder

Add the test project 'flow-test'"

file -> module -> new module from existing source
select flow-test and import as a maven module
```

## Run the test project:

```
cd ./flow-test
mvn clean install
java -jar target/flow-test.jar
```

## Collect test data

This step is necessary for the Python utility in src/main/analysis. analyze_log.py needs a logfile generated during use of flow-test.
```
./src/main/analysis/poorMansLoadTest.sh
```
This accesses the REST services in flow-test, creating the file /tmp/flow.log that can be used for analysis
using analyze_log.py.
Run analyze_log.py and generate a png:
```
python ./src/main/analysis/analyze_log.py
./src/main/analysis/print.sh
```


## Experiments

The code in flow contains several experiments. 

- The asm package holds a sample class App (copied from ...) using the ASM library. This code currently detects callers of a specified method. 
This should be extended to work recursively (some embarrassing first attempt is in the code).
- The flow package tries to use the Spoon library. Finding JPA implementations works, but finding 
callers of a method seems difficult. 
 
Both libraries work on either compiled code or sources, trying to reliably analyze code. This appears more
difficult than expected, so another approach would be to run an application that logs relevant method calls.
Flowtest is intended to be run as an application, logging the calls that result from accessing the api. 

![alt=sample output](https://github.com/jvermeir/flow/blob/master/flow_test.png)
