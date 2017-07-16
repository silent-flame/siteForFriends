package ru.mysite.web.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySimpleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("windows-1251");
//        resp.getWriter().write("Answer from самого простого сервлета");
        resp.getWriter().write("<!DOCTYPE html>");
        resp.getWriter().write("<html>");
        resp.getWriter().write(" <head>");
        resp.getWriter().write("  <meta charset=\"UTF-8\">");
        resp.getWriter().write("  <title> Мы самые крутые френды </title>");
        resp.getWriter().write(" </head>");

        resp.getWriter().write(" <body>");
        resp.getWriter().write("    Answer от простого сервлета");
        resp.getWriter().write(" </body>");
    }
}