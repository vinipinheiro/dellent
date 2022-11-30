REST service to calculate an Alticci Sequence

According with the challenge's document, the Alticci sequence is define as follows:

n=0 => a(0) = 0

n=1 => a(1) = 1

n=2 => a(2) = 1

n>2 => a(n) = a(n-3) + a(n-2)

Requiriments 

- Java 11
- Maven

Swagger http://localhost:8080/swagger-ui/index.html#/

Execution via Postman
Method: GET
URL: http://localhost:8080/alticci/{n}
{n}: index sequence
```
