package com.testing.login;

import com.mysql.cj.Session;
import com.test.sqlUtil.MysqlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname ${NAME}
 * @Description 类型说明
 * @Date 2021/3/4 21:47
 * @Created by 小白sy
 */
@javax.servlet.annotation.WebServlet(name = "Login")
public class Login extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符串的编码，解决乱码显示问题
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
//        response.getWriter().append("post方法被调用了。");

        //获取输入的值
        String user = request.getParameter("username");
        String pwd = request.getParameter("password");

        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(30);

        Cookie[] cookies=request.getCookies();
        String sessionid=session.getId();

        if (user != null && user.length() > 0 && pwd != null && pwd.length() > 0) {
            if (user.length() > 1 && user.length() < 17 && pwd.length() > 1 && pwd.length() < 17) {

                Pattern specialword = Pattern.compile("[^A-Za-z0-9_\\-\\u4e00-\\u9fa5\\w-_]");
                Matcher specialuser = specialword.matcher(user);
                Matcher specialpwd = specialword.matcher(pwd);
                if (!specialuser.find() && !specialpwd.find()) {
                    MysqlUtils symysql = new MysqlUtils();
                    try {
                        symysql.creatconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().append("数据库连接失败");
                    }
                    if (symysql.prochecklogin(user, pwd)) {
//                if("Roy".equals(user)&&"123456".equals(pwd)){
                        response.getWriter().append("{\"status\":0,\"msg\":\"恭喜您，登录成功\"}");
                        session.setAttribute("loginName", user);
                        Cookie sessioncookie=new Cookie("JSESSIONID",sessionid );
                        sessioncookie.setMaxAge(30);
                        response.addCookie(sessioncookie);
                    } else {
                        response.getWriter().append("{\"status\":0,\"msg\":\"用户名或密码错误\"}");
                    }
                } else {
                    response.getWriter().append("{\"status\":0,\"msg\":\"用户名密码不能包含特殊字符\"}");
                }
            } else {
                response.getWriter().append("{\"status\":0,\"msg\":\"用户名密码长度需要为3-16位\"}");
            }
        } else {
            response.getWriter().append("{\"status\":0,\"msg\":\"用户名密码不能为空\"}");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
//        response.getWriter().append("get方法被调用了。");

        String user = request.getParameter("username");
        String pwd = request.getParameter("password");

        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(30);

        Cookie[] cookies=request.getCookies();
        String sessionid=session.getId();

        if (user != null && user.length() > 0 && pwd != null && pwd.length() > 0) {
            if (user.length() > 1 && user.length() < 17 && pwd.length() > 1 && pwd.length() < 17) {

                Pattern specialword = Pattern.compile("[^A-Za-z0-9_\\-\\u4e00-\\u9fa5\\w-_]");
                Matcher specialuser = specialword.matcher(user);
                Matcher specialpwd = specialword.matcher(pwd);
                if (!specialuser.find() && !specialpwd.find()) {
                    MysqlUtils symysql = new MysqlUtils();
                    try {
                        symysql.creatconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().append("数据库连接失败");
                    }
                    if (symysql.prochecklogin(user, pwd)) {
//                if("Roy".equals(user)&&"123456".equals(pwd)){
                        response.getWriter().append("{\"status\":0,\"msg\":\"恭喜您，登录成功\"}");
                        session.setAttribute("loginName", user);
                        Cookie sessioncookie=new Cookie("JSESSIONID",sessionid );
                        sessioncookie.setMaxAge(30);
                        response.addCookie(sessioncookie);
                    } else {
                        response.getWriter().append("{\"status\":0,\"msg\":\"用户名或密码错误\"}");
                    }
                } else {
                    response.getWriter().append("{\"status\":0,\"msg\":\"用户名密码不能包含特殊字符\"}");
                }
            } else {
                response.getWriter().append("{\"status\":0,\"msg\":\"用户名密码长度需要为3-16位\"}");
            }
        } else {
            response.getWriter().append("{\"status\":0,\"msg\":\"用户名密码不能为空\"}");
        }
    }
}
