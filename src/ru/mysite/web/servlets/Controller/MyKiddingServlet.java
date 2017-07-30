package ru.mysite.web.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MyKiddingServlet extends HttpServlet {
    private String message = "Здравствуйте!";
    HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("windows-1251");
        PrintWriter pr = resp.getWriter();
        pr.write("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "  <meta charset=\"UTF-8\">" +
                "  <title>Возьмите трубку</title>" +
                "</head>" +
                "<body>" +
                "<table align=\"center\">" +
                "<tr><td>" +
                "<div align=\"left\">" +
                message + "<br>" +
                "    <form action=\"" + req.getContextPath() + "\" method=\"post\">" +
                "      <input type=\"text\" name=\"message\" id=\"message\" size=\"40\"  placeholder=\"Введите ваше сообщение\">" +
                "      <input type=\"submit\" id=\"submit\" value=\"Отправить\">" +
                "      <input type=\"reset\" value=\"Сброс\">" +
                "    </form>" +
                "</div>" +
                "<a href="+ AuthorizationServlet.domainAddress+">На главную</a>" +
                "</td></tr>" +
                "</table>" +
                "</body>" +
                "</html>");
        pr.flush();
        pr.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession();
//        session.setMaxInactiveInterval(5);

        int numberOfMessage;
        if (session.getAttribute("numberOfMessage") == null)
            numberOfMessage = 2;
        else {
            numberOfMessage = (Integer) session.getAttribute("numberOfMessage");
            numberOfMessage = (numberOfMessage) % 5 + 1;
        }
        switch (numberOfMessage) {
            case 1: {
                message = "Здравствуйте!";
                break;
            }
            case 2: {
                message = "Это аптека?";
                break;
            }
            case 3: {
                message = "У вас есть что-нибудь от головы?";
                break;
            }
            case 4: {
                message = "А у вас есть харамбурум?";
                break;
            }
            case 5: {
                message = "А вы не подскажете адрес?";
                break;
            }
        }


        session.setAttribute("numberOfMessage", numberOfMessage);
        this.doGet(req, resp);
    }
}