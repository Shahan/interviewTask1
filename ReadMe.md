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
This project follows KISS principle (https://en.wikipedia.org/wiki/KISS_principle). So there is only one java class that includes all 3 tests. Hence no auxiliary DTOs, services, etc.

### Dependencies
**RESTAssured** for rapid tests-for-REST development

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

### Configuration
There are 2 configuration properties defined in `src/test/resources/test.properties` file:
```
baseApiUrl=https://swapi.co
baseApiPath=/api
```
They both together build a prefix for all requests (`baseApiUrl` + `baseApiPath`)