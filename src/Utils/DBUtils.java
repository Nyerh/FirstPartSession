package Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static String className = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static{
        Properties properties = null;
        try {
            properties = new Properties();
            //通过IO流读取数据
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            //根据key获取value
            className = properties.getProperty("className");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("passwordA");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName(className);
            //2.使用DriverManager获取连接对象
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
