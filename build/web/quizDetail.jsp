<%-- 
    Document   : quizDetail
    Created on : Feb 7, 2021, 10:48:55 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="answerList" value="${sessionScope.DETAIL}"></c:set> 
            <title>Quiz Detail</title>
            <link rel="shortcut icon" type="image/png" href="lib/jasmine-1.3.1/jasmine_favicon.png" >
            <link rel="stylesheet" type="text/css" href="lib/jasmine-1.3.1/jasmine.css">
            <script type="text/javascript" src="lib/jasmine-1.3.1/jasmine.js"></script>
            <script type="text/javascript" src="lib/jasmine-1.3.1/jasmine-html.js"></script>
            <script type="text/javascript" src="lib/jquery.min.js"></script>
            <script type="text/javascript" src="spec/SpecHelper.js"></script>
            <script type="text/javascript" src="spec/SimplePaginationSpec.js"></script> 
            <script type="text/javascript" src="counterClock.js"></script>
        </head>
        <body>
            <a href="quizHistory.jsp">Quiz History</a> </br>
            <a href="homePage.jsp">Take a Quiz</a>
        <c:if test="${not empty answerList}">
            <div class="list-wrapper">  
                <c:forEach var="question" items="${answerList}" varStatus="counter" >
                    <div class="selector list-item">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th> ${counter.count}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Question: ${question.key.question}</td>
                                </tr>
                                <tr>
                                    <td>A: ${question.key.a}</td>
                                </tr>
                                <tr>
                                    <td>B: ${question.key.b}</td>                        
                                </tr>
                                <tr>
                                    <td>C: ${question.key.c}</td>      
                                </tr>        
                                <tr>
                                    <td>D: ${question.key.d}</td>
                                </tr>
                            </tbody>
                        </table>
                                <h3>Your answer: ${question.value}</h3>
                                <h3>Correct answer: ${question.key.conrrectAnswer}</h3>
                    </div>
                </c:forEach>                
            </div>
            <div id="pagination-container"></div>
        </c:if>
        <script type="text/javascript" src="jquery.simplePagination.js"></script> 
        <script type="text/javascript" src="phanTable.js"></script>
        <style>
            div#pagination-container li {
                display: inline-block;
                padding: 12px;
                border: 1px solid;
            }
            div#pagination-container ul {
                margin: 0;
                padding: 0;
                text-align: center;
            }
            div.list-wrapper{
                padding-left: 37%;
                padding-bottom: 50px;
            }

        </style>        
    </body>
</html>
