import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MetaData {
    private MysqlDataSource dataSource;

    public MetaData() throws IOException {
        File file = new File(SelectData.class.getClassLoader().getResource("db.properties").getFile());
        FileInputStream f = new FileInputStream(file);
        Properties p = new Properties();
        p.load(f);
        dataSource = new MysqlDataSource();
        dataSource.setUrl(p.getProperty("db_url"));
        dataSource.setUser(p.getProperty("db_user"));
        dataSource.setPassword(p.getProperty("db_password"));
    }

    public void getDataBaseMetaData() throws SQLException {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData dbmd = connection.getMetaData();
        System.out.println("Database Product Name:"+dbmd.getDatabaseProductName());
        System.out.println("DatabaseProductVersion:"+dbmd.getDatabaseProductVersion());
        System.out.println("DatabaseMajorVersion:"+dbmd.getDatabaseMajorVersion());
        System.out.println("DatabaseMinorVersion:"+dbmd.getDatabaseMinorVersion());
        System.out.println("DriverName:"+dbmd.getDriverName());
        System.out.println("DriverVersion:"+dbmd.getDriverVersion());
        System.out.println("URL:"+dbmd.getURL());
        System.out.println("UserName:"+dbmd.getUserName());
        System.out.println("MaxColumnsInTable:"+dbmd.getMaxColumnsInTable());
        System.out.println("MaxRowSize:"+dbmd.getMaxRowSize());
        System.out.println("MaxStatementLength:"+dbmd.getMaxStatementLength());
        System.out.println("MaxTablesInSelect"+dbmd.getMaxTablesInSelect());
        System.out.println("MaxTableNameLength:"+dbmd.getMaxTableNameLength());
        System.out.println("SQLKeywords:"+dbmd.getSQLKeywords());
        System.out.println("NumericFunctions:"+dbmd.getNumericFunctions());
        System.out.println("StringFunctions:"+dbmd.getStringFunctions());
        System.out.println("SystemFunctions:"+dbmd.getSystemFunctions());
        System.out.println("supportsFullOuterJoins:"+dbmd.supportsFullOuterJoins());
        System.out.println("supportsStoredProcedures:"+dbmd.supportsStoredProcedures());
        connection.close();
    }

    public void getResultSetMetaData() throws SQLException {
        try( Connection connection = dataSource.getConnection() ){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            String col1 = resultSetMetaData.getColumnName(1);
            String col2 = resultSetMetaData.getColumnName(2);
            String col3 = resultSetMetaData.getColumnName(3);
            System.out.println("\n"+col1+"\t"+col2+"\t"+col3);

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2)+"\t"+resultSet.getString(3));
            }
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        MetaData metaData = new MetaData();
        metaData.getDataBaseMetaData();
        metaData.getResultSetMetaData();
    }
}
