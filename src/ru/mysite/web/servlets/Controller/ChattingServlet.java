package ru.mysite.web.servlets.Controller;

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
        resp.setIntHeader("Refresh", 5);
        PrintWriter pr = resp.getWriter();
        login = AuthorizationServlet.getLogin(req);


        pr.write("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "  <meta charset='UTF-8' content='text/html' >" +
                "  <title>Chat</title>" +
                "    <link rel=\"stylesheet\" href=\"style.css\""+
                "</head>" +
                "<body>");


//                "                    Просмотр сообщений" +
        {
            int intNumberOfMessages = numberOfMessages.get();
            for (int i = 0; i < intNumberOfMessages; i++) {
                pr.write(messages.get(i) + "<br> ");
            }
        }


        pr.write("</body>" +
                "</html>");
        pr.flush();
        pr.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        login = AuthorizationServlet.getLogin(req);
        String message = req.getParameter("message");
        numberOfMessages.getAndIncrement();
        messages.add("<b class='nickname'>" + login + "</b> : " + message);
        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        resp.setHeader("Location","chatting.jsp");
    }
}
