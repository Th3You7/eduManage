<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2/20/2025
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Enaa</title>
    <link  rel="stylesheet"
           href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
           integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
           crossorigin="anonymous">

</head>
<body>

<%@ include file="../../components/navbar.jsp" %>

<br>

<div class="row">
    <div class="container">

        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-${sessionScope.messageType}" role="alert">
                <c:out value="${sessionScope.message}" />
            </div>
            <c:remove var="message" scope="session" />
            <c:remove var="messageType" scope="session" />
        </c:if>
        <h3 class="text-center">List of Employees</h3>
        <hr>
        <div class="container text-left pl-0">
            <a href="/course/new-form" class="btn btn-success">Add
                New Course</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="course" items="${courses}">

                <tr>
                    <td><c:out value="${course.id}" /></td>
                    <td><c:out value="${course.name}" /></td>
                    <td><c:out value="${course.description}" /></td>

                    <td><a href="/course/edit-form?id=<c:out value='${course.id}' />" >Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="/course/delete?id=<c:out value='${course.id}' /> " data-toggle="modal" data-target="#modal">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
        <!-- Modal -->
    </div>
</div>
</body>
</html>
