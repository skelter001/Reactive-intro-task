# Reactive RESTful Web Service Calculator

[Task tech. requirement](TASK.md)

Stack: Java 11, Gradle, Spring WebFlux, Graaljs

Service produces series of calculations for each client request. For each calculation
server runs 2 JS functions passed by the client with a single argument: call number of execution.
The result are returned to the client as a stream of ordered or unordered data.

Response data is plain text (CSV-line formatted)

The service is built to work with Graaljs. 

## Calculation request

Calculation request is a JSON with 3 parameters:
- firstFunction - JavaScript code of first function
- secondFunction - JavaScript code of the second function
- iter - calculations number

## Modes

- **unordered** - the data is sent to the client as soon as it was calculated.
Response format: `<execution idx>, <function idx>, <function result>, <calculation time>`
- **ordered** - the results of two functions with common execution index are zipped together
  and then the data is sent to the client  
  Response format: `<execution idx>, <func1 result>, <func1 calculation time>,
  <count of further results of func1 already calculated>,
  <func2 result>, <func2 calculation time>,
  <count of further results of func2 already calculated>`  
  
## Client

Client module produce to connect to endpoint and pass request defined in 
configuration. 

Client profiles:
- `spring.profile.ordered` - for ordered calculation
- `spring.profile.unordered` - for unordered calculation

## Service configuration

The configuration consists of three properties:
- `reactiveintrotask.delay-millis` - the delay between function executions
- `reactiveintrotask.timeout-millis` - function execution timeout
