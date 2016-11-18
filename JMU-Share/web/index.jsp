<%-- 
    Document   : index.jsp
    Created on : Nov 18, 2016, 9:32:47 AM
    Author     : greatwmc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ page import="model.DBHandler" %>
   
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            DBHandler.createTables();
            DBHandler db = new DBHandler();

            Comment c = new Comment(1,2,"Find This Comment");
            db.insertComment(c);
            ArrayList<Comment> comments = db.getPostComments(2);
            comments.contains(c);
        %>
    </body>
</html>
