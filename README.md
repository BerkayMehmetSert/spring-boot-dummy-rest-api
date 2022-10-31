# Dummy REST API

This is a dummy REST API built with [Spring Boot](https://spring.io/projects/spring-boot).

## Getting Started

To get started, you can simply clone this repository and import the project into your favorite IDE.

### Prerequisites

The following items should be installed in your system:

* Java 8 or newer.
* Maven 3.0 or newer.
* Your favorite IDE

### Installing

* Clone this repository
* Import into your favorite IDE
* Install the dependencies with `mvn install`
* Run the application with `mvn spring-boot:run`
* Access the deployed web application at: http://localhost:8080
* You can also access the Swagger UI at: http://localhost:8080/swagger-ui.html

## Database

The application uses PostgreSQL as the database. You can install it from [here](https://www.postgresql.org/download/).

### Application Properties

The `application.properties` file is located in `src/main/resources` folder. You can change the database connection
settings there.

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/DummyDB
spring.datasource.username=_yourUsername_
spring.datasource.password=_yourPassword_
spring.jpa.properties.javax.persistence.validation.mode=none
```

### Database creation

The database can be created with the following command:

```sql
CREATE
DATABASE DummyDB;
```

### Database tables

The database tables are created automatically by Hibernate.

## Endpoints

### User

**GET /api/users**

Returns all the users.

Parameters:

* `page` - Page number (optional)
* `size` - Page size (optional)
* `search` - Search query (optional)

**GET /api/users/{id}**

Returns the user with the given id.

**GET /api/users/{id}/company**

Returns the company of the user with the given id.

**GET /api/users/{id}/company/address**

Returns the address of the company of the user with the given id.

**GET /api/users/{id}/address**

Returns the address of the user with the given id.

**GET /api/users/{id}/bank**

Returns the bank of the user with the given id.

**POST /api/users**

Creates a new user.

**PUT /api/users/{id}**

Updates the user with the given id.

**DELETE /api/users/{id}**

Deletes the user with the given id.

### Product

**GET /api/products**

Returns all the products.

Parameters:

* `page` - Page number (optional)
* `size` - Page size (optional)
* `search` - Search query (optional)

**GET /api/products/{id}**

Returns the product with the given id.

**Get /api/products/{id}/category**

Returns the category of the product with the given id.

**GET /api/products/{id}/images**

Returns the images of the product with the given id.

**POST /api/products**

Creates a new product.

**PUT /api/products/{id}**

Updates the product with the given id.

**DELETE /api/products/{id}**

Deletes the product with the given id.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [PostgreSQL](https://www.postgresql.org/) - Database
* [Swagger](https://swagger.io/) - API Documentation
* [Lombok](https://projectlombok.org/) - Java Library
* [ModelMapper](http://modelmapper.org/) - Java Library

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
