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
                <h4 class="card-title">Scheduled classroom details </h4>

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
                <a class="my-2 btn btn-outline-warning rounded-pill" type="button" data-toggle="modal"
                   data-target="#addModal">Schedule class
                </a>

                <table class="table table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th>Date</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Subject</th>
                        <th>Classroom</th>
                        <th>Lecturer</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${classrooms}" var="classrooms">
                        <tr>
                            <td>${classrooms.date}</td>
                            <td>${classrooms.startTime}</td>
                            <td>${classrooms.endTime}</td>
                            <td>${classrooms.subject}</td>
                            <td>${classrooms.className}</td>
                            <td>${classrooms.lecturer}</td>
                            <sec:authorize access="hasAnyAuthority('ADMIN','LECTURER')">
                                <td class="text-center">
                                    <a href="/class/removeClassroom/${classrooms.id}"><i class="fa fa-trash red"
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
<%@include file="addClassroom.jsp" %>

</body>
</html>
