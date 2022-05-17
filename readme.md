# API REST

Deploy: https://back-api-gf.herokuapp.com/
*Note: requires login. See more information bellow.

This project was my final assignment in Backend 1 (Certified Tech Dev carreer).
The requirements were:

- Features: AMD for Patients, Dentists, Appointments, and a login.
- Project organization and configurations.
- Business classes.
- Data access classes with the use of ORM.
- Service classes.
- Controller classes.
- Use of MVC and Thymeleaf.
- Good practices:
  - Logging exceptions with Log4J.
  - Unit testing with JUnit.


## Concepts and frameworks used

**Spring - Maven project**
- Spring Web
- JPA
- Thymeleaf
- Security

**Other dependencies**
- H2 Db
- Lombok
- Junit
- Log4j
- Modelmapper


## Goals accomplished
+ Completed functional API with basic security.
+ Global exception handler and exceptions logged with Log4j.
+ Custom endpoints/queries with HQL and JPA.
+ Unit and integration tests with Junit.
+ Use of DTOs models and Entities


## API usage
The API is currently deployed in the Heroku platform and an authentication method is required to make any request.
- Admin, user|pass: admin@mail.com|admin (Authorization: "Basic " + btoa("admin@mail.com:admin")
  - Has access to everything
- Basic user, user|pass: user@mail.com|user (Authorization: "Basic " + btoa("user@mail.com:user")
  - Only access to /appointments

**ENDPOINTS**
- Dentists: https://back-api-gf.herokuapp.com/dentists
- Patients: https://back-api-gf.herokuapp.com/patients
- Appointments: https://back-api-gf.herokuapp.com/appointments

**METHODS**
- GET: 
  - all: ("/")
  - by id: ("/{id}")
  - *Dentists exclusive: /dentists..
    - By lastname: ("/lastname/{lastname}")
    - Appointments by date and lastname: ("/lastname/{lastname}/{date}")
  - *Patients exclusive: /patients..
    - By dni: ("/dni/{dni}")
  - *Appointments exclusive: /appointments..
    - By patient id: ("/patient/{id}")
    - By dentist id: ("/dentist/{id}")
- POST: Request body.
  - any: ("/")
- PUT: Request ID and body.
  - by id: ("/{id}")
- DELETE: Request ID.
  - by id: ("/{id}")
