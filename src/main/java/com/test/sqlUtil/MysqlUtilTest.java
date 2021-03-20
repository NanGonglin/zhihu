package com.test.sqlUtil;

import java.sql.SQLException;
import java.util.Map;

/**
 * @Classname MysqlUtilTest
 * @Description 类型说明
 * @Date 2021/3/8 22:32
 * @Created by 小白sy
 */
public class MysqlUtilTest {
    public static void main(String[] args)  {
        MysqlUtils sy=new MysqlUtils();
        sy.creatconnect();
        System.out.println(sy.getUserinfo("Roy"));
        Map<String, String> userinfoMap = sy.getUserinfo("Roy");
        String jsonResult="{";
        for(String key:userinfoMap.keySet()){
            if(!key.equals("id")&&!key.equals("password")){
                jsonResult+="\""+key+"\":\""+userinfoMap.get(key)+"\",";
            }
        }
        jsonResult+="}";
        jsonResult=jsonResult.replaceAll(",}","}");
        System.out.println(jsonResult);
    }
}
