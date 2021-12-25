<%--
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
        <img class="rounded mx-auto d-block hero-container-img" src="${pageContext.request.contextPath}/assets/img/time-management.png">
        <h1>Welcome to LineUp</h1>
        <p>Manage Your Time Table</p>
    </div>
</header>

<div class="d-flex justify-content-center">
    <div class="my-5 col-lg-7 grid-margin stretch-card">
        <div class="card shadow p-2 mb-5 bg-body rounded">
            <div class="card-body">
                <h4 class="card-title">View Subject Details for batch ${batch}</h4>

                <table class="table table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th>Subject ID</th>
                        <th>Subject Name</th>
                        <th>Lecturer Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${subjects}" var="subjects">
                        <tr>
                            <td>${subjects.id}</td>
                            <td>${subjects.name}</td>
                            <td>${subjects.user}</td>
                            <td>
                                <a href="/subject/removeSubject/${subjects.id}"><i class="fa fa-trash red" aria-hidden="true"></i></a>
                                <a href="/class/viewClassrooms/${subjects.id}"><i class="fas fa-eye red" style="color: rgba(100,100,100,0.71)" aria-hidden="true"></i></a>
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
