package com.test.sqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname mysqlTest
 * @Description 类型说明
 * @Date 2021/3/6 17:35
 * @Created by 小白sy
 */
public class MysqlTest {
    public static void main(String[] args) throws Exception {
        //1、数据库驱动加载
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2、连接数据库的url、用户名、账号
        Connection sycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_project?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC",
                "root", "sy123456");
        queryResult(sycon);
    }

    public static void queryResult(Connection sycon) throws SQLException {
        //3、创建查询
        Statement systatement = sycon.createStatement();
        //4、编写查询语句
//        String sql="select * from userinfo where username='Roy' and password='123456' ";
        String sql = "select * from userinfo where  password='123456'";
        //5、执行查询
        ResultSet resultSet = systatement.executeQuery(sql);
        ResultSetMetaData thead = resultSet.getMetaData();


        List<Map<String,String>> resultlist=new ArrayList<>();

        while(resultSet.next()) {
           Map<String,String> resultmap=new HashMap<>();
           for(int column=1;column<=thead.getColumnCount();column++) {
//            System.out.println("第"+column+"列字段名是"+thead.getColumnName(column)+"字段的值是"+resultSet.getString(column));
                resultmap.put(thead.getColumnName(column), resultSet.getString(column));
            }
            resultlist.add(resultmap);
        }
        System.out.println(resultlist);
    }

}
