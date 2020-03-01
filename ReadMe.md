# Task
### Description
Develop several tests for The Star Wars API project (https://swapi.co).

### Mandatory technologies
- **Java** for code
- **Gradle** to build 
- **TestNG** to run tests
- **Allure** to make reports

### List of tests
1. Test a search for ships that contain `Starships` substring in field `starship_class`
2. (Smoke test) Check full data match for ship with `id = 39` (except links to episodes, pilotes, etc.)
3. (Smoke test) Ensure that planet with `id = 0` does not exist 

### Other requirements
- it should be possible to run only `smoke` tests
- prepare instructions how to run all tests or only smoke tests

# Build & Run instructions
### Run smoke tests
1. `cd` to project root
2. `gradlew clean smokeTest` (`./gradlew clean smokeTest` for *nix terminal)

### Run all tests
1. `cd` to project root
2. `gradlew clean test` (`./gradlew clean test` for *nix terminal)

# Implemenataion details
### Architecture
This project consists of 3 layers:
- `Tests layer`. Contains classes with executable tests.
- `Service layer`. Contains steps implementations that are used by steps. Utils/helper classes are also to be presented there.
- `Domain layer`. DTOs and POJOs to be there.

Auxiliary test data is located in `resources/data`.

### Dependencies
**RESTAssured** for rapid tests-for-REST development.

**Lombok** to replace boiler-plate ``getter/setters/constructors/toString`` methods with annotations.

**Gson** for JSON (de)serialization. 

### Reporting
Allure is supported here. That's why there are so many eye-catching annotations :)

#### How to open report
To open Allure report do the following:
1. `cd` to project root
2. `gradlew allureServe` (`./gradlew allureServe` for *nix terminal)

#### Attachments
The following data is attached to tests reports for the convenience:
- REST requests
- REST resposes
- Setup data: base API URL and base API path
- Some objects that are provided to particular steps as parameters.

### Configuration
There is 1 configuration property defined in `src/test/resources/test.properties` file:
```
baseApiUrl=https://swapi.co/api
```
`baseApiUrl` is a prefix for all REST requests. 