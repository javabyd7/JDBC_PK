package pl.sda.jdbc;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        dbConnector.getConnection();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/employee_db?useTimezone=true&serverTimezone=UTC",
                    "hbstudent",
                    "hbstudent");
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

            /*
            String insert = "insert into employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, 10000);
            preparedStatement.setString(2, "Nowak");
            preparedStatement.setString(3, "Piotr");
            preparedStatement.setString(4, "x3435");
            preparedStatement.setString(5, "nowak@wp.pl");
            preparedStatement.setString(6, "5");
            preparedStatement.setInt(7, 1102);
            preparedStatement.setString(8, "bezrobotny");
            preparedStatement.execute();
*/
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
