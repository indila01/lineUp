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
    <div class="my-5 col-lg-6 grid-margin stretch-card">
        <div class="card shadow p-3 mb-5 bg-body rounded">
            <div class="card-body">
                <sec:authorize access="hasAuthority('LECTURER')">
                    <a href="${pageContext.request.contextPath}/class/viewClassroomsByLecturer" role="button"
                       type="submit" class="btn btn-dark ">View my classrooms</a>
                </sec:authorize>
                <sec:authorize access="hasAuthority('STUDENT')">
                    <a href="${pageContext.request.contextPath}/class/viewClassroomsByBatch" role="button" type="submit"
                       class="btn btn-dark ">View my classrooms</a>
                </sec:authorize>


                <form:form action="${pageContext.request.contextPath}/class/searchClassroom" method="POST" modelAttribute="search">
                    <h2 class="text-center mt-4">View Timetable</h2>

                    <div class="form-group">

                        <div class="row">
                            <div class="col-md-5 mx-auto">
                                <form:select path="batch" type="text" class="form-control form-select" name="role"
                                        placeholder="Role"
                                        required="required" autofocus="autofocus">
                                    <c:forEach items="${batches}" var="batches">
                                        <option>${batches.batchCode}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>

                        <div class="row mt-2">
                            <div class="col-md-5 mx-auto">
                                <form:input path="fromDate" type="date" class="form-control" name="fromDate"
                                       placeholder="From Date"
                                       required="required" autofocus="autofocus"/>
                            </div>
<%--                            <div class="col-md-5 mx-auto">--%>

<%--                                <input type="date" class="form-control" name="toDate"--%>
<%--                                       placeholder="To Date"--%>
<%--                                       required="required" autofocus="autofocus"/>--%>
<%--                            </div>--%>


<%--                        </div>--%>
                    </div>

                    <div class="form-group ">
                        <div class="row mt-2">
                            <div class="col text-center">


                                <button type="submit" class="btn btn-dark ">Search</button>
                            </div>
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
