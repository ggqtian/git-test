package JDBC.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//封装Dao层数据库重复代码
/*Todo：简化非dql和dql操作
···
*/
public abstract class BaseDao {
    //封装简化非dql语句  --sql：sql语句  ---params：占位符的值
    //注意传入占位符的值必须与问号位置复合
    public int executeUpdate(String sql, Object... params) throws SQLException {
        Connection connection = JdbcUtilsV2.getConnection();
        //创建prepadstatement并传入sql
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //占位符赋值
        //可变参数可以当数组使用
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        //发送sql语句
        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();
        //是否回收连接需要考虑是不是事务
        if (connection.getAutoCommit()) {
            JdbcUtilsV2.freeConnection();
        }
        //connection.setAutoCommit(false);//开启事务那么连接由业务层处理

        return rows;

    }

    /*
    非dql语句  返回值固定---int类型
    dql语句    返回值   ---list<T>
        数据库的数据 ->对应java中的实体类
        table
            t_user
                id
                account
                password
        java
            USER
                ID
                ACCOUNT
                PASSWORD
        刚好表中的一行数据 ->java类的一个对象 ->多行  ->list<java实体类> list；
        <T> 声明一个泛型，
         1.确定泛型  eg.   User.class T=User{}
         2.使用反射技术属性赋值

     将查询结果封装到一个实体类集合
        clazz  要接值的实体类集合的模板对象
        sql    查询语句，要求列名或者别名等于实体类的属性名
        params  占位符的值
        返回值   查询的实体列集合
        T       声明的结果泛型

     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //获取连接
        Connection connection = JdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params == null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> list = new ArrayList<>();
        //ResultSetMetaData 内装的是当前结果集列的信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        //可以根据metaData的下标来获取列的数量
        while (resultSet.next()) {
            //调用类的无参构造函数
            T t = clazz.newInstance();
            for (int i = 1; i < columnCount; i++) {
                //获取对应列下角标的值
                Object value = resultSet.getObject(i);
                String propertiesName = metaData.getColumnLabel(i);
                //反射给对象的属性值赋值
                Field field = clazz.getDeclaredField(propertiesName);
                field.setAccessible(true);//属性可以设置打破private私有的修饰
                /*
                参数一：要赋值的对象
                参数二：具体的属性值
                如果属性是静态的属性那么第一个参数可以为空
                 */
                field.set(t, value);

            }
            list.add(t);
        }
        preparedStatement.close();
        resultSet.close();
        if (connection.getAutoCommit()) {
            JdbcUtilsV2.freeConnection();
        }
        return list;
    }
}
