# demo-spring-microservices
**Eureka**

1. Whenever a service is up on server, it will register itself with eureka(i.e central server) using _register-with-eureka_ property. Its details like name, port, host, etc. are stored in the service registry.
   It also gets list of other services registered with eureka using _fetch-registry_ property.
   
   ![image](https://user-images.githubusercontent.com/68026750/147881991-bde35b28-9f5b-4e73-b597-187594b81721.png)

2. WHY TO USE:  
  i. We never know which service is up or down, what is the port on which its available.   
  ii. So whenver we want to contact some service, we can get its contact details (port no.) from eureka registry using its application name.  
  iii. So suppose CODERMS want to call API of PROBLEMMS, then the url will be:  
      String url = "http://PROBLEMMS/"+ urlParams;  
      So we don't need to know its port no., we will directly get the available one from eureka registry.  
  iv. Also, it we use ribbon with eureka, then it will do the load balancing automatically (based on the configurations). Like first time it will call Problem service on port 6002, then second time on 6003, then third time again on 6002 and so on in alternate order. And this all with just one line URL!! 
      

**Zuul**

1. Zuul acts like a gateway. The client will be sending requesting to it, and based on the URL it will find out where to route that request.
2. We create a Zuul application and register it with eureka.
3. In application.properties (configuration file), we add information regarding the request routing

_zuul.routes.user.path=/user/problems/*\
zuul.routes.user.service-id=UserMS_

https://www.tutorialspoint.com/spring_boot/spring_boot_zuul_proxy_server_and_routing.htm


**Hystrix**

1. Whenever we send a request to some service and we didn't get success due to any reason (may be the service is not up, or some failure, or some connection issues, etc.), we need something to handle this situation. When such failure occurs, we need to stop sending requests to this service for some time so that we do not overload it with continously failining requests and it recover from failure.
2. Circuit breaking pattern is used for that. The library will tolerate failures up to a threshold. Beyond that, it leaves the circuit open. Which means, it will forward all subsequent calls to the fallback method, to prevent future failures.
3. Whenever the circuit is open, the response from the fallabck method is provided. Fallback executes when a. an error occurs, b. timeout occurs or c. circuit opens

\
_hystrix.command.default.metrics.rollingStats.timeInMilliseconds=10000\
hystrix.command.default.circuitBreaker.requestVolumeThreshold=10\
hystrix.command.default.circuitBreaker.errorThresholdPercentage=50\
hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds=10000_ 

Within a time frame of 10000ms (timeInMilliseconds) if we get 10 (requestVolumeThreshold) requests, out of which more that 50% (errorThresholdPercentage) requests re failed then the circuit will be open for 10000ms (sleepWindowInMilliseconds) i.e, for neext 10000ms request will be not be sent to the actual method/handler and it will be providing fallback response only. After 10000ms, a request will be sent to actual method, if it works fine then the circuit will be close again and the normal excution will start again.  Otheriws if this request fails, then again circuit will be opened for next 10000ms.

https://cloud.spring.io/spring-cloud-netflix/multi/multi__circuit_breaker_hystrix_clients.html
https://www.tutorialspoint.com/spring_cloud/spring_cloud_circuit_breaker_using_hystrix.htm
