package com.zbq.database.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author zhangboqing
 * @date 2018/4/29
 */
public class H2Test {

    //数据库连接URL，当前连接的是~/testdb目录下的test数据库
    private static final String JDBC_URL = "jdbc:h2:~/testdb/test";
    //连接数据库时使用的用户名
    private static final String USER = "sa";
    //连接数据库时使用的密码
    private static final String PASSWORD = "";
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS="org.h2.Driver";

    public static void main(String[] args) throws Exception {
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        //如果存在USER表就先删除USER表
        stmt.execute("DROP TABLE IF EXISTS t_user");
        //创建USER_INFO表
        stmt.execute("CREATE TABLE t_user(id INT(20) PRIMARY KEY,name VARCHAR(64),age INT(4),address VARCHAR(64),groups VARCHAR(10))");
        //新增
        for (int i = 16; i < 26; i++) {
            stmt.executeUpdate("INSERT INTO t_user VALUES(" +i+ ",'"+i+"号"+"',"+i+",'"+i+"号楼','"+i+"组')");
        }
        //删除
        stmt.executeUpdate("DELETE FROM t_user WHERE age < 18");
        //修改
        stmt.executeUpdate("UPDATE t_user SET groups='advanced' WHERE age > 22");
        //查询
        ResultSet rs = stmt.executeQuery("SELECT * FROM t_user WHERE age > 20");
        //遍历结果集
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name")+ "," + rs.getInt("age")+ rs.getString("address")+ ","+ rs.getString("groups"));
        }
        //释放资源
        stmt.close();
        //关闭连接
        conn.close();
    }

//    public static void main(String[] a)
//            throws Exception {
//        Connection conn = DriverManager.
//                getConnection("jdbc:h2:~/test", "sa", "");
//         add application code here
//        conn.close();
//    }
}
