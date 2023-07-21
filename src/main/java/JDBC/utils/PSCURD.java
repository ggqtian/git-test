package JDBC.utils;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCURD extends BaseDao {

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {

        String sql = "insert into t_user(account,password,nickname)values(?,?,?)";
        executeUpdate(sql, "33", "55", "77");
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {

        String sql = "update t_user set nickname= ? where id = ?";
        executeUpdate(sql, "新的nick", "id");


    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {

        String sql = "delete from t_user where id=?;";
        executeUpdate(sql, "1", "3");
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
            for (int i = 1; i < columnCount; i++) {
                //获取对应列下角标的值
                Object value = resultSet.getObject(i);
                String key = metaData.getColumnLabel(i);
                map.put(key, value);

            }
            list.add(map);


        }
        System.out.println("list " + list);
        resultSet.close();
        connection.close();
        preparedStatement.close();
    }


}
