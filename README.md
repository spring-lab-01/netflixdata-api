# Start application at 8080
mvn spring-boot:run

## APIs are secured via basic auth 
- username : user
- password : password

# Testing in swagger
- http://localhost:8080/swagger-ui/index.html
- Add authorization header
  ![](swagger-auth.png)  
  ![](swagger-auth-user-pwd.png)

# Testing in browser 
http://localhost:8080/api/v1/content-dataset?offset=0&page_size=10&sort_by=releaseYear&filter=type:tv,year:2025

# Testing using curl commands

curl -v "http://localhost:8080/ping" ^
-H "accept: */*"


curl -v "http://localhost:8080/api/v1/content-dataset?offset=0&page_size=10&sort_by=releaseYear" ^
-H "accept: */*" ^
-u "user:password"

curl -v "http://localhost:8080/api/v1/content-dataset?offset=0&page_size=10&sort_by=releaseYear" ^
-H "accept: */*" ^
-H "Authorization: Basic dXNlcjpwYXNzd29yZA=="


