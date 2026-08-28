# Java JDBC Database Practice

A small Java practice project for learning how to connect a Java application to a SQLite database using **JDBC**, create database tables, insert records, and retrieve data usi  ng SQL queries.

## Project Overview

This project demonstrates the basic workflow of working with a relational database from Java:

1. Connect to a SQLite database.
2. Create database tables using SQL.
3. Insert records using `PreparedStatement`.
4. Query records using `Statement`.
5. Read results using `ResultSet`.
6. Handle database errors using `SQLException`.
7. Use Java's `try-with-resources` to automatically close database resources.

The project is intentionally designed as a practice exercise, with several `TODO` sections that need to be completed.

---

## Technologies Used

* **Java**
* **JDBC (Java Database Connectivity)**
* **SQLite**
* **Maven**
* **SQL**

---

## Project Structure

```text
src/
└── main/
    └── java/
        └── co/
            └── za/
                └── practicedb/
                    ├── Database.java
                    └── DatabaseSetup.java

pom.xml
README.md
```

### `Database.java`

Responsible for creating the connection to the SQLite database.

Example:

```java
public class Database {

    public static Connection connect() throws SQLException {
        String url = "jdbc:sqlite:shop.db";
        return DriverManager.getConnection(url);
    }
}
```

### `DatabaseSetup.java`

Responsible for:

* Creating database tables
* Inserting initial data
* Inserting purchases
* Querying purchases
* Displaying database results

---

# Database Design

The practice project uses three main entities:

### Stores

Stores contain information about shops/stores.

Possible fields:

| Column       | Type    | Description             |
| ------------ | ------- | ----------------------- |
| `store_id`   | INTEGER | Unique store identifier |
| `store_name` | TEXT    | Name of the store       |

### Products

Products represent items that can be purchased.

Possible fields:

| Column         | Type    | Description               |
| -------------- | ------- | ------------------------- |
| `product_id`   | INTEGER | Unique product identifier |
| `product_name` | TEXT    | Name of the product       |
| `price`        | REAL    | Product price             |

### Purchases

Purchases record transactions made at a store.

Possible fields:

| Column        | Type    | Description                          |
| ------------- | ------- | ------------------------------------ |
| `purchase_id` | INTEGER | Unique purchase identifier           |
| `store_id`    | INTEGER | Store associated with the purchase   |
| `product_id`  | INTEGER | Product associated with the purchase |
| `price`       | REAL    | Purchase price                       |

The exact schema is intentionally left as part of the exercise.

---

# JDBC Connection

The application uses JDBC to communicate with SQLite.

The general flow is:

```text
Java Application
       |
       v
     JDBC
       |
       v
SQLite JDBC Driver
       |
       v
  SQLite Database
```

The database connection is created through:

```java
Database.connect();
```

---

# Creating Tables

The `DatabaseSetup.setup()` method contains SQL statements for creating the required tables.

For example:

```java
String table1 = """
        CREATE TABLE IF NOT EXISTS stores (
            store_id INTEGER PRIMARY KEY,
            store_name TEXT NOT NULL
        )
        """;
```

The SQL statements are executed using a `Statement`:

```java
try (
    Connection connection = Database.connect();
    Statement statement = connection.createStatement()
) {

    statement.executeUpdate(table1);

} catch (SQLException e) {
    System.out.println(e.getMessage());
}
```

### Why `IF NOT EXISTS`?

Using:

```sql
CREATE TABLE IF NOT EXISTS
```

prevents SQLite from throwing an error when the table already exists.

---

# Inserting Data

The project uses `PreparedStatement` to insert records.

Example:

```java
String insertData = """
        INSERT INTO stores (store_id, store_name)
        VALUES (?, ?)
        """;
```

Values can then be supplied using:

```java
PreparedStatement statement =
        connection.prepareStatement(insertData);

statement.setInt(1, 1);
statement.setString(2, "Shoprite");

statement.executeUpdate();
```

The `?` placeholders are replaced with values using methods such as:

```java
statement.setInt(...)
statement.setString(...)
statement.setDouble(...)
```

---

# Why Use PreparedStatement?

`PreparedStatement` is preferred when inserting values supplied by the application.

Instead of building SQL like this:

```java
String sql =
    "INSERT INTO stores VALUES (" + id + ", '" + name + "')";
```

use:

```java
String sql = """
        INSERT INTO stores (store_id, store_name)
        VALUES (?, ?)
        """;
```

This makes the code safer and easier to maintain.

It also helps protect against **SQL injection** when handling external input.

---

# Inserting Purchases

The project also requires inserting records into the `purchases` table.

The starter code contains:

```java
statement.setInt(1, 200);
statement.setInt(2, 100);
statement.setDouble(3, 356.79);
```

These values represent the parameters in the purchase `INSERT` statement.

The order of the parameters must match the order of the `?` placeholders in the SQL statement.

For example:

```sql
INSERT INTO purchases (store_id, product_id, price)
VALUES (?, ?, ?)
```

would correspond to:

```java
statement.setInt(1, 200);
statement.setInt(2, 100);
statement.setDouble(3, 356.79);
```

---

# Querying the Database

The project queries the purchases table using:

```java
String query = "SELECT * FROM purchases";
```

The query is executed using:

```java
Statement queryStatement = connection.createStatement();

ResultSet result =
        queryStatement.executeQuery(query);
```

The returned rows can then be processed using:

```java
while (result.next()) {
    int purchaseId = result.getInt("purchase_id");

    // Retrieve other columns here
}
```

---

# ResultSet

`ResultSet` represents the data returned by a SQL query.

For example:

```java
while (result.next()) {

    int purchaseId =
            result.getInt("purchase_id");

    int storeId =
            result.getInt("store_id");

    double price =
            result.getDouble("price");

    System.out.println(
        purchaseId + " | " +
        storeId + " | " +
        price
    );
}
```

The call:

```java
result.next()
```

moves the cursor to the next row.

The loop continues until there are no more rows.

---

# Try-With-Resources

The project uses Java's **try-with-resources** feature.

Example:

```java
try (
    Connection connection = Database.connect();
    Statement statement = connection.createStatement()
) {
    // Database operations

} catch (SQLException e) {
    e.printStackTrace();
}
```

This automatically closes resources such as:

* `Connection`
* `Statement`
* `PreparedStatement`
* `ResultSet`

This is important because database resources should not be left open.

---

# TODO Tasks

The current `DatabaseSetup.java` file contains several exercises.

### 1. Import Required Classes

Add the necessary JDBC imports.

For example:

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
```

---

### 2. Create the Database Tables

Complete:

```java
String table1 = """
        fill me in
        """;
```

and:

```java
String table2 = """
        fill me in
        """;
```

You need to define the appropriate SQL schemas.

---

### 3. Execute the CREATE TABLE Statements

Complete the section:

```java
// TODO: Execute your statements
```

using:

```java
statement.executeUpdate(table1);
statement.executeUpdate(table2);
```

---

### 4. Insert Store Data

Complete the `insertData` SQL statement.

Then supply values using:

```java
statement.setInt(...);
statement.setString(...);
```

and execute the statement using:

```java
statement.executeUpdate();
```

---

### 5. Insert Purchase Data

Complete:

```java
String purchaseInsert = """
        fill me in
        """;
```

Then insert the required purchase values.

---

### 6. Complete the Database Query

The project already contains:

```java
String query = "SELECT * FROM purchases";
```

Complete the `try` block so that it correctly creates:

* `Connection`
* `PreparedStatement`
* `ResultSet`

---

### 7. Retrieve the Remaining Columns

The starter code currently retrieves:

```java
int purchase_id = result.getInt("purchase_id");
```

Retrieve the remaining columns and print them.

---

# Running the Project

Clone the project:

```bash
git clone <repository-url>
```

Move into the project:

```bash
cd practicedb
```

Compile the project:

```bash
mvn compile
```

Run the application using your IDE or Maven configuration.

---

# Maven Dependency

The project requires the SQLite JDBC driver.

Example Maven dependency:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.50.3.0</version>
</dependency>
```

---

# Concepts Practiced

This project is designed to reinforce the following concepts:

### Java

* Classes
* Methods
* Variables
* Exceptions
* Try-with-resources
* Text blocks
* Object-oriented programming

### JDBC

* `Connection`
* `DriverManager`
* `Statement`
* `PreparedStatement`
* `ResultSet`
* `SQLException`

### SQL

* `CREATE TABLE`
* `INSERT`
* `SELECT`
* Primary keys
* Foreign keys
* Data types
* Parameterized queries

---

# Learning Objectives

By completing this project, you should be able to:

* Connect a Java application to SQLite.
* Create database tables from Java.
* Execute SQL statements from Java.
* Insert data using `PreparedStatement`.
* Retrieve database records using `ResultSet`.
* Understand the difference between `Statement` and `PreparedStatement`.
* Handle JDBC exceptions.
* Properly close database resources.
* Build a basic Java database application.

---

# Notes

This project is intentionally incomplete.

The `TODO` comments are exercises and should be completed before considering the database setup finished.

The main goal is not simply to make the application run, but to understand how **Java, JDBC, SQL, and SQLite work together**.

---

## Practice Challenge

After completing the TODOs, try extending the application with:

1. A method to find a purchase by ID.
2. A method to list all stores.
3. A method to add a new store.
4. A method to delete a store.
5. A method to calculate the total value of all purchases.
6. A method to find the most expensive purchase.
7. Foreign-key relationships between stores, products, and purchases.
8. A menu-driven command-line interface.

Example:

```text
===== Store Database =====

1. View stores
2. View products
3. View purchases
4. Add store
5. Add purchase
6. Exit

Choose an option:
```.
