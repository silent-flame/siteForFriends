package ru.mysite.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ChattingServlet extends HttpServlet {
    private String login = "";
    private static List<String> messages = new CopyOnWriteArrayList<>();
    private static AtomicInteger numberOfMessages = new AtomicInteger(0);

    @Override
    public void init() throws ServletException {
        super.init();
        messages.add("Chat created");
        numberOfMessages.getAndIncrement();

        System.out.println("Init ChattingServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter pr = resp.getWriter();
        login = UserHandler.getLogin(req);
        pr.write("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "  <meta charset='UTF-8'>" +
                "  <title>Chat</title>" +
                "</head>" +
                "<body>");
        pr.write("<table border='5' width='100%' cellspacing='0' cellpadding='0'>" +
                "    <tr>" +
                "      <td width='15%'>" + login + "</td>" +
                "    <td>" +
                "          <table border='2' width='100%' cellspacing='0' cellpadding='4'>" +
                "              <tr>" +
                "                 <td>");


//                "                    Просмотр сообщений" +
        {
            int intNumberOfMessages = numberOfMessages.get();
            for (int i = 0; i < intNumberOfMessages; i++) {
                pr.write(messages.get(i) + "<br>");
            }
        }


        pr.write("                 </td>" +
                "                 </tr>" +
                "                 <tr>" +
                "                 <td>" +
                "                     <form action='chatting' method='post'>" +
                "                         <input type='text' name='message' size='40'>" +
                "                         <input type='submit' value='Send'>" +
                "                     </form>" +
                "                 </td>" +
                "              </tr>" +
                "          </table>" +
                "    </td>" +
                "    </tr>" +
                "    </table>");
        pr.write("</body>" +
                "</html>");
        pr.flush();
        pr.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        login = UserHandler.getLogin(req);
        String message = req.getParameter("message");
        numberOfMessages.getAndIncrement();
        messages.add("<b>" + login + "</b> : " + message);
        doGet(req, resp);
    }


}
