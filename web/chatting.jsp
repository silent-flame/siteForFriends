<%@ page import="ru.mysite.web.servlets.Controller.AuthorizationServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% String login = AuthorizationServlet.getLogin(request); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>Chat</title>
    <style type="text/css">
        .nickname{
            font-size: 40px;
            color: #2fda24;
        }
    </style>
    <%--<link rel="stylesheet" href="style.css">--%>
</head>
<body >
<table  border="2"  style="border-color: darkgreen; width: 100%; height: 30pt; margin: 5px" cellspacing='0' cellpadding='0'  >
    <tr>
        <td width='15%'>
            <img src="java.jpg" width="100%">
            <p align="center" class="nickname"> <%=login%></p>
        </td>
        <td>
            <table border='1' style="border-color: darkgreen;height: 100% ; width: 100%"  cellspacing='0' cellpadding='4' bgcolor="#7fffd4">
                <tr>
                    <td>

                        <iframe  style="height: 100% ;width: 100%; margin-bottom: 10px;border: medium"  src="/chatting" frameborder="0">Без плавающих фреймов</iframe>
                    </td>
                </tr>
                <tr>
                    <td height="25px">
                        <form action='chatting' method='post'>
                            <input type='text' name='message' size='100' placeholder="Введите сообщение..." autofocus>
                            <input type='submit' value='Отправить'>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>