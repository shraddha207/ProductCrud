# Product Catalogue RESTful API


This Spring Boot application provides a RESTful API for managing a product catalogue system. It allows users to perform CRUD operations on products, search products with filters, and rate products.

## Technologies Used

* Spring Boot
* MongoDB
* Swagger

##Prerequisites
Before running this application, ensure that you have the following installed:

* Java Development Kit (JDK) 8 or higher
* Maven
* MongoDB

#Setup Instructions

Follow these steps to set up and run the Product Catalogue API:

1.Clone the Repository:  Clone the project repository from GitHub.

2.Configure MongoDB: Install and configure MongoDB on your local machine. Update the MongoDB connection details in the application.properties file.


##Getting Started
Follow these steps to set up and run the application:

1.Clone this repository to your local machine

```
git clone https://github.com/shraddha207/ProductCrud.git
```
2.Navigate to the project directory.

```
cd product_crud
```
3.Build the project using Maven

```
mvn clean install -DskipTests

```
4.Run the application.

```
java -jar target/ProductCrud-0.0.1-SNAPSHOT.jar

```

Click [here](http://localhost:9091/swagger-ui.html#/product45controller) to access the Swagger UI in your browser.

#Documentation
Click [here](http://localhost:8590/swagger-ui.html#/product45controller) to access the documentation.





