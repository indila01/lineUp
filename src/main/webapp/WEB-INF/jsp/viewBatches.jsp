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

<div class="d-flex justify-content-center">
    <div class="my-5 col-lg-7 grid-margin stretch-card">
        <div class="card shadow p-2 mb-5 bg-body rounded">
            <div class="card-body">
                <h4 class="card-title">View Batch Details</h4>

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
                        Batch</a>
                </sec:authorize>

                <table class="table table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th>Batch Code</th>
                        <th>Course</th>
                        <th>Year</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${batches}" var="batches">
                        <tr>
                            <td>${batches.batchCode}</td>
                            <td>${batches.course}</td>
                            <td>${batches.year}</td>
                            <td >
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <a href="/batch/removeBatch/${batches.id}"><i class="fa fa-trash red"
                                                                                  aria-hidden="true"></i></a>
                                    <a type="button" title="Edit batch" class="btn" data-toggle="modal" data-target="#editModal${batches.id}">
                                        <i class="fas fa-edit"></i> </a>
                                </sec:authorize>
                                <a href="/subject/viewSubjectsByBatch/${batches.id}"><i class="fas fa-eye red"
                                                                                        style="color: rgba(100,100,100,0.71)"
                                                                                        aria-hidden="true"></i></a>
                                <a href="/student/viewStudentsByBatch/${batches.id}"><i class="fas fa-users"

                                                                                        aria-hidden="true"></i></a>

                            </td>


                        </tr>
                        <%@ include file="editBatch.jsp" %>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<%@include file="addBatch.jsp" %>

</body>
</html>
