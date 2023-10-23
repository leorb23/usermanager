## Local deployment
### Dependencies for Local Development
this application uses spring boot embed server and runs in 8080 port.
Just run the UserManagerApplication class

#### Database:
This application uses an H2 in memory database, once application is running the database is created.
Database credentials and properties(db name, user, password, connection) are in the application.properties file
Once the application stops the Database will be deleted.
If a persistent Database is needed modify in application.properties file like this
```
#spring.datasource.url=jdbc:h2:mem:db_user_manager
spring.datasource.url=jdbc:h2:./src/main/resources/data/db_user_manager
```

#### cURL: Import the cURL and execute it from postman
Save user
```
curl --location 'localhost:8080/user-manager/users/save' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Leandro Erazo",
    "email": "leandro.erazo@gmail.com",
    "password": "Abc12",
    "phones": [
        {
            "number": "30027490789",
            "cityCode": "123",
            "countryCode": "57"
        }
    ]
}'
