<%-- 
    Document   : HomePage
    Created on : Jan 31, 2021, 10:36:50 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="counterClock.js"></script>
        <script type="text/javascript" src="lib/jasmine-1.3.1/jasmine.js"></script>
        <script type="text/javascript" src="lib/jasmine-1.3.1/jasmine-html.js"></script>
        <script type="text/javascript" src="lib/jquery.min.js"></script>
        <c:set var="subList" value="${sessionScope.SUBLIST}" ></c:set>
        <c:set var="username" value="${sessionScope.NAME}"></c:set>
            <title>Quiz Online</title>
        </head>
        <body>
            <h1>Quiz Online</h1>
            <font color="blue">Welcome, ${username} </font></br>
        <a href="GetQuizHistoryServlet">Quiz History</a>
        <c:if test="${not empty subList}">
            <form action="takeaQuiz">
                <h2>Select Subject: </h2>
                <select name="dbSubject">
                    <c:forEach var="sub" items="${subList}">
                        <option>${sub.subjectID}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Start Quiz" />
            </form>
        </c:if>

    </body>
</html>
