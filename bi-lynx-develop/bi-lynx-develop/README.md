# BiLynx

## Overview
Use this project as a starting point to quickly create a new RI Microservice.

## Prerequistes
- RI microservice setup running locally: https://wiki.trssllc.com/display/PD/Local+Setup

## Usage
- clone this project and set the local project name to bi-[project-name]
   * _kebab-case_ for bi-new-project-name

- open in intellij

- replace across entire project (match case):
  * `biLynx` -> `bi[ProjectName]`
     * _UpperCamelCase_ for ProjectName
  * `BiLynx` -> `Bi[ProjectName]`
     * _UpperCamelCase_ for ProjectName 
  * `bilynx` -> `bi[projectname]`
     * _lowercase_ for projectname
  * `8083` -> `[portnumber]`
     * example: 8083

- rename file 
	- `BiLynxApp` -> `Bi[ProjectName]App`
	    - location: `/src/main/java/com/trss/bi/`

- setup project sdk (jdk 8)

- close intellij

- delete the `bi-[project-name]/.git` folder

- restart intellij
	- resolve: ! invalid VCS root mapping
	    - remove as git project
	- resolve: unmanaged pom 
	    - add as a maven project

- run `Bi[ProjectName]App` run config
	- run as is (if intellij autocreated it)
    - or create own springboot run config

- verify it registered with the registry at: http://localhost:8761/#/

- go to the gateway swagger: http://localhost:8100/#/admin/docs
	- select `bi[projectname]` from dropdown
	- execute the GET helloworld
	- success returned

### datasource configuring

- update `spring.data` `mongodb` and `elasticsearch` values
    - `/src/main/resources/config/application-dev.xml`

- update environmentTag value
    - `/application-dev.xml` (local overrides)

### scope configuring
- in SecurityConfiguration.java, you can define a scope on the token that a user is required to have in order to access the api
- uncomment the scope code in this file, and specify the appropriate scope name
- update `bi-auth` `UserTokenEnhancer.addScopes()` to add the new scope name

## JHipster README

This application was generated using JHipster 5.8.1, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v5.8.1](https://www.jhipster.tech/documentation-archive/v5.8.1).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

This application is configured for Service Discovery and Configuration with the JHipster-Registry. On launch, it will refuse to start if it is not able to connect to the JHipster-Registry at [http://localhost:8761](http://localhost:8761). For more information, read our documentation on [Service Discovery and Configuration with the JHipster-Registry][].

## Development

To start your application in the dev profile, simply run:

    ./mvnw

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

To optimize the biLynx application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/*.war

Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./mvnw clean test

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

Then, run a Sonar analysis:

```
./mvnw -Pprod clean test sonar:sonar
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a mongodb database in a docker container, run:

    docker-compose -f src/main/docker/mongodb.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mongodb.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw package -Pprod verify jib:dockerBuild

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 5.8.1 archive]: https://www.jhipster.tech/documentation-archive/v5.8.1
[doing microservices with jhipster]: https://www.jhipster.tech/documentation-archive/v5.8.1/microservices-architecture/
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v5.8.1/development/
[service discovery and configuration with the jhipster-registry]: https://www.jhipster.tech/documentation-archive/v5.8.1/microservices-architecture/#jhipster-registry
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v5.8.1/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v5.8.1/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v5.8.1/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v5.8.1/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v5.8.1/setting-up-ci/
