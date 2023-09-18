# SPS Commerce Evaluation README

## Building the application
- Application was built with Java 17
- You will need Maven installed (built with Maven 3.9.0)

To build the application, run the following command:

`mvn clean install`

## Running the application
Once the application has been built, it can be run with the following command:

`mvn spring-boot:run`

The application will start on port 8080 (configurable via `application.properties` file.)

Useful URLs:
- swagger-ui: http://localhost:8080/swagger-ui
- OpenAPI spec: http://localhost:8080/api-docs
- H2 console: http://localhost:8080/h2-console
  - username: sa (`spring.datasource.username` in `application.properties`)
  - password: password (`spring.datasource.password` in `application.properties`)
- API base URL: http://localhost:8080/
  - Location: http://localhost:8080/api/location
  - Organization: http://localhost:8080/api/organization
  - Product: http://localhost:8080/api/product
  - Subscription: http://localhost:8080/api/subscription

## API Key
To make API calls, add a `X-API-KEY` header to requests with a value of: `APIKEY`
Both are configurable in `application.properties`.
- `api.key.name=X-API-KEY`
- `api.key.value=APIKEY`

## Postman Collection
If you want to use Postman, there is a Postman collection included 
in the project root directory: `SPS Commerce Postman Collection.postman_collection.json`