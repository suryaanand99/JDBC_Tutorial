package javaJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.1.104:8080/testdb",
                "root", "password");
        Statement statement = connection.createStatement();
        String create_table = "create table person(" +
                "name varchar(10)," +
                "age int," +
                "blood_group varchar(10)" +
                ")";
        statement.executeUpdate(create_table);
        System.out.println("Table created successfully");
        statement.close();
        connection.close();
    }
}
