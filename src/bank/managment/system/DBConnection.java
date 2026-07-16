package bank.managment.system;

import java.sql.*;

public class DBConnection {

    Connection connection;
    Statement statement;

    public DBConnection() {
        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "mahak",
                    "1234"
            );

            statement = connection.createStatement();

            System.out.println("Database Connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}