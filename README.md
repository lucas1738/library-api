# Library API

Proof of Concept developed to put into practice Java 11 features in a Microsservices environment. 

## Getting Started

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes. 

### Prerequisites

Requirements for the software and other tools to build, test and push
- Java 11
- Gradle
- Docker

### Installing

A step by step series of examples that tell you how to get a development
environment running

Set up the project database using Docker

    docker run --name containerPostgres --restart=always -e POSTGRES_USER=lucasbarbosa -e POSTGRES_PASSWORD=lucas123 -e POSTGRES_DB=library_db -p 5432:5432 -d postgres

Run the application with

    ./gradlew bootRun


## Running the tests

Tests are divided in modules. There are two modules: test and contractTest

### Run Unit Tests

Tests responsible for assuring of code components' quality 

    ./gradlew test

### Run Contract Test

Tests responsible for assuring that API's contract is accurate

    ./gradlew contractTest

### Run All Tests

Run all automate test to assure code quality

    ./gradlew quality

## Stage

Before commiting please run the following command to clean and build the project

    ./gradlew stage


## Authors

- **Lucas Magalhães** 
  [GitHub](https://github.com/lucasmagalhaees)

