<%-- 
    Document   : register.jsp
    Created on : Dec 5, 2016, 4:37:24 PM
    Author     : recinocs
--%>

<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="format.css">
    </head>
    <body>
        <%
            session.setAttribute("logged_in", false);
            session.setAttribute("user", null);
            session.setAttribute("role", null);
        %>
        <div id="container">
            <div id="header">
                <jsp:include page='menubar_register.jsp'/>
            </div>

            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <div class="centeredForm">
                        <h3 class="welcome">Welcome to JMU-Share!</h3>
                        <p class="register">Please make sure you fill out every field.</p>
                        <div class="register_info">
                            <s:bean name="bean.SchoolList">
                                <s:form action="RegisterAction">
                                    <s:textfield name="name" placeholder="Name"/>
                                    <s:textfield name="email" placeholder="Email"/>
                                    <s:textfield name="username" placeholder="Username"/>
                                    <s:password name="password" placeholder="Password"/>
                                    <s:password name="confirm" placeholder="Confirm Password"/>
                                    <s:select name="school" list="schoolList"/>
                                    <s:submit name="submit" value="Sign me up!"/>
                                    <s:hidden name="isProfessor" value="0"/>
                                </s:form>
                            </s:bean><br/><br/>
                            <s:a href="sign_in">Already got in account? Sign in!</s:a>
                        </div>
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
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
