package JDBC.utils;

import com.alibaba.druid.util.JdbcUtils;
import com.mysql.cj.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class UtilsTest {
    public void testInsert() throws SQLException {
        Connection connection= jdbcUtils.getConnection();
        jdbcUtils.freeConnection(connection);

    }
}
