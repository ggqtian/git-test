package JDBC.BankTest;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//一个转账方法是一个事务，事务是在业务层开启的
/*todo:事务是在业务层开启的,利用try catch代码块完成事务的提交回滚
        将connection传入dao层即可！dao只负责使用
* */
public class BankService {
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        transfer("33","22",500 );

    }
    public void transfer(String addAccount,String subAccount,int money) throws SQLException, ClassNotFoundException {
        BankDao bankDao=new BankDao();
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.创建连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///dbtest1", "root", "abc1234");


        //一个事务的最基础的要求，必须是同一个连接对象
        try {
            //开启事务
            //关闭事务自动提交
            connection.setAutoCommit(false);
            //执行数据库动作
            bankDao.add(addAccount,money,connection);
            System.out.println("-------");
            bankDao.sub(subAccount,money,connection);
            //事务提交
            connection.commit();

        }catch (Exception e){
        connection.rollback();
        throw  e;
        }finally {
            connection.close();
        }

    }
}
