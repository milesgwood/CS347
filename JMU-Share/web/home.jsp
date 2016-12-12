<%-- 
    Document   : example_2sections
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tlds/jstags.tld" prefix="mt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<mt:login_check/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="format.css">
    </head>
    <body>
        <div id="container">
            <div id="header"><jsp:include page='menubar.jsp'/></div>
            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <h1>Your posts...</h1>
                        <% //Set Default session attribute userId for testing
                            if (session.getAttribute("userId") == null) {
                                session.setAttribute("userId", 1);
                            }
                        %>
                        <div id="post_display">
                            <s:iterator value="userPosts">
                                <%-- This creates the url to the post --%>
                                <s:url var="url" action="viewpost">
                                    <s:param name="postId" value="%{id}"/>
                                </s:url>

                                <%-- This creates the url to the author profile--%>
                                <s:url var="authorProfileLink" action="profile">
                                    <s:param name="userId" value="authorID"/>
                                </s:url>

                                <div class="floating-box">
                                    <%-- This is the actual title of the notes and the link --%>
                                    <div class="titleOfPost">
                                        <s:a href="%{url}"><s:property value="title"/></s:a>
                                    </div>
                                    <ul>
                                        <%-- Top of each comment includes the author username, linkm, and score. --%>
                                        <li class="buttons"><s:a href="%{authorProfileLink}"><s:property value="authorName"/></s:a></li>
                                        <li class="buttons"><s:property value='className'/></li>
                                        <li class="buttons">Delete</li>
                                    </ul>
                                            <div class="post" id="postId<s:property value='id'/>">
                                                <s:property value="notes_desc"/>
                                            </div>
                                    </div>
                                    <br/>
                            </s:iterator>
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
