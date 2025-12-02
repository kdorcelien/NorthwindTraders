package com.pluralsight;

import java.sql.*;

public class Query {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root",
                "");

        String query = "SELECT ProductId, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductId = ? OR ProductName LIKE ?";


        try {
            PreparedStatement statement = connection.prepareStatement(query);

     statement.setInt(1,2);
     statement.setString(2, "%hot%");



            ResultSet results = statement.executeQuery();



            while (results.next()) {
                int id = results.getInt("ProductId");
                String name = results.getString("ProductName");
                int uPrice = results.getInt("UnitPrice");
                int uInstock = results.getInt("UnitsInStock");
                System.out.printf("Id:   %d %n Name:   %s %n Price:   %d %n Stock:   %d %n",id ,name, uPrice, uInstock);
                System.out.println("--------------------------------------------------------");
            }

            results.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
