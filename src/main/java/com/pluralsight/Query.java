package com.pluralsight;

import java.sql.*;

public class Query {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root",
        "Olie_Corsaire001!");

Statement statement = connection.createStatement();

String query = "SELECT ProductName FROM Products";

ResultSet results = statement.executeQuery(query);

while (results.next()){
    String name = results.getString(1);
    System.out.println(name);
}
connection.close();
    }
}
