package dictionary.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/dictionary";
    private static final String USERNAME = "root";
    private static final String PWD = "root";

    private static Connection connection;

    public DBConnector(){
    }
    public static Connection getConnection(){
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME,PWD);
            if (!connection.isClosed()){
                System.out.println("Соединение с БД установлено!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
