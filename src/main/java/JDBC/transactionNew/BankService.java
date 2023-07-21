package JDBC.transactionNew;

import JDBC.transactionNew.BankDao;
import JDBC.utils.JdbcUtilsV2;
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
        Connection connection = JdbcUtilsV2.getConnection();
        //一个事务的最基础的要求，必须是同一个连接对象
        try {
            //开启事务
            //关闭事务自动提交
            connection.setAutoCommit(false);
            //执行数据库动作
            bankDao.add(addAccount,money);
            System.out.println("-------");
            bankDao.sub(subAccount,money);
            //事务提交
            connection.commit();

        }catch (Exception e){
        connection.rollback();
        throw  e;
        }finally {
            JdbcUtilsV2.freeConnection();
        }

    }
}
