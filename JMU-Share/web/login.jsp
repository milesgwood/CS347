<%-- 
    Document   : login
    Created on : Dec 5, 2016, 2:52:32 PM
    Author     : recinocs
--%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="format.css">
    </head>
    <body>
        <%
          session.setAttribute("logged_in", false);
          session.setAttribute("userId", null);
          session.setAttribute("role", null);
        %>
        <div id="container">
            <div id="header">
                <jsp:include page='menubar.jsp'/>
            </div>

            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <div class="login">
                            <h4>
                                Please login to JMU-Share
                            </h4>
                            <div id="loginContent">
                            <s:form action="LoginAction" method="get">
                                <p><s:textfield name="username" placeholder="Username"/></p>
                                <p><s:password name="password" placeholder="Password"/></p>
                                <p><s:submit name="submit" value="Login"/></p>
                            </s:form></div>
                            <p><a href="register_user">Not registered? Sign up here!</a>
                            <br/><br/><br/><br/><br/><br/>
                        </div>
                    </div>
                </div>
                <div class="wrapper">
                    <div id='bottomContent'>
                        <div class="logo">
                            <img src="image/James_MadisonU_seal.png" alt="James Madison University seal"/>
                        </div>
                    </div>
                </div>
                <br>
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
