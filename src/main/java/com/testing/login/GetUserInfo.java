package com.testing.login;

import com.test.sqlUtil.MysqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Classname ${NAME}
 * @Description 类型说明
 * @Date 2021/3/8 21:00
 * @Created by 小白sy
 */
@WebServlet(name = "GetUserInfo")
public class GetUserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        if(request.getSession().getAttribute("loginName")!=null){
            MysqlUtils sy=new MysqlUtils();
            sy.creatconnect();
            Map<String, String> userinfoMap = sy.getUserinfo(request.getSession().getAttribute("loginName").toString());
            String jsonResult="{";
            for(String key:userinfoMap.keySet()){
                if(!key.equals("id")&&!key.equals("password")){
                    jsonResult+="\""+key+"\":\""+userinfoMap.get(key)+"\",";
                }
            }
            jsonResult+="}";
            jsonResult=jsonResult.replaceAll(",}","}");
            response.getWriter().append(jsonResult);
        }
        else {
            response.getWriter().append("{\"status\":-7,\"msg\":\""+"您还没有登录，不能查询用户信息。"+"\"}");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().append("{\"status\":-8,\"msg\":\""+"不支持使用get方法进行用户查询。"+"\"}");
    }
}
