# Devowelizer

This is a simple test automation project that tests the only enpdoint in the 'Devowelizer' service. The service removes vowels from input strings.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 21
- Maven
- Docker (for local test runs)

### Installing

Well, just clone the repository: `git clone https://github.com/yourusername/devowelizer.git`

## Project structure

The project has 2 standard java packages: the **java** source that contains the API client and model and the **test** code that contains the only class **DevowelTest.java**. Naming convention are as per the default Surefire plugin config: test classes should end with ***Test.java** in order to be picked up by the runner.

## Dependancies

The project uses TestNG and RestAssured as main dependancies. It also uses Hamcrest matchers in the verification step of the test.

## Running tests

Locally the docker container containing the target service should be running.

`docker run -d -p 8086:8080 casumo/devowelizer:latest`

Tests can be run by simply invoking maven **test** phase. There is only one property **app.casumo.api** defaulted to **localhost:8086** where it is assumed the service would be running. For other test environments (ex. dev, test etc.) or other ports on local machine, it can be overwritten on in the command line as 

`mvn clean test -Dapp.casumo.api=http://testenv.domain.com`
or
`mvn clean test -Dapp.casumo.api=http://localhost:3000`

Tests are run in parallel by default. To run the tests sequentally the parameter **parallel** should be set to false in the test class.

`
@DataProvider(name = "inputAndExpectationForDevowelization", parallel = false)
`

## Github actions

A Github actions workflow has been added in **.github/workflows/ct.yml**.
It is triggered on pull requests and push events. It will compile the source and test code first. Then it will pull the service docker image and will start it on port 8086.
At the end it will run the tests with maven.
