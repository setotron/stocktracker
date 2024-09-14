# Stocktracker_Springboot
Stocktracker is a demo application with Springboot, HTML and CSS tools about inventory control.
This application was made as a final degree work with my colleagues with the intention of providing large inventory companies with an intuitive and easy to use app.

The application has several functions:

* User control (Login)
* Add, modify and delete products
* Product and customer search
* Sending products to customer


## How to use it
* You will need to install Eclipse or InteliJ or another IDE compatible with Java and Springboot.
* A SQL compatible database
* A browser such as Chrome or Edge
  
It is important to modify the POM to match your database connection.

```java
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/tfg_inventario
spring.datasource.username=root
spring.datasource.password=1234
```
