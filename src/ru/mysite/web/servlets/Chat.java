package ru.mysite.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Chat extends HttpServlet {
    private String login="";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("windows-1251");
        PrintWriter pr = resp.getWriter();
        login=(String) req.getSession().getAttribute("login");
        if (login==null) login="";
        pr.write("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "  <meta charset='UTF-8'>" +
                "  <title>Чат</title>" +
                "</head>" +
                "<body>" +
                "  <form action='' method='post'>" +
                "      Login<input type='text' name='login'>" +
                "           <input type='submit' value='Отправить'>" +
                "           <input type='reset' value='Сброс'>" +
                "  </form>" +
                "login = "+login+
                "</body>" +
                "</html>");


        pr.flush();
        pr.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("login");
        req.getSession().setAttribute("login",login);
        doGet(req, resp);
    }


}
