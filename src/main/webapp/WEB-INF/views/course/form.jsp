<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2/20/2025
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Enaa</title>
    <head>
        <title>Enaa</title>
        <link  rel="stylesheet"
               href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
               integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
               crossorigin="anonymous">

    </head>
    <%@ include file="../../components/navbar.jsp" %>

    <br>

    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${course != null}">
                <form action="edit" method="post">
                    </c:if>
                    <c:if test="${course == null}">
                    <form action="new" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${course != null}">
                                    Edit Course
                                </c:if>
                                <c:if test="${course == null}">
                                    Add New Course
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${course != null}">
                            <input type="hidden" name="id" value="<c:out value='${course.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Name</label>
                            <input type="text"
                                   value="<c:out value='${course.name}' />" class="form-control"
                                   name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Description</label>
                            <input type="text"
                                   value="<c:out value='${course.description}' />" class="form-control"
                                   name="description" required>
                        </fieldset>


                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
            </div>
        </div>
    </div>
</head>
<body>

</body>
</html>
