package JDBC;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class PSCURD {

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///dbtest1", "root", "abc1234");
        //编写sql语句
        String sql = "insert into t_user(account,password,nickname)values(?,?,?)";
        //创建prepadstatement并传入sql
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //占位符赋值
        preparedStatement.setObject(1, "text");
        preparedStatement.setObject(2, "text");
        preparedStatement.setObject(3, "33");
        //发送sql语句
        int rows = preparedStatement.executeUpdate();
        //输出结果
        if (rows > 0) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入失败");
        }
        //关闭资源
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///dbtest1", "root", "abc1234");
        String sql = "update t_user set nickname= ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 44);
        preparedStatement.setObject(2, 3);
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");

        }
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///dbtest1", "root", "abc1234");
        String sql = "delete from t_user where id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 3);
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }

    }

    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        /*使用map来接收每一行的数据（key--列名  value--内容），然后再用list<map>来接收所有数据
            如何获取列的名称
        * */
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///dbtest1", "root", "abc1234");
        String sql = "select *from t_user;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map> list = new ArrayList<>();
        //ResultSetMetaData 内装的是当前结果集列的信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        //可以根据metaData的下标来获取列的数量

        while (resultSet.next()) {
            Map map = new HashMap<>();
            /*
            map.put("id", resultSet.getInt("id"));
            map.put("account", resultSet.getString("account"));
            map.put("password", resultSet.getString("password"));
            map.put("nickname", resultSet.getString("nickname"));
              list.add(map);

             */
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <columnCount;  i++) {
                //获取对应列下角标的值
                Object value = resultSet.getObject(i);
                String key = metaData.getColumnLabel(i);
                map.put(key,value);

            }
            list.add(map);


        }
        System.out.println("list " + list);
        resultSet.close();
        connection.close();
        preparedStatement.close();
    }


}
