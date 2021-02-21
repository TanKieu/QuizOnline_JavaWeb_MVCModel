<%-- 
    Document   : quizHistory
    Created on : Feb 7, 2021, 10:12:29 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz History</title>
        <c:set var="quizList" value="${sessionScope.RECORD}"></c:set>
        </head>
        <body>
            <h1>History</h1>
            <a href="homePage.jsp">Take a Quiz</a></br>
            <form action="findQuizHistory">
                <input type="text" name="txtSubId" value="enter subject ID" /><br/>
                <input type="submit" value="Find"  />
            </form>
        <c:url var="urlRewriting" value="findQuizHistory">
            <c:param name="txtSubId" value=""/>
        </c:url>
        <a href="${urlRewriting}">Show all</a>
        <c:if test="${not empty quizList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Quiz ID</th>
                        <th>Subject</th>
                        <th>Scores</th>
                    </tr>
                </thead>
                <c:forEach var="Quizdto" items="${quizList}" >         
                    <tbody>
                        <tr>
                            <td>${Quizdto.quizID}</td>
                            <td>${Quizdto.subID}</td>
                            <td>${Quizdto.scores}</td>
                            <td>
                                <c:url var="urlRewriting" value="getDetail">
                                    <c:param name="quizID" value="${Quizdto.quizID}"/>
                                </c:url>
                                <a href="${urlRewriting}">Detail</a>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
