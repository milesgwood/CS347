<%-- 
    Document   : example_2sections
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>

<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tlds/jstags.tld" prefix="mt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<mt:login_check/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="format.css">
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page='menubar.jsp'/>
            </div>       
            <% //Set Default session attribute userId for testing
                if (session.getAttribute("userId") == null) {
                    session.setAttribute("userId", 1);
                }
            %>
            <div id="content">
                <div class="wrapper">
                    <div id='topContent2'>
                        <h1>Upload your notes</h1>
                        <s:bean name="bean.ClassList">
                        <s:form action="uploadToServer"  method="post"  enctype="multipart/form-data">
                            <s:textfield name="title" placeholder="Title or Subject"/>
                            <s:textfield name="notesDesc" placeholder="Description of your Notes"/>
                            <s:select id="className" name="className" list="alphaOrderedClasses"/>
                            <s:file name="upload"/>
                            <s:file name="upload"/>
                            <s:file name="upload"/>
                            <s:textarea style="width:100%;height:150px" name="body" placeholder="Enter text here..."/>
                            <s:submit/>
                        </s:form>
                        </s:bean>
                        <s:iterator value="upload" var="u">
                            <s:property value="u"/><br/>
                        </s:iterator>
                        <s:iterator value="uploadContentType" var="ct">
                            <s:property value="ct"/><br/>
                        </s:iterator>
                        <s:iterator value="uploadFileName" var="fn">
                            <s:property value="fn"/><br/>
                        </s:iterator>
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
