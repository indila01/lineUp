<%--
  Created by IntelliJ IDEA.
  User: indiladineth
  Date: 2021-04-25
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Register</title>
<%@ include file="adminNavBar.jsp"%>

    <br>
    <br>
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">

<%--                <!-- success message -->--%>
<%--                <c:if message ="${param.success}">--%>
<%--                    <div class="alert alert-info">You've successfully registered--%>
<%--                        to our awesome app!</div>--%>

<%--                </c:if>--%>

                <h1>Registration</h1>

                <form:form action="${pageContext.request.contextPath}/guest/processSignup" method="POST" modelAttribute="signUpUser">
                    <div class="form-group">
                        <form:label path="firstName" class="control-label" for="firstName"> First Name </form:label>
                        <form:input path="firstName" id="firstName" class="form-control"
                               required="true" autofocus="autofocus" />
                    </div>

                    <div class="form-group">
                        <form:label path="lastName" class="control-label" for="lastName"> Last Name </form:label> <form:input
                            id="lastName" class="form-control" path="lastName"
                            required="true" autofocus="autofocus" />
                    </div>

                    <div class="form-group">
                        <form:label path="email" class="control-label" for="email"> Email </form:label> <form:input
                            id="email" class="form-control"  required="true" path="email"
                            autofocus="autofocus" />
                    </div>

                    <div class="form-group">
                        <form:label path="password" class="control-label" for="password"> Password </form:label> <form:input
                            id="password" class="form-control" type="password" path="password"
                           required="true" autofocus="autofocus" />
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Register</button>
                        <span>Already registered? <a href="/" >Login
								here</a></span>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
<%@include file="common/footer.jsp"%>
