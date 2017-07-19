package ru.mysite.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserHandler extends HttpServlet {
    public static String domainAddress = "http://localhost/";
    private String login;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setIntHeader("Refresh", 1);
        login = getLogin(req);
        System.out.println("UserHandler begin. "+((login == null) ? "Truth null" : login));
        System.out.flush();
        if (login == null || login.equals("null")) {

            PrintWriter pr = resp.getWriter();
            pr.write("<!DOCTYPE html>" +
                    "<html lang='en'>" +
                    "<head>" +
                    "   <meta charset=\"windows-1251\">" +
                    "   <title>Add new user</title>" +
                    "</head>" +
                    "<body >");

            pr.write("<form action = '" + "/userhandler" + "' method = 'post'>" +
                    "<input type = 'text' name = 'login' size = '30' minlength = '6' maxlength = '20' placeholder = 'Write login'><br >" +
                    "<input type = 'submit' value = 'Add'>" +
                    "<input type = 'reset' value = 'Reset'>" +
                    "</form >");


            pr.write("</body >" +
                    "</html >");
            pr.flush();
            pr.close();
            System.out.println("Userhandler doGet; login = "+login);
        } else {
            System.out.println("UserHandler branch 'else' ");
            new ChattingServlet().doGet(req, resp);
/*            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY  );
            resp.setHeader("Location",domainAddress+"chatting");*/
        }
    }


    protected static String getLogin(HttpServletRequest request) {
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
        System.out.println("UserHandler getLogin; login = " + ((tmplogin == null) ? "truth null" : tmplogin));
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
