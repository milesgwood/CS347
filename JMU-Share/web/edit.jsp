<%-- 
    Document   : edit
    Created on : Dec 9, 2016, 9:28:37 AM
    Author     : recinocs
--%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tlds/jstags.tld" prefix="mt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<mt:login_check/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
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
                        <br/><br/>
                        <p5>Any fields left blank will not be changed.</p5>
                        <div class="edit_form">
                            <s:form action="editProfile">
                                <p><s:textfield name="name" placeholder="Name"/></p>
                                <p><s:textfield name="email" placeholder="Email"/></p>
                                <p><s:textfield name="username" placeholder="Username"/></p>
                                <p><s:password name="password" placeholder="New Password"/></p>
                                <p><s:password name="confirm" placeholder="Confirm New Password"/></p>
                                <p><s:submit name="submit" value="Save"/></p>
                                <p><s:submit name="cancel" action="cancelEdit" value="Cancel"/></p>
                            </s:form>
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