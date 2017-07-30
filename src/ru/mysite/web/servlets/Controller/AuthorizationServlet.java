package ru.mysite.web.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    public static String domainAddress = "http://localhost/";
    private String login;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        login = getLogin(req);

        System.out.println("Authorization of user: " + login);
        System.out.flush();
        if (login == null || login.equals("null")) {
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            resp.setHeader("Location","/authorization.html");
        } else {
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            resp.setHeader("Location",domainAddress+"chatting.jsp");
//            resp.setHeader("Location","/chatting.jsp");

        }
    }


    public static String getLogin(HttpServletRequest request) {
        String tmplogin = null;
        if (request.getSession().getAttribute("login") != null)
            tmplogin = (String) request.getSession().getAttribute("login");
        if (tmplogin == null) {
            Cookie[] cookies = request.getCookies();
            if ((cookies != null) && (cookies.length > 0))
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("login"))
                        tmplogin = cookie.getValue();
                }
        }

        return tmplogin;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("UserHandler doPost");
        login = req.getParameter("login");
        req.getSession().setAttribute("login", login);
        Cookie cookieLogin = new Cookie("login", login);
        cookieLogin.setMaxAge(604800);//Устанавливается максимальный срок хранения куков - 7 суток
        resp.addCookie(cookieLogin);
        System.out.println("user " + login + " added.");
        doGet(req, resp);
    }
}
