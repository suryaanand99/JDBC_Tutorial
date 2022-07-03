package javaJDBC;

import java.io.*;
import java.sql.*;

public class InsertData {
    public static void insertUsingStatement(Connection connection, File file){
        try (Statement st = connection.createStatement()) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] attr = line.split(",");
                String sql = String.format("insert into person values('%s','%s','%s')", attr[0], attr[1], attr[2]);
                st.executeUpdate(sql);
                line = bufferedReader.readLine();
            }
            System.out.println("Inserted data successfully using statement...");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static  void insertUsingPreparedStatement(Connection connection, File file){
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert  into person values(?,?,?)"
        )){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null){
                String[] attr = line.split(",");
                preparedStatement.setString(1, attr[0]);
                preparedStatement.setInt(2, Integer.parseInt(attr[1]));
                preparedStatement.setString(3, attr[2]);
                preparedStatement.executeUpdate();
                line = bufferedReader.readLine();
            }
            System.out.println("Inserted data successfully using prepared statement...");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertUsingBatch(Connection connection, File file){
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into person values(?,?,?)"
        )){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null){
                String[] attr = line.split(",");
                preparedStatement.setString(1, attr[0]);
                preparedStatement.setInt(2, Integer.parseInt(attr[1]));
                preparedStatement.setString(3, attr[2]);
                preparedStatement.addBatch();
                line = bufferedReader.readLine();
            }
            preparedStatement.executeBatch();
            System.out.println("Inserted data successfully using prepared statement in batches...");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.1.104:8080/testdb",
                "root", "password");
        File file = new File(InsertData.class.getClassLoader().getResource("Data.txt").getFile());

        InsertData.insertUsingBatch(connection, file);

        connection.close();
    }
}
