package com.testing.login;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Description 类型说明
 * @Date 2021/3/8 21:00
 * @Created by 小白sy
 */
@WebServlet(name = "Logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session=request.getSession();
        if(session.getAttribute("loginName")!=null){
            String loginName=session.getAttribute("loginName").toString();
            response.getWriter().append("{\"status\":2,\"msg\":\"" + "注销"+loginName+"成功。" + "\"}");
        }
        else{
            response.getWriter().append("{\"status\":2,\"msg\":\"" + "未登录，不能注销。" + "\"}");
        }
        session.invalidate();
    }
}
