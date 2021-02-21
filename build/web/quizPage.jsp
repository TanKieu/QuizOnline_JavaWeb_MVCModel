<%-- 
    Document   : quizPage
    Created on : Feb 1, 2021, 12:35:59 AM
    Author     : winnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="quizList" value="${sessionScope.QUIZLIST}"></c:set> 
            <title>Quiz Page</title>
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
            <div>
                <span id="h">Giờ</span> :
                <span id="m">Phút</span> :
                <span id="s">Giây</span>
            </div>
        <c:if test="${not empty quizList}">
            <div class="list-wrapper">  
               <form action="submitQuiz">
                <c:forEach var="question" items="${quizList}" varStatus="counter" >
                    <div class="selector list-item">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th> ${counter.count}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Question: ${question.question}</td>
                                </tr>
                                <tr>
                                    <td>A: ${question.a}</td>
                                </tr>
                                <tr>
                                    <td>B: ${question.b}</td>                        
                                </tr>
                                <tr>
                                    <td>C: ${question.c}</td>      
                                </tr>        
                                <tr>
                                    <td>D: ${question.d}</td>
                                </tr>
                            </tbody>
                        </table>

                                    <tr>
                                    Answer: 
                                     <select name="${question.qID.trim()}">
                                        <option></option>
                                        <option>A</option>
                                        <option>B</option>
                                        <option>C</option>
                                        <option>D</option>
                                     </select>
                                    </tr>
                                
                    </div>
                </c:forEach>   
                   <input id="SubmitButton" type="submit" value="Finish"/>  <br/> <br/>
                </form>
            </div>
            <div id="pagination-container"></div>
        </c:if>
        <script type="text/javascript" src="jquery.simplePagination.js"></script> 
        <script type="text/javascript" src="phanTable.js"></script>
        <script>
            <c:set var="quizTime" value="${sessionScope.QUIZTIME}"></c:set>    
            start(${quizTime});</script>
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
