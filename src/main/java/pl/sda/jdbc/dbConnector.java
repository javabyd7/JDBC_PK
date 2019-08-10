package pl.sda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class dbConnector {
    private static Connection connection = null;
    private final static String ADDRESS = "jdbc:mysql://localhost";
    private final static String DATABASE = "employee_db";
    private final static String USER = "hbstudent";
    private final static String PASSWORD = "hbstudent";
    private final static String PORT = "3307";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String PARAMS = "useTimezone=true&serverTimezone=UTC";

    private static String getFormatedURL() {
        return ADDRESS + ":" + PORT + "/" + DATABASE + "?" + PARAMS;
    }

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadConnection() {
        try {
            connection = DriverManager.getConnection(getFormatedURL(), USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection() {
        if (connection == null) {
            loadDriver();
            loadConnection();
        }
        return connection;
    }


}
