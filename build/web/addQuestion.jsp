<%-- 
    Document   : addQuestion
    Created on : Jan 26, 2021, 6:55:19 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Question</title>
    </head>
    <body>
        <c:set var="errors" value="${sessionScope.CREATEERRORS}"></c:set>
        <h1>Add New Question</h1>
        <form action="addQuestion">
            Question Id: <input type="text" name="txtQuesID" value="" /></br>
            <c:if test="${not empty errors.questionIDError}">
                <font color="red">${errors.questionIDError}</font></br>
            </c:if>
            Question: <input type="text" name="txtQuestion" value="" /></br>
            <c:if test="${not empty errors.questionError}">
                <font color="red">${errors.questionError}</font></br>
            </c:if>
            Answer A: <input type="text" name="txtA" value="" /></br>
            <c:if test="${not empty errors.aError}">
                <font color="red">${errors.aError}</font></br>
            </c:if>            
            Answer B: <input type="text" name="txtB" value="" /></br>
            <c:if test="${not empty errors.bError}">
                <font color="red">${errors.bError}</font></br>
            </c:if>             
            Answer C: <input type="text" name="txtC" value="" /></br>
            Answer D: <input type="text" name="txtD" value="" /></br>
            Correct Answer : <input type="text" name="txtAnswer" value="" /></br>
            <c:if test="${not empty errors.answerError}">
                <font color="red">${errors.answerError}</font></br>
            </c:if>              
            Subject Id: <input type="text" name="txtSubId" value="" /></br>
            <c:if test="${not empty errors.subIdError}">
                <font color="red">${errors.subIdError}</font></br>
            </c:if>    
            Status : <input type="checkbox" name="cbStatus" value="ON" checked="checked" /></br>
            <input type="submit" value="Add" />   
        </form>
    </body>
</html>
