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
                            <s:form id="login" action="login" method="get">
                                <p><s:textfield name="username" placeholder="Username"/></p>
                                <p><s:textfield name="password" placeholder="Password"/></p>
                                <p><s:submit name="submit" value="Login"/></p>
                            </s:form>
                            <p><a href="register_user">Not registered? Sign up here!</a>
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
