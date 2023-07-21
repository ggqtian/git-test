package JDBC;



import java.sql.*;
import java.util.Scanner;

/*使用预编译statement来完成用户登录

todo：防止注入攻击  |演示ps的使用流程
* */
public class PSuserLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //获取用户信息
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入账号");
        String account = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        //ps的数据库流程
        //注册驱动---利用反射机制触发静态代码块完成一次驱动注册

            Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbtest1", "root", "abc1234");
        //1.编写sql语句结构 不包含动态值语句，动态符用占位符？代替   ----？只能替代动态值的部分
        String sql="select *from t_user where account = ? and password = ? ;";
            //2.创建preparedstatement，并且传入sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.动态值 占位符？赋值
            //index：从左到右从1开始数占位符的位置
            //object：占位符的值，可以设置任何类型的数据

            preparedStatement.setObject(1,account);

            preparedStatement.setObject(2,password);
             //4.发送sql语句，并获得结果集
            //statement:ececuteUpdate(sql)||executeQuery(sql);
            //preparedStatement:ececuteUpdate()||executeQuery()---已经知道语句和动态值
            ResultSet resultSet = preparedStatement.executeQuery();
        //结果集解析
        if(resultSet.next()){
            System.out.println("登录成功");
        }
        else {
            System.out.println("登录失败");
        }

        //释放资源
        resultSet.close();
        connection.close();
        preparedStatement.close();



    }
}
