package JDBC;


import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

//模拟用户登录
/*
Todo:
    1.明确jdbc使用流程和详细内容内部的api设计
    2.发现问题，引出preparedStatement

Todo:输入账号和密码
    进行数据库信息查询（t_user）
    反馈登录成功还是失败

ToDo:
    键盘输入事件，收集账号信息和密码
    注册驱动
    获取连接
    创建statement
    发送sql语句并返回结果
    解析结果
    关闭资源

* */
public class StatementLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //获取用户信息
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入账号");
        String account = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        //注册驱动
        /*问题：注册两次驱动
                1.DriverManager.registerDriver();本身会注册一次
                2.Driver。static{DriverManager.registerDriver();}静态代码块也会注册一次

          解决问题：只想注册一次
                  只出发静态代码块
          触发静态代码块：在类加载的时刻会触发静态代码块
                       类加载机制
                                加载：【class文件->jvm虚拟机的class对象】
                                连接：【验证（检查文件类型）->准备（静态默认值）->解析（触发静态代码块）】
                                初始化（静态属性赋真实值）
           触发类加载：
                    1.new关键字
                    2.调用静态方法
                    3.调用静态属性
                    4.接口 1.8 default默认实现
                    5.反射
                    6.子类触发父类
                    7.程序的入口
        * */
        //方案一
        //DriverManager.registerDriver(new Driver());
        //方案二反射   字符串 ->外部的配置文件  完成数据库驱动的切换，在不改变代码的情况下
        Class.forName("com.mysql.cj.jdbc.Driver");//触发类加载

        //获取数据库连接
        /*      getConnection(1,2,3);是一个重载方法，允许开发者用不同的方式传入数据库连接的核心参数
                核心属性：
                        1.数据库软件所在主机的ip地址；  127.0.0.1本机
                        2.数据库软件所在主机的端口号     3306
                        3.连接的具体库                dbtest1
                        4.连接的账号密码               root---abc1234
                        5.可选的连接信息

                三个参数：String URL         数据库软件所在的信息，连接的具体库，以及其他的可选信息
                                            语法：jdbc:数据库厂商名【mysql，Oracle】://ip地址||主机名:port/数据库名？key=value
                                                 &key=value 可选信息！
                                            具体：jdbc:mysql://127.0.0.1:3306/dbtest1

                                            本机的省略写法：如果你的数据库软件安装在你的本机，可以进行一些省略
                                                jdbc:mysql://127.0.0.1:3306/dbtest1=jdbc:mysql:///dbtest1
                                                省略了本地地址和默认端口号
                        String  user
                        String  password
                两个参数：String URL      :数据库软件所在的信息，连接的具体库，以及其他的可选信息
                        Properties info :存储账号密码  类似于map  只不过key和value都是字符串类型的

                一个参数：String URL ：jdbc:mysql://127.0.0.1:3306/dbtest1?user=root&password=abc1234

        * */
        Connection connection = getConnection("jdbc:mysql://127.0.0.1:3306/dbtest1?user=root＆password=root");
        //创建statement对象
        Statement statement = connection.createStatement();
        //发送sql语句获得结果
        /*
        TODO:
            字符串拼接比较麻烦，而且只能拼接字符串类型比较麻烦
            有可能发生注入攻击 -----动态值充当了sql语句结构，影响了查询结果
        * */
        String str="SELECT * FROM t_user WHERE account='"+account+"' AND password='"+password+"';";
        ResultSet resultSet = statement.executeQuery(str);
        //结果集的解析
        /*java是一种面向对象，将查询结果封装成了一个resultSet对象，我们应该理解，内部一定是有行有列的
        resultSet->逐行获取数据，行->列的数据
        A ResultSet object maintains a cursor pointing to its current row of data.
        Initially the cursor is positioned before the first row. The next method moves the cursor to the next row,
        and because it returns false when there are no more rows in the ResultSet object,
         it can be used in a while loop to iterate through the result set.
         想要进行数据解析我们需要进行两件事情:1.移动游标获得数据行 2.获得指定行数据的列数据即可
                1.游标移动问题 resultSet内部包含一个游标指向当前行数据
                默认游标指向的是第一行数据之前
                然后调用next（）方法移动游标，如果有很多行可以调用while循环
                Boolean =next（）
                2.获取游标指向的行的列的数据
                ResultSet.get类型（String columnLabel |int  columnIndex）
                    columnLabel:列名
                    columnIndex：列的下角标  从左往右 从1开始

        * */
        //只要移动游标就代表登录成功
        /*
        if(resultSet.next()){
            System.out.println("登录成功");
        }
        else {
            System.out.println("登录失败");
        }

         */

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account1 = resultSet.getString("account");
            String password1 = resultSet.getString(3);
            String nickname = resultSet.getString(4);
            System.out.println(id+"--"+account+"--"+password);

        }

        resultSet.close();
        statement.close();
        connection.close();


    }
}
