package JDBC.transactionNew;

import JDBC.utils.JdbcUtilsV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankDao {
    //加钱的操作数据库的方法
    public void add(String account, int money) throws ClassNotFoundException, SQLException {
        Connection connection = JdbcUtilsV2.getConnection();
        //3.编写sql语句
        String sql="update t_bank set money = money + ? where account = ?; ";
        //4.创建statement对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //5.？赋值
        statement.setObject(1,money);
        statement.setObject(2,account);
        //6.发送sql
        statement.executeUpdate();
        //7.关闭资源

        statement.close();
        System.out.println("加钱成功");
    }
    public void sub(String account, int money) throws SQLException, ClassNotFoundException {
        Connection connection = JdbcUtilsV2.getConnection();

        //3.编写sql语句
        String sql="update t_bank set money = money - ? where account = ?; ";
        //4.创建statement对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //5.？赋值
        statement.setObject(1,money);
        statement.setObject(2,account);
        //6.发送sql
        statement.executeUpdate();
        //7.关闭资源

        statement.close();
        System.out.println("减钱成功");

    }
}
