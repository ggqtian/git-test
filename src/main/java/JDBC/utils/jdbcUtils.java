package JDBC.utils;
/*
v1.0版本工具类:
    内部包含一个连接池对象，并且对外提供获取回收连接的方法
    工具类的方法一般写为静态的

    如何实现：
        属性：连接池对象【只实例化一次】
            1. 单例模式   2.静态代码块
        方法：1.获取对外提供的方法 2.回收外部传入的方法
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {
    private static DataSource dataSource=null;
    static {
        Properties properties=new Properties();
        InputStream ips = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //对外提供连接的方法
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public static void freeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
