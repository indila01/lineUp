<%--
  Created by IntelliJ IDEA.
  User: indiladineth
  Date: 2021-05-04
  Time: 11:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <%@ include file="common/header.jsp" %>
    <title>Title</title>
</head>
<body>
<header class="header-blue">

    <%@ include file="adminNavBar.jsp" %>
    <div class="container hero px-2">
        <img class="rounded mx-auto d-block hero-container-img" src="${pageContext.request.contextPath}/static/assets/img/time-management.png">
        <h1>Welcome to LineUp</h1>
        <p>Manage Your Time Table</p>
    </div>

</header>

<div class="d-flex justify-content-center">
    <div class="my-5 col-lg-10 grid-margin stretch-card">
        <div class="card shadow p-3 mb-5 bg-body rounded">
            <div class="card-body">
                <h4 class="card-title">User Details</h4>

                <table class="table table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Batch</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="users">
                        <tr>
                            <td>${users.firstName}</td>
                            <td>${users.lastName}</td>
                            <td>${users.username}</td>
                            <td>${users.email}</td>
                            <td>${users.role}</td>
                            <td>${users.batch}</td>
                            <td class="text-center">
                                <a href="/admin/removeUser/${users.id}"><i class="fa fa-trash red" aria-hidden="true"></i></a>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
</body>
</html>
