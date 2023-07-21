package JDBC;

import org.junit.Test;

import java.sql.*;
//------------批量数据插入
public class PSOtherPart1 {


    //使用批量插入
    /*todo:路径后面添加？rewriteBatchedStatements=true代表允许批量插入
                insert语句后面必须是values，并且不能有;
                不是执行每条executeQuery  而是批量添加executeBatch() 后统一执行executeBatch()
                减少了java端与数据库端交互的次数，将多次交互集中到一次交互
    * */
    @Test
    public void testBatchInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
   "jdbc:mysql:///dbtest1?rewriteBatchedStatements=true","root","abc1234");
        String sql="insert into t_user(account,password,nickname) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setObject(1,"nn"+i);
            preparedStatement.setObject(2,"dd"+i);
            preparedStatement.setObject(3,"ss"+i);
            preparedStatement.addBatch();//不执行追加到values后面
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.close();
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println(end-start);

    }
    @Test
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///dbtest1?user=root&password=abc1234");
        String sql="insert into t_user(account,password,nickname) values(?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setObject(1,"dd"+i);
            preparedStatement.setObject(2,"gg"+i);
            preparedStatement.setObject(3,"mm"+i);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        connection.close();
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println(end-start);

    }

}
