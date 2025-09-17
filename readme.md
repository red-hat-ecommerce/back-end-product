# Red Hat E-commerce Product Service

A microservice for managing product information in the Red Hat E-commerce platform. This service provides REST APIs for retrieving product data.

## Overview

This service is part of a microservices-based e-commerce application. It's responsible for providing product information to other services and clients. The service is built using Quarkus and Apache Camel, and it's designed to be deployed in containers.

## Technologies Used

- **Quarkus**: Red Hat build of Quarkus 3.20.1.SP1
- **Java**: Java 21
- **Apache Camel**: For routing and integration
- **Maven**: For build and dependency management
- **Docker**: For containerization
- **JUnit 5 & REST-assured**: For testing

## Prerequisites

- JDK 21 or later
- Maven 3.8.1 or later
- Docker (for containerized deployment)

## Getting Started

### Building the Application

```bash
mvn clean package
```

For native compilation:

```bash
mvn clean package -Pnative
```

### Running the Application

#### Development Mode

```bash
mvn quarkus:dev
```

This will start the application in development mode with hot reload enabled.

#### Production Mode

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

### Running Tests

```bash
mvn test
```

## API Endpoints

### Get All Products

- **URL**: `/api/product/`
- **Method**: GET
- **Response Format**: JSON
- **Success Response**:
  - **Code**: 200
  - **Content**: Array of product objects
  ```json
  [
    {
      "productId": 4,
      "productName": "Product 4",
      "productPrice": 100000.0,
      "productShortDescription": "Product 4",
      "productDescription": "<p>Product 4 </p> <p> Lorem ipsum dolor sit amet...</p>"
    },
    {
      "productId": 5,
      "productName": "Product 5",
      "productPrice": 50000.0,
      "productShortDescription": "Product 5",
      "productDescription": "<p>Product 5 </p> <p> Lorem ipsum dolor sit amet...</p>"
    },
    {
      "productId": 6,
      "productName": "Product 6",
      "productPrice": 202500.0,
      "productShortDescription": "Product 6",
      "productDescription": "<p>Product 6 </p> <p> Lorem ipsum dolor sit amet...</p>"
    }
  ]
  ```

## Deployment

### Docker

The application can be containerized using the provided Dockerfile:

For a regular JVM apps,
```bash
# Build the application
mvn clean package

# Build the Docker image
docker build -t redhat-ecommerce/product-service -f Dockerfile.jvm .

# Run the container
docker run -p 8080:8080 redhat-ecommerce/product-service
```

For native apps,
```bash
# Build the application
mvn clean package -Dnative

# Build the Docker image
docker build -t redhat-ecommerce/product-service -f Dockerfile.native-micro .

# Run the container
docker run -p 8080:8080 redhat-ecommerce/product-service
```

### Configuration

The application can be configured using the `application.properties` file located in `src/main/resources/`. Key configuration properties include:

- `quarkus.http.port`: The port on which the application listens (default: 8080)
- `quarkus.log.level`: The log level for the application (default: INFO)
- `quarkus.management.port`: The port for management endpoints (default: 9000)

## Project Structure

- `src/main/java/com/redhat/ecommerce/product/model/`: Contains the data models
- `src/main/java/com/redhat/ecommerce/product/service/`: Contains the business logic
- `src/main/java/com/redhat/ecommerce/product/route/`: Contains the API routes defined with Apache Camel
- `src/main/resources/`: Contains configuration files
- `src/test/java/`: Contains test classes

## Development

### Adding New Products

To add new products, modify the `getProducts()` method in the `ProductService` class:

```java
public List<Product> getProducts() {
    LOG.info("get list of Products");
    return List.of(
            new Product(4, "Product 4", 100_000.0, "Product " + 4, generateProductDescription(4)),
            new Product(5, "Product 5", 50_000.0, "Product " + 5, generateProductDescription(5)),
            new Product(6, "Product 6", 202_500.0, "Product " + 6, generateProductDescription(6)),
            // Add new products here
            new Product(7, "Product 7", 150_000.0, "Product " + 7, generateProductDescription(7))
    );
}
```

### Adding New Endpoints

To add new endpoints, modify the `configure()` method in the `ProductRoute` class:

```java
@Override
public void configure() throws Exception {
    rest("/api/product")
            .get("/")
                .produces("application/json")
                .to("direct:get-products")
            // Add new endpoint
            .get("/{id}")
                .produces("application/json")
                .to("direct:get-product-by-id");

    // Existing route
    from("direct:get-products")
            .routeId("get-products-api")
                .log("calling get-products")
                .bean(ProductService.class, "getProducts")
                    .marshal().json(JsonLibrary.Jackson);

    // New route
    from("direct:get-product-by-id")
            .routeId("get-product-by-id-api")
                .log("calling get-product-by-id")
                // Implement logic to get product by ID
                .marshal().json(JsonLibrary.Jackson);
}
```

## License

This project is licensed under the Apache License 2.0.

## Author

Muhammad Edwin < edwin at redhat dot com >