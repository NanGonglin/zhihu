package com.test.sqlUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * @Classname propTest
 * @Description 类型说明
 * @Date 2021/3/7 16:20
 * @Created by 小白sy
 */
public class propTest {
    public static void main(String[] args) throws IOException {
        Properties syp=new Properties();
        syp.load(Properties.class.getResourceAsStream("/login.properties"));
        System.out.println(syp.getProperty("driverclass"));
        System.out.println(syp.getProperty("jdbcurl"));
        System.out.println(syp.getProperty("username"));
        System.out.println(syp.getProperty("password"));
    }
}
