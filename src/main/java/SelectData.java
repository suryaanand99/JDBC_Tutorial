import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SelectData {
    public MysqlDataSource dataSource;
    
    public SelectData() throws IOException {
        File file = new File(SelectData.class.getClassLoader().getResource("db.properties").getFile());
        FileInputStream f = new FileInputStream(file);
        Properties p = new Properties();
        p.load(f);
        dataSource = new MysqlDataSource();
        dataSource.setUrl(p.getProperty("db_url"));
        dataSource.setUser(p.getProperty("db_user"));
        dataSource.setPassword(p.getProperty("db_password"));
    }

    // calls stored procedure
    public void getBloodGroupById(String name) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            CallableStatement callableStatement = connection.prepareCall("{call getBloodGroupByName(?)}");
            callableStatement.setString(1, name);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                System.out.println("Name: " + resultSet.getString("name")
                        + " age: " + resultSet.getInt("age")
                        + " blood: "+ resultSet.getString("blood_group")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        SelectData selectData = new SelectData();
        selectData.getBloodGroupById("Praneesh");
    }
}
