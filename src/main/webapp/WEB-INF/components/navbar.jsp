<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2/20/2025
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- Navbar -->
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="student/list" class="navbar-brand"> Enaa
                Management App </a>
        </div>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/student/list">Student <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/course/list">Course</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/inscription/list">Inscription</a>
            </li>
        </ul>
        <c:if test="${not empty sessionScope.user}">
            <div class="ml-auto text-white">
                <c:out value="${sessionScope.user.email}" />(
                    <a href="/user/logout" >Disconnect</a>
                )
            </div>

        </c:if>
    </nav>
</header>