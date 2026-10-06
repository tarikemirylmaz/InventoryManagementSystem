# Inventory Management System

A Java-based inventory management system developed with **Maven**, **Swing GUI**, database connectivity, and a structured data access layer.

## About the Project

Inventory Management System is a desktop application designed to manage products, categories, users, and stock information.

The project follows a structured Maven architecture and separates the graphical user interface, business logic, and database operations into different components.

It was developed to practice object-oriented programming, database operations, GUI development, and software architecture concepts in Java.

## Technologies Used

- Java
- Java Swing
- Maven
- SQL
- JDBC
- JPA / Persistence
- Object-Oriented Programming (OOP)
- DAO (Data Access Object) Pattern
- NetBeans GUI Builder

## Features

- User login system
- Product management
- Stock tracking and management
- Product category management
- Electronic and food product types
- Database connectivity
- Database queries
- Graphical user interface
- Persistent data management
- Modular Maven project structure

## Project Structure

The project follows the standard Maven directory structure:

```text
src/
└── main/
    ├── java/
    │   └── com/mycompany/inventorytrackingproject/
    │       ├── Main.java
    │       ├── Product.java
    │       ├── ElectronicProduct.java
    │       ├── FoodProduct.java
    │       ├── InventoryManager.java
    │       ├── DBConnection.java
    │       ├── ProductDAO.java
    │       ├── StockDAO.java
    │       ├── UserDAO.java
    │       ├── LoginScreen.java
    │       ├── MainScreen.java
    │       ├── ProductScreen.java
    │       ├── StockScreen.java
    │       ├── QueryScreen.java
    │       └── CategoryDetailScreen.java
    │
    └── resources/
        └── META-INF/
            └── persistence.xml
```

## Main Components

- `Main.java` - Entry point of the application
- `Product.java` - Base product model
- `ElectronicProduct.java` - Represents electronic products
- `FoodProduct.java` - Represents food products
- `InventoryManager.java` - Handles inventory-related operations
- `DBConnection.java` - Manages database connectivity
- `ProductDAO.java` - Handles product database operations
- `StockDAO.java` - Handles stock database operations
- `UserDAO.java` - Handles user-related database operations
- `LoginScreen.java` - User login interface
- `MainScreen.java` - Main application interface
- `ProductScreen.java` - Product management interface
- `StockScreen.java` - Stock management interface
- `QueryScreen.java` - Database query interface
- `CategoryDetailScreen.java` - Displays category-related information

## Architecture

The project uses the **DAO (Data Access Object) pattern** to separate database access operations from the application's business logic and user interface.

It also follows the standard Maven project structure, keeping Java source files and application resources organized separately.

The `persistence.xml` file contains persistence-related configuration used by the application.

## Project Report

The repository includes:

`InventoryManagementSystem_Report.docx`

The report contains additional information about the project's analysis, design, implementation, and system structure.

## Author

**Tarık Emir Yılmaz**
