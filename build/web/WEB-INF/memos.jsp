<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.memoproject.model.Memo" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<f:view>
    <html>
        <head>
            <meta charset=UTF-8"/>
            <title>My-memo</title>
            <link rel="stylesheet" type="text/css" href="screen.css">
        </head>
        <body>
            <div id="container">   
            <div id="header">
                <p>
                    <b>My-Memo</b>- a simple web app for managing memos
                </p>
            </div>
            <div id="content">
                <h1>Add Memo:</h1>
                <form method="POST" action="memos">
                    <input type="text" name="memo" size="30"
                           placeholder="Enter your memo here"/>
                    <button type="submit" name="button" value="save">Add</button>
                    <c:if test="${!empty requestScope.err}">
                        <span style="color: red"><c:out value="${requestScope.err}"/></span>
                    </c:if>>
                    <h1>My Memos</h1>
                    <c:if test="${sessionScope.memos==null||sessionScope.memos.size()==0}">
                        <p>Please add some memos.</p>
                    </c:if>
                    <c:if test="${sessionScope.memos.size()>0}">
                    <table>
                        <tr>
                            <th>Memo</th>
                            <th>Saved</th>
                        </tr>
                        <c:forEach items="${sessionScope.memos}" var="memo">
                        <tr>
                            <td>
                                <c:out value="${memo.description}"/>
                            </td>
                            <td>
                                <c:out value="${memo.created}"/>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <br/>
                    <button type="submit" name="button" value="reset">Reset list</button>
                   </c:if>
                </form>
            </div>
            <div id="footer">
                <p>(c) 2015 Schiesser/Schmollinger, MIT license</p>
            </div>
    </div>
        </body>
    </html>
</f:view>
