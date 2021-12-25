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
    <div class="my-5 col-lg-5 grid-margin stretch-card">
        <div class="card shadow p-3 mb-5 bg-body rounded">
            <div class="card-body">
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

                <form:form action="${pageContext.request.contextPath}/changePassword" method="POST"
                           modelAttribute="user">

                    <h2 class="text-center">Profile</h2>
                    <div class="form-group">

                        <div class="row">
                            <div class="form-group col-md-6">
                                <form:label path="firstName">First Name</form:label>
                                <form:input path="firstName" type="text" class="form-control" name="firstName"
                                            placeholder="First Name" disabled="true"
                                            required="required" autofocus="autofocus"/>
                            </div>

                            <div class="form-group col-md-6">
                                <form:label path="lastName">Last Name</form:label>
                                <form:input path="lastName" type="text" class="form-control" name="lastName"
                                            placeholder="Last Name" disabled="true"
                                            required="required" autofocus="autofocus"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <form:label path="username">Username</form:label>
                                <form:input path="username" type="text" class="form-control" name="username"
                                            placeholder="Username" disabled="true"
                                            required="required" autofocus="autofocus"/>
                            </div>
                            <div class="form-group col-md-6">
                                <form:label path="email">Email</form:label>
                                <form:input path="email" type="text" class="form-control" name="email"
                                            placeholder="Email" disabled="true"
                                            required="required" autofocus="autofocus"/>
                            </div>

                        </div>
                        <div class="row">
                            <div class="form-group col">
                                <form:label path="batch">Batch</form:label>
                                <form:input path="batch" type="text" class="form-control" name="email"
                                            placeholder="Batch" disabled="true"
                                            required="required" autofocus="autofocus"/>
                            </div>

                        </div>

                        <div class="row">
                            <div class="form-group col">
                                <form:label path="password">Password</form:label>
                                <form:input path="password" type="password" class="form-control" name="password"
                                            placeholder="Password"
                                            required="required" autofocus="autofocus"/>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="form-group col mt-3">
                            <button type="submit" class="btn btn-dark">Update</button>
                        </div>
                    </div>
                </form:form>


            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>

</body>
</html>
