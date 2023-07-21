package JDBC.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
/*
  todo:
        利用线程本地变量存储连接信息，确保一个线程的多个方法可以得到同一个连接
        在做事务操作时service和dao方法属于同一个线程就不同再传递参数了大家都可以调用get方法得到同一个connection
 */

public class JdbcUtilsV2 {
    private static DataSource dataSource=null;
    static ThreadLocal<Connection> t1=new ThreadLocal<>();
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
        //先去查看线程本地是否存在
        Connection connection = t1.get();
        if(connection==null){
            //线程本地变量没有则连接池中获取
             connection = dataSource.getConnection();
            t1.set(connection);
        }
        return connection;
    }
    public static void freeConnection() throws SQLException {
        Connection connection = t1.get();
        if(connection!=null){
            t1.remove();//清空线程本地变量数据
            connection.setAutoCommit(true);//事务状态回归
            connection.close();
        }

    }

}
