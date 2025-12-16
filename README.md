

## Overview
A distributed travel booking platform built using **Spring Boot 4**, **Java 17**, and **microservices architecture**.  
It allows users to book flights and hotels, process payments, and receive notifications.



## Architecture Diagram

https://drive.google.com/file/d/1c4zL3SnuK-kyLjSVTVv9jyQUUcwtQn_r/view?usp=sharing

**Description:**
- **User → Booking Service**: Validates user
- **Booking Service → Flight & Hotel Services (Feign Client)**: Checks availability
- **Booking Service → Payment Service (WebClient)**: Processes payment
- **Booking Service → Notification Service (WebClient)**: Sends notification
- **Booking Status**: PENDING → CONFIRMED



## Microservices & Ports

| Service                | Port | Description                                 |
|------------------------|------|---------------------------------------------|
| User Service           | 8081 | Manage users                                |
| Flight Service         | 8082 | Manage flights and availability            |
| Hotel Service          | 8083 | Manage hotels and availability             |
| Booking Service        | 8084 | Orchestrates booking flow                  |
| Payment Service        | 8085 | Handles payments                            |
| Notification Service   | 8086 | Sends booking notifications                 |



## Booking Flow Summary

1. **User sends booking request:**
2. Booking Service:
  •	Validates user via WebClient

  •	Checks flight & hotel availability via Feign Client

  •	Calculates total cost

  •	Stores booking as PENDING

  •	Calls Payment Service via WebClient

  •	Calls Notification Service via WebClient

  •	Updates booking to CONFIRMED

## 3. Getting Started
 Prerequisites
  •	Java 17+

  •	Maven 3+

 •	Postman

## 4. Running Services
 Run each service in a separate terminal:
 
   •	cd user-service && mvn spring-boot:run
   
   •	cd flight-service && mvn spring-boot:run
   
   •	cd hotel-service && mvn spring-boot:run
   
   •	cd booking-service && mvn spring-boot:run
   
   •	cd payment-service && mvn spring-boot:run
   
   •	cd notification-service && mvn spring-boot:run

##5. Postman Testing
 postman collection: https://drive.google.com/file/d/1GVdotV6r1DQ3EoRHSUzpOyNG37eOdSXw/view?usp=sharing





