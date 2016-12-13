<%-- 
    Document   : example_2sections
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tlds/jstags.tld" prefix="mt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<mt:login_check/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
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
                        <div class="profile_image">
                        <img src="image/default_profile.png" alt="Default Image" height="250" width="250">
                        </div>
                        <div class="profile_info">
                        <br/><br/>
                        <p3 class="thick">Name: <s:property value='user.getName()'/></p3><br/>
                        <p3 class="thick">Username: <s:property value='user.getUsername()'/></p3><br/>
                        <p3 class="thick">Email: <s:property value='user.getEmail()'/></p3><br/>
                        <p3 class="thick">School: <s:property value='school.getSchoolName()'/></p3><br/><br/>
                        </div>
                        <a class="edit_link" href="edit_profile">Edit Profile</a>
                        <div style="clear:both"></div>
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
