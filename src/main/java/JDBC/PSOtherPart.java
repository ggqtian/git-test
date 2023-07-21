package JDBC;

import org.junit.Test;

import java.sql.*;

/*
todo:主键回显：
            1.再创建preparedStatement的时候，告知携带回数据库自增长的主键
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            2.通过preparedStatement.getGeneratedKeys()获得一行一列的结果集，
             通过next和get方法得到主键值
* */
public class PSOtherPart {
    @Test
    public void returnPrimaryKey() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///dbtest1?user=root&password=abc1234");
        String sql="insert into t_user(account,password,nickname) values(?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1,"test1");
        preparedStatement.setObject(2,"123456");
        preparedStatement.setObject(3,"baba");

        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println("插入成功");
            //获取司机（Statement）装主键的结果集对象
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();//移向第一行
            int r = generatedKeys.getInt(1);//第一列的数据
            System.out.println(r);
        }
        else {
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();


    }

}
