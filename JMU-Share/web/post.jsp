<%-- 
    Document   : post
    Created on : Nov 9, 2016, 5:48:19 PM
    Author     : greatwmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="format.css">
        <style>
            .arrow-up {
                width: 0; 
                height: 0; 
                border-left: 15px solid transparent;
                border-right: 15px solid transparent;
                border-bottom: 15px solid green;
                margin-bottom: 3px;
            }

            .arrow-down {
                width: 0; 
                height: 0; 
                border-left: 15px solid transparent;
                border-right: 15px solid transparent;
                border-top: 15px solid #990000;
                margin-top: 3px;
            }


            .floating-box {
                display: inline-block;
                max-height: 60px;
                margin-bottom: 3px;
                border: 1px solid #dddddd;
                border-left: solid transparent;
                border-right: solid transparent;
                border-bottom: solid transparent;
                padding-top: 1em;
                padding-bottom: 1em;
            }

            .comment
            {
                padding-top: 2px;
                padding-bottom: 2px;
                display: inline-block;
            }

            .arrows {
                display: inline-block;
            }
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
                    var score = parseInt(inner,10) - 1;
                    middle.innerHTML = score;
            }

            function upVote(x) {
                    var middle = x.nextElementSibling;
                    var downArrow = middle.nextElementSibling;
                    var inner = middle.innerHTML; 
                    var score = parseInt(inner,10) + 1;
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
                        <h1>Cryptography Ciphers and Their Weaknesses</h1>
                        <h2>CS 457 Brett Tjaden - Fall 2016</h2>
                        <p>Notes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            Symmetric key systems - both sender and receiver have the same key
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                            If in the same row shift right or shift downNotes Begin Here --- Cryptography - designing systems so only certain people can see through the disguise
                        </p>
                    </div>
                </div>
                <br>
                <div class="wrapper">
                    <div id='bottomContent'>
                        <div class="comment">
                            <div class="arrows">
                                <div class="arrow-up" onclick="upVote(this)" onmouseenter="brightenUp(this)" onmouseleave="darkenUp(this)"></div>
                                <div>456</div>
                                <div class="arrow-down" onclick="downVote(this)" onmouseenter="brightenDown(this)" onmouseleave="darkenDown(this)"></div>
                            </div>
                            <div class="floating-box">hellohellohelafojshdfijhd</div>
                        </div>
                        <br>

                        <form action="comment" method="post">
                            <textarea style="width:100%" name="comments" id="comments"> Write comment here...</textarea>
                            <input type="submit" value="Submit Comment">
                        </form>
                    </div>
                </div>
            </div>
            <div id="footer">
                <jsp:include page='footer.jsp'/>
            </div>
        </div>
    </body>
</html>
