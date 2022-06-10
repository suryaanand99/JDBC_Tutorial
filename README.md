# JDBC #

> **Steps:**
>1. load and register driver class
>2. establish connection between java applications and database
>3. creation of statement
>4. prepare, send and execute sql
>5. process result from resultset
>6. close the connection

## Load and Register driver class ##

There are 4 types of drivers
1. *type -1 drivers / Bridge driver*
2. *type -2 drivers / Native driver*
3. *type-3 drivers / Network driver*
4. *type-4 drivers / thin driver*

>Recommended Driver - thin driver

`Class.forName(driver _name);`

But from Java 1.6, JVM takes care of loading the driver automatically based on jdbc_url and classpath
registering the driver to driverManager is done as part of internal static block implementation of the driver

##Establish connection between java applications and database ##

`Connection con = DriverMaager.getConnection(jdbc_url, username, password);`

- one connection object can be associated with many statement objects
- JDBC_URL = main_protocol:sub_protocol:subname
- main_protocol is always jdbc

## Creation of statement ##

- Statement object purpose is to send sql query for execution and give back the results

`Statement st = con.createStatement();`

The Statement Interface has:
- execute()
- executeQuery()
- executeUpdate()
- getResultSet()
- getUpdateCount()

## Prepare, Send and Execute sql ##

Types of sql statement:
- ddl : create table, alter table, drop table
- dml: insert, delete, update
- dql: select
- dcl: alter password, grant access
- dac: start audit, stop audit
- tcc: commit, rollback, savepoint etc

To execute sql queries:
1. `public ResultSet executeQuery(String sql) throws SQLException`
    - to execute select query
>When used with non-select query in executeQuery():
>- Type 1 driver: SQL Exception
>- Type 4 driver: empty ResultSet
 
2. `public int executeUpdate(String sql) throws SQLException`
    - to execute non-select queries (dml queries)
    - returns no of rows affected by the sql
>When used with select query in executeUpdate():
>- Type 1 driver: SQL Exception
>- Type 4 driver: no of rows selected 
>> When used with create query in executeUpdate():
>>- Type 1 driver: returns -1
>>- Type 4 driver: returns 0
3. `public boolean execute(String sql) throws SQLException`
    - to execute both select or non-select queries
    - if we don't know what type of query we are going to use, use execute method
    - if return type is true, it is select query otherwise it is non-select query
    - to get resultSet, `st.getResultSet();`
    - to get no of rows affected, `st.getUpdateCount();`

## Process result from ResultSet ##

ResultSet interface contains
- `public boolean next()`
- `public int findColumn(String column_name)`
- `public xxx getXxx(String column_name)` or `public xxx getXxx(int column_index)`

**Sample program to get ResultSet:**
```
while(rs.next()){
   // Read data from that Record
   SOP(rs.getInt("mn0") + "..." + re.getString("mname"));
}
```

>- Per statement object, only one resultSet can be possible
>- If we get another result by executing a new sql query, the previous result set will closed
>- When trying to access previous result set, we get SQLException

## Close the connection ##

1. close resultSet, rs.close()
2. close statement, st.close()
3. close connection, con.close()

>Instead of three close() method, close the connection alone.
>It will take care of closing statement object.
>Statement object will take care of closing resultSet

## Working with type 1 driver ##
```
Java         --->  Type - 1 driver           --- ->  ODBC    --> DataBase
applications <---  (JDBC-ODBC Bridge driver) <----   driver  <--
```
- it is platform dependent. only for windows
- Driver className: sun.jbdc.odbc.JdbcOdbcDriver
- jdbc_url = jdbc:odbc:dsn_name

## Working with type 2 driver ##
```
Java         ---> Type - 2 driver          ---->  Vendor specific --> DataBase
applications <--- (Native Api Partly Java) <----  Native library  <--
```
- performance is better than type -2
- database dependent

>1. place oracle jar in classpath
>2. driver class name: oracle.jdbc.driver.OracleDriver or oracle.jdbc.OracleDriver
>3. jdbc_url: jdbc:oracle:oci8:@XE where XE is system Id

## Working with type 4 ##
```
Java         ---> Type - 2 driver            ---->  DataBase
applications <--- (pure Java or thin driver) <----
```
- platform independent
- driver class name: oracle.jdbc.driver.OracleDriver or oracle.jdbc.OracleDriver
- jdbc_url : jbdc:oracle:thin:@localhost:1521:XE

**Limitation of type-4:**
- No scalability
- resource utilization is not upto the mark
- cannot customize
- complex deployment
- vendor specific code

## Working with type 3 ##
```
Java         ---> Type - 2 driver   ---->  Middleware           --> DataBase
applications <--- (Middleware Java) <----  server (IDS server)  <--
```
- database independent
- platform independent
- middleware server internally uses type -1/2/4 driver to communicate with database
- driver class : ids.sql.IDSDriver
- jdbc_url : jbdc:ids://localhost:12/conn?dsn=dsn_name

