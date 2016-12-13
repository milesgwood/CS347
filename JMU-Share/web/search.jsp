<%-- 
    Document   : search
    Created on : Dec 8, 2016, 6:21:30 PM
    Author     : recinocs
--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tlds/jstags.tld" prefix="mt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<mt:login_check/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" type="text/css" href="format.css">
    </head>
    <body>
        <div id="container">
            <div id="header"><jsp:include page='menubar.jsp'/></div>
            <div id="content"><div class="wrapper"><div id='topContent3'>
                        <h1>Search</h1>
                        <s:bean name="bean.SchoolList">
                            <s:form action="SearchAction">
                                <p><s:textfield name="title" placeholder="Title"/></p>
                                <p><s:textfield name="class_num" placeholder="Class Number"/></p>
                                <p><s:textfield name="class_name" placeholder="Class Name"/></p>
                                <p><s:select name="school" list="schoolList"/></p>
                                <p><s:submit name="submit" value="Search Results"/></p>
                            </s:form>
                        </s:bean>
                    </div>
                </div>
                <div class="wrapper">
                    <div id='bottomContent'>
                        <s:iterator value="results">
                            <s:url var="url" action="viewpost">
                                <s:param name="postId" value="%{id}"/>
                            </s:url>
                            <s:a href="%{url}"><s:property value="title"/></s:a>
                                <div class="floating-box">
                                <s:property value="notes_desc"/>
                                Title: <s:property value='title'/>
                                Class Name: <s:property value="class_name"/>
                                Class Number: <s:property value="class_num"/>
                                School: <s:property value="school"/>
                            </div>
                        </s:iterator>
                        <div class="logo">
                            <img src="image/James_MadisonU_seal.png" alt="James Madison University seal"/>
                        </div>
                    </div>
                </div>
            </div>
        <div id="footer"><jsp:include page='footer.jsp'/></div>
        </div>
    </body>
</html>