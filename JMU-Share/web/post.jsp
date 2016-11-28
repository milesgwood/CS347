<%-- 
    Document   : post
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>

<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="format.css">
        <style>

        </style>
        <script>
            function brightenUp(x) {
                x.style.borderBottom = "15px solid #66ff66";
            }

            function darkenUp(x) {
                x.style.borderBottom = "15px solid #008000";
            }

            function brightenDown(x) {
                x.style.borderTop = "15px solid #ff0000";
            }

            function darkenDown(x) {
                x.style.borderTop = "15px solid #990000";
            }

            function downVote(x) {
                var middle = x.previousElementSibling;
                var inner = middle.innerHTML;
                var score = parseInt(inner, 10) - 1;
                middle.innerHTML = score;
            }

            function upVote(x) {
                var middle = x.nextElementSibling;
                var downArrow = middle.nextElementSibling;
                var inner = middle.innerHTML;
                var score = parseInt(inner, 10) + 1;
                middle.innerHTML = score;
            }
        </script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page='menubar.jsp'/>
            </div>

            <div id="content">
                <div class="wrapper">
                    <div id='topContent'>
                        <h1>Title -- Cryptography Ciphers and Their Weaknesses</h1>
                        <h2>Endorse- <s:property value="post.getEndorse()"/></h2>
                        <h2>Rating- <s:property value="post.getRating()"/></h2>
                        <h2>Class Id- <s:property value="post.getClassId()"/></h2>
                        <h2>Author Id- <s:property value="post.getAuthorName()"/></h2>
                        <p><s:property value="post.getContentBody()"/></p>
                    </div>
                </div>
                <br>
                <div class="wrapper">
                    <div id='bottomContent'>
                        <div id="commentsSection">
                            <%!
                                String sesPostId;
                                String reqPostId;
                            %>
                            <%
                                if (request.getParameter("postId") != null) {
                                    reqPostId = request.getParameter("postId");
                                    session.setAttribute("postId", reqPostId);
                                } else {
                                    sesPostId = (String) session.getAttribute("postId");
                                    request.setAttribute("postId", sesPostId);
                                }
                            %>
                            <s:iterator  value="commentsList">
                                <div class="comment">
                                    <div class="arrows">
                                        <div class="arrow-up" onclick="upVote(this)" onmouseenter="brightenUp(this)" onmouseleave="darkenUp(this)"></div>
                                        <div><s:property value="score"/></div>
                                        <div class="arrow-down" onclick="downVote(this)" onmouseenter="brightenDown(this)" onmouseleave="darkenDown(this)"></div>
                                    </div>
                                    <div class="floating-box"><s:property value="comment"/><br>By Author <s:property value="commentAuthorName"/></div>
                                </div>
                                <br>
                            </s:iterator>
                        </div>
                        <s:form id="submitComment" action="submitComment" method="post">
                            <s:textarea style="width:100%" name="comment" id="comment" placeholder="Write comment here..."/>
                            <p>Here is the session - <s:property value="#session.postId"/></p>
                            <s:hidden name="postId" value="1"/>
                            <s:submit type="submit" value="submitComment"/>
                        </s:form>
                    </div>
                </div>
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
