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
