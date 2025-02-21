
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
        <h3 class="text-center">List of Inscription</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Student</th>
                <th>Course</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="inscription" items="${inscriptions}">

                <tr>
                    <td><c:out value="${inscription.id}" /></td>
                    <td><c:out value="${inscription.studentId}" /></td>
                    <td><c:out value="${inscription.courseId}" /></td>
                    <td><c:out value="${inscription.inscDate}" /></td>

                    <td><a href="/inscription/edit-form?id=<c:out value='${inscription.id}' />" >Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="/inscription/delete?id=<c:out value='${inscription.id}' /> " data-toggle="modal" data-target="#modal">Delete</a></td>
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
