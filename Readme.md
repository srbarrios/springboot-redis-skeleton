# Java backend with SpringBoot and Redis

This is a Java backend example, with easy API endpoints that can connect to a Redis NoSQL Database. Also, this backend has the bases to be deployed in Google Cloud.

## Start it

```
gradle clean
gradle build
gradle bootRun #Optionally you can pass the argument --spring.profiles.active
```

## API Request Examples

### Retrieve all users

```
curl --request GET 'http://localhost:9090/users/' --header 'Content-Type: application/json'
```

### Create a user

```
curl --location --request POST 'http://localhost:9090/users/' --header 'Content-Type: application/json' --data-raw '{"mailAddress":"srbarrios@gmail.com","lives":3, "wrongAnswers" : []}'
```

### Update a user
```
curl --location --request PUT 'http://localhost:9090/users/srbarrios@gmail.com' \
--header 'Content-Type: application/json' \
--data-raw '{"mailAddress":"srbarrios@gmail.com","lives":1, "wrongAnswers" : []}'
```
