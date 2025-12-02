package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class Query {
    final static Scanner scan = new Scanner(System.in);
    static Connection connection;
    static Statement statement;
    static ResultSet results;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    openConnection(args);
    menuDisplay();



    }

    public static void openConnection(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind",
                args[0],
                args[1]
        );
    }

    public static void menuDisplay() throws SQLException, ClassNotFoundException {
        System.out.println("What do you want to do?\n" +
                "1) Display all products\n" +
                "2) Display all customers\n" +
                "0) Exit\n" +
                "Select an option: ");

        int option = scan.nextInt();
        scan.nextLine();


        switch (option) {
            case 1 -> {
                displayProducts();
            }
            case 2 -> displayCustomers();
            case 0 -> {
                try{
                System.out.println("Au-Revoir");
                scan.close();
            } finally {
                    closeConnection();
                }
            }
        }
    }

    public static void displayProducts() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Products";
        try {

            statement = connection.createStatement();
            results = statement.executeQuery(query);

            while (results.next()) {
                int id = results.getInt("ProductId");
                String name = results.getString("ProductName");
                int uPrice = results.getInt("UnitPrice");
                int uInstock = results.getInt("UnitsInStock");
                System.out.printf("Id:   %d %n Name:   %s %n Price:   %d %n Stock:   %d %n", id, name, uPrice, uInstock);
                System.out.println("--------------------------------------------------------");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void displayCustomers() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Customers ORDER BY Country";
        try {

            statement = connection.createStatement();


            results = statement.executeQuery(query);

            while (results.next()) {
                String contactName = results.getString("ContactName");
                String companyName = results.getString("CompanyName");
                String city = results.getString("City");
                String country = results.getString("Country");
                String phone = results.getString("Phone");

                System.out.printf("Contact:   %s %n Company:   %s %n City:   %s %n Country:   %s %n Phone:   %s %n", contactName, companyName, city, country, phone);
                System.out.println("--------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() throws SQLException {

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

}
