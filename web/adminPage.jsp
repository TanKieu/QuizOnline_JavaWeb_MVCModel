<%-- 
    Document   : adminPage
    Created on : Jan 25, 2021, 9:36:10 PM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="shortcut icon" type="image/png" href="lib/jasmine-1.3.1/jasmine_favicon.png" >
        <link rel="stylesheet" type="text/css" href="lib/jasmine-1.3.1/jasmine.css">
        <script type="text/javascript" src="lib/jasmine-1.3.1/jasmine.js"></script>
        <script type="text/javascript" src="lib/jasmine-1.3.1/jasmine-html.js"></script>
        <script type="text/javascript" src="lib/jquery.min.js"></script>

        <script type="text/javascript" src="spec/SpecHelper.js"></script>
        <script type="text/javascript" src="spec/SimplePaginationSpec.js"></script>
    </head>
    <body>
        <c:set var="username" value="${sessionScope.NAME}" ></c:set>
        <c:set var="quesList" value="${sessionScope.QUESTION}"></c:set>
        <c:set var="updateErrors" value="${sessionScope.UPDATEERROR}"></c:set>
        <font color="blue">Welcome, ${username}</font> </br>
        <form action="logout">
            <input type="submit" value="Log Out" />
        </form>
        <a href="addQuestion.jsp">Add Question</a>
        <form action="searchQuestion">
            Question name:  <input type="text" name="txtQuestion" value="" />
            Status: <input type="checkbox" name="cbStatus" value="ON" checked="checked" />
            Subject <input type="text" name="txtSubject" value="" /></br>
            <input type="submit" value="Search" />
        </form>
        <c:if test="${not empty quesList}">
            <table border="1" class="selector">
                <thead>
                    <tr>
                        <th>Question ID</th>
                        <th>Question</th>
                        <th>A</th>
                        <th>B</th>
                        <th>C</th>
                        <th>D</th>
                        <th>Answer</th>
                        <th>createDate</th>
                        <th>status</th>
                        <th>Subject ID</th>
                    </tr>
                </thead>

                <tbody class="list-wrapper">

                    <c:forEach var="questionDTO" items="${quesList}">

                        <tr class="list-item">
                    <form action="updateQuestion">
                        <td>${questionDTO.qID}</td>
                        <td>
                            <input type="text" name="txtQuestion" value="${questionDTO.question}" />
                        </td>
                        <td>
                            <input type="text" name="txtA" value="${questionDTO.a}" />
                        </td>
                        <td>
                            <input type="text" name="txtB" value="${questionDTO.b}" />
                        </td>
                        <td>
                            <input type="text" name="txtC" value="${questionDTO.c}" />
                        </td>
                        <td>
                            <input type="text" name="txtD" value="${questionDTO.d}" />
                        </td>
                        <td>
                            <input type="text" name="txtAnswer" value="${questionDTO.conrrectAnswer}" />
                        </td>
                        <td>${questionDTO.createDate}</td>
                        <td>
                            <c:if test="${true == questionDTO.status}">
                                <input type="checkbox" name="cbStatus" value="ON" checked="checked" /> Active
                            </c:if> 
                            <c:if test="${false == questionDTO.status}">
                                <input type="checkbox" name="cbStatus" value="ON" /> Active
                            </c:if>                             
                        </td>
                        <td><input type="text" name="txtSubId" value="${questionDTO.subId}"/> 
                        </td>    
                        <input type="hidden" name="txtQuestionID" value="${questionDTO.qID}" /> 
                        <td>
                            <input type="submit" value="Update" />
                        </td>
                        <td>
                            <c:url var="urlRewriting" value="deleteQuestion">
                                <c:param name="pk" value="${questionDTO.qID}"/>
                            </c:url>
                            <a href="${urlRewriting}">Delete</a>
                        </td>
                        </tr>
                    </form>
                </c:forEach>

            </tbody>

        </table>
        <c:if test="${not empty updateErrors}">
            <h2>Update Errors:</h2>
            <c:if test="${not empty updateErrors.questionError}">
                <font color="red">${updateErrors.questionError} </font>
            </c:if></br>

            <c:if test="${not empty updateErrors.aError}">
                <font color="red">${updateErrors.aError} </font></br>
            </c:if>    
            <c:if test="${not empty updateErrors.bError}">
                <font color="red">${updateErrors.bError} </font></br>
            </c:if>         
            <c:if test="${not empty updateErrors.answerError}">
                <font color="red">${updateErrors.answerError} </font></br>
            </c:if>   
            <c:if test="${not empty updateErrors.subIdError}">
                <font color="red">${updateErrors.subIdError} </font> </c:if></br>
        </c:if>
        <div id="pagination-container"></div>
    </c:if>

    <script type="text/javascript" src="jquery.simplePagination.js"></script> 
    <script type="text/javascript" src="phantrang.js"></script>
    <style>
        div#pagination-container li {
            display: inline-block;
            padding: 20px;
            border: 1px solid;
        }
        div#pagination-container ul {
            margin: 0;
            padding: 0;
            text-align: center;
        }

    </style>
</body>
</html>

