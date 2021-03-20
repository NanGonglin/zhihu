package com.test.sqlUtil;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @Classname MysqlUtils
 * @Description 类型说明
 * @Date 2021/3/6 18:06
 * @Created by 小白sy
 */
public class MysqlUtils {
    private Connection myconnector;

    public void creatconnect()  {
        try {
            Properties syp=new Properties();
            syp.load(MysqlUtils.class.getResourceAsStream("/login.properties"));

            String classname=syp.getProperty("driverclass");
            String url=syp.getProperty("jdbcurl");
            String username=syp.getProperty("username");
            String password=syp.getProperty("password");

            //1、数据库驱动加载
            Class.forName(classname);
            //2、连接数据库的url、用户名、账号
            myconnector= DriverManager.getConnection(url,username,password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败。");
        }

    }

    public boolean checklogin(String user,String pwd){
        ResultSet resultSet=null;
        try {
            //3、创建查询
            Statement systatement = myconnector.createStatement();
            //4、编写查询语句
//        String sql="select * from userinfo where username='Roy' and password='123456' ";
            String sql = "select * from userinfo where username='" + user + "' and  password='" + pwd + "'";
            //5、执行查询
            resultSet = systatement.executeQuery(sql);

            if (resultSet != null && resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("数据库查询失败");
            return false;
        }
    }
    public boolean prochecklogin(String user,String pwd){
        ResultSet resultSet=null;
        try {
            //3、创建查询
            CallableStatement callableStatement = myconnector.prepareCall("call login(?,?)");
            callableStatement.setString(1,user);
            callableStatement.setString(2,pwd);

            //5、执行查询
            resultSet = callableStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("数据库查询失败");
            return false;
        }
    }

    public  List<Map<String,String>> queryResult(String user,String pwd) throws SQLException {
        //3、创建查询
        Statement systatement = myconnector.createStatement();
        //4、编写查询语句
//        String sql="select * from userinfo where username='Roy' and password='123456' ";
        String sql = "select * from userinfo where username='" + user + "' and  password='" + pwd + "'";
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
        return resultlist;
    }

    public  Map<String,String> getUserinfo(String user)  {
        try {
            //3、创建查询
            Statement systatement = myconnector.createStatement();
            //4、编写查询语句
//        String sql="select * from userinfo where username='Roy' and password='123456' ";
            String sql = "select * from userinfo where username='" + user + "'";
            //5、执行查询
            ResultSet resultSet = systatement.executeQuery(sql);
            ResultSetMetaData thead = resultSet.getMetaData();
            Map<String,String> resultmap=new HashMap<>();

            while(resultSet.next()) {
                for(int column=1;column<=thead.getColumnCount();column++) {
    //            System.out.println("第"+column+"列字段名是"+thead.getColumnName(column)+"字段的值是"+resultSet.getString(column));
                    resultmap.put(thead.getColumnName(column), resultSet.getString(column));
                }
            }
            return resultmap;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
}
