package pl.sda.jdbc;

import java.sql.*;
import java.util.Scanner;

class EmployeeService {
    private static Scanner scanner = new Scanner(System.in);
    private Connection connection;

    void pilot() {
        int pilot = -1;
        while (pilot != 0) {
            System.out.println("1 - Dodaj pracownika \n2 - Wyswietl pracownikow \n0 -zakoncz program");
            pilot = scanner.nextInt();
            switch (pilot) {
                case 1: {
                    addEmployee();
                    System.out.println(1);
                    break;
                }
                case 2: {
                    printAllEmployee();
                    break;
                }
            }
        }
    }

    private void addEmployee() {
        connection = dbConnector.getConnection();
        try {
            String insert = "insert into employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            System.out.println("Podaj id: ");
            preparedStatement.setInt(1, scanner.nextInt());
            System.out.println("Nazwisko: ");
            preparedStatement.setString(2, scanner.next());
            System.out.println("Imie: ");
            preparedStatement.setString(3, scanner.next());
            System.out.println("Numer: ");
            preparedStatement.setString(4, scanner.next());
            System.out.println("Email: ");
            preparedStatement.setString(5, scanner.next());
            System.out.println("Kod biura z innej tabeli: ");
            preparedStatement.setString(6, scanner.next());
            System.out.println("Raport do istniejacego pracownika: ");
            preparedStatement.setInt(7, scanner.nextInt());
            System.out.println("Nazwa pracy: ");
            preparedStatement.setString(8, scanner.next());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void printAllEmployee() {
        connection = dbConnector.getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "select * from employees";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getString("employeeNumber")
                        + ", last name: " + resultSet.getString("lastName")
                        + ", first name: " + resultSet.getString("firstName")
                        + ", extension: " + resultSet.getString("extension")
                        + ", email: " + resultSet.getString("email")
                        + ", office code: " + resultSet.getString("officeCode")
                        + ", reports to: " + resultSet.getString("reportsTo")
                        + ", job title: " + resultSet.getString("jobTitle"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
