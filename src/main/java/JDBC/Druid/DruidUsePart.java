package JDBC.Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUsePart {
    /*直接使用代码设置连接池连接参数
       1.创建一个Druid连接池对象
       2.设置连接池参数【必要|非必要】
       3.获取连接【通用方法，连接池都一样】
       4.回收连接
     */
    @Test
    public void test() throws SQLException {
        DruidDataSource  dataSource=new DruidDataSource();
        //设置参数
            //必须: 连接数据库驱动类的全限定符【注册驱动】|URL|user|password
            //非必须；初始化连接池的数量，最大的连接数量......
        dataSource.setUrl("jdbc:mysql:///dbtset1");
        dataSource.setUsername("root");
        dataSource.setPassword("abc1234");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");//帮助我们进行驱动注册并获取连接
        dataSource.setInitialSize(5);//初始化数量
        dataSource.setMaxActive(10);//最大数量
        DruidPooledConnection connection = dataSource.getConnection();
        //数据库crd操作
        //回收连接
        connection.close();;

    }
    /*
    通过读取外部配置文件的方式来实例化连接池对象
     */
    @Test
    public  void  testSoft() throws Exception {
        //读取外部配置文件properties
        Properties properties=new Properties();
        //src下的文件可以使用类加载器提供的方法
        InputStream ips = DruidUsePart.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(ips);
        //使用连接池的工具类的工程模式，创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        //进行数据库crd操作
        connection.close();

    }
}
