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
        <img class="rounded mx-auto d-block hero-container-img"
             src="${pageContext.request.contextPath}/assets/img/time-management.png">
        <h1>Welcome to LineUp</h1>
        <p>Manage Your Time Table</p>
    </div>
</header>

<div class="d-flex h-75 justify-content-center">
    <div class="my-5 col-lg-8 grid-margin stretch-card">
        <div class="card shadow p-3 mb-5 bg-body rounded">
            <div class="card-body">
                <h4 class="card-title">Lecturer Details</h4>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error}
                    <button type="button" class="btn-close" data-dismiss="alert" aria-label="Close">

                    </button>
                </div>
                <% }%>

                <%
                    String success = (String) request.getAttribute("success");
                    if (success != null) { %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${success}
                    <button type="button" class="btn-close" data-dismiss="alert" aria-label="Close">

                    </button>
                </div>
                <% }%>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <a class="my-2 btn btn-outline-warning rounded-pill" type="button" data-toggle="modal"
                       data-target="#addModal">Create
                        Lecturer</a>
                </sec:authorize>
                <table class="table table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Username</th>
                        <th>Email</th>
                        <sec:authorize access="hasAuthority('ADMIN')">
                            <th>Action</th>
                        </sec:authorize>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lecturers}" var="lecturers">
                        <tr>
                            <td>${lecturers.firstName}</td>
                            <td>${lecturers.lastName}</td>
                            <td>${lecturers.username}</td>
                            <td>${lecturers.email}</td>
                            <sec:authorize access="hasAuthority('ADMIN')">
                                <td class="text-center">
                                    <a href="/lecturer/removeLecturer/${lecturers.id}"><i class="fa fa-trash red"
                                                                                      aria-hidden="true"></i></a>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<%@include file="addLecturer.jsp" %>
</body>
</html>
