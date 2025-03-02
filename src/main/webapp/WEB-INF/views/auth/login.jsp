<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2/28/2025
  Time: 10:49 AM
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

<div class="row">
    <br>
<div class="container col-md-5 mt-5">
    <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-${sessionScope.messageType}" role="alert">
            <c:out value="${sessionScope.message}" />
        </div>
        <c:remove var="message" scope="session" />
        <c:remove var="messageType" scope="session" />
    </c:if>
    <div class="card">
        <div class="card-body">
            <form action="login" method="post">

                    <caption>
                        <h2>
                            Login
                        </h2>
                    </caption>


                    <fieldset class="form-group">
                        <label>email</label>
                        <input type="email"
                               class="form-control"
                               name="email" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label>
                        <input type="password"
                               class="form-control"
                               name="password" required>
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

</div>


</body>
</html>
