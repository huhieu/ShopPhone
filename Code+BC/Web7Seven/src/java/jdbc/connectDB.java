package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {

    public connectDB() {
        super();
    }

    public static Connection getConnect() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=7Seven;user=sa;password=123456;useUnicode=true;characterEncoding=UTF-8");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Kết nối thất bại!" + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(getConnect());
    }
}