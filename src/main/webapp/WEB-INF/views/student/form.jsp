<%--
  Created by IntelliJ IDEA.
  User: malak
  Date: 21/02/2025
  Time: 10:08
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



<body>

<%@ include file="../../components/navbar.jsp" %>

<br>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${student != null}">
            <form action="edit" method="post">
                </c:if>
                <c:if test="${student == null}">
                <form action="new" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${student != null}">
                                Edit Student
                            </c:if>
                            <c:if test="${student == null}">
                                Add New Student
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${student != null}">
                        <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>name</label>
                        <input type="text"
                               value="<c:out value='${student.name}' />" class="form-control"
                               name="name" required>
                    </fieldset>

                        <fieldset class="form-group">
                            <label>email</label>
                            <input type="text"
                                   value="<c:out value='${student.email}' />" class="form-control"
                                   name="email" required>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>birthdate</label>
                            <input type="text"
                                   value="<c:out value='${student.birthdate}' />" class="form-control"
                                   name="birthdate" required="required">

                            <fieldset class="form-group">
                                <label>course</label>
                                <select name="courses">
                                 <c:forEach var="course" items="${courses}">
                                     <option  value='<c:out value='${course.id}' />'>
                                         <c:out value='${course.name}' />
                                     </option>
                                 </c:forEach>
                                </select>
                        </fieldset>








                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>

