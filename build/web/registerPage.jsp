<%-- 
    Document   : registerPage
    Created on : Jan 25, 2021, 5:20:24 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <c:set var="errors" value="${requestScope.REGISTER_ERRORS}"></c:set>
        <form action="register" method="POST">
            Email <input type="text" name="txtEmail" value="" /> </br>
        <c:if test="${not empty errors.emailError}">
            <font color="red">
                ${errors.emailError}</br>
            </font>
        </c:if>
            Password <input type="password" name="txtPassword" value="" /> </br>
        <c:if test="${not empty errors.passwordError}">
            <font color="red">
                ${errors.passwordError}</br>
            </font>
        </c:if>            
            Confirm password <input type="password" name="txtConfirm" value="" /></br>
        <c:if test="${not empty errors.confirmNotMatch}">
            <font color="red">
                ${errors.confirmNotMatch}</br>
            </font>
        </c:if>             
            Name <input type="text" name="txtName" value="" /> </br>
            Role <select name="dlRole">
                <option selected="true">Student</option>
                <option>Teacher</option>
            </select> </br>
            <input type="submit" value="Submit" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
