package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String URL ="jdbc:mysql://localhost:3306/mysql";
    private static final String USERNAME ="root1";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение получено!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения с базой данных: " + e.getMessage());
            }
        }
    }

}
