# UserOrder_Kafka

## Getting Started
Running kafka with 3 topic named test, test1, test2.

### Structure

```
Application: It is the main file that calls the various API's running on different host where you provide the id, name, email as in json format. It runs on port 8085
```
```
Producer: It is a service that initiate a consumer to consume the message created by various producers during the services. It runs on the port 8084
```
```
User_verification: It is a service that is responsible for creating user, placing new orders, verify user. To place any order you must be a valid user. It runs on the port 8080. It produces the message using topic test.
```
```
Status: It is a service that is only responsible for checking the status of the orders placed by the valid user. It produces the message of various stages using topic test1. It runs in the port 8081.
```
```
Refund: It is a Service that is responsible to make a refund of any order that is not in right condition, which is denoted by order code other than 200 and 203. 201 code is used to denote order is Initiated, and 203 is used to denote order is placed for refund. This service runs on the port 8083.
```

## Authors
Sudip Kandel

