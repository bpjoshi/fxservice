# fxservice

Sample REST API for FX Service

## How to Run 

You can import this in your IDE as maven project and run there. Also this application is packaged as a war with Tomcat 8 embedded. You run it using the ```java -jar``` command.

## About the Service

The service is just a simple hotel review REST service. It uses an in-memory database (H2) to store the data.The REST URIs are defined in ```com.bpjoshi.fxservice.api.TradeController```.


* All APIs are "self-documented" by Swagger2 using annotations. Which can be accessed at ```localhost:8999/swagger-ui.html``` and can be used to perform rest request.



### Get information about system health, configurations, etc.

```
http://localhost:8998/env
http://localhost:8998/health
http://localhost:8998/info
http://localhost:8998/metrics
```
