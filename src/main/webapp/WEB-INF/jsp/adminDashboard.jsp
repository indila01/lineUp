<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <%@ include file="common/header.jsp" %>

    <title>Admin Dashboard</title>
</head>

<body>
<header class="header-blue">

    <%@ include file="adminNavBar.jsp" %>


</header>


<h1>hello</h1>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCookie1">Cookies</button>

    <div class="signup-form">
            <form:form action="${pageContext.request.contextPath}/admin/register" method="POST" modelAttribute="registerUser">
            <h2 class="text-center">Create an Account</h2>
            <p class="text-center">Please fill in this form to create an account!</p>

            <div class="form-group">
                <div class="row">
                    <div class="col"><form:input path="firstName" type="text" class="form-control" name="first_name" placeholder="First Name" required="required" autofocus="autofocus"/></div>
                    <div class="col"><form:input path="lastName" type="text" class="form-control" name="last_name" placeholder="Last Name" required="required" autofocus="autofocus"/></div>
                </div>
            </div>
                <div class="form-group">
                    <form:input path="username" type="username" class="form-control" name="username" placeholder="Username" required="required" autofocus="autofocus"/>
                </div>
            <div class="form-group">
                <form:input path="email" type="email" class="form-control" name="email" placeholder="Email" required="required" autofocus="autofocus"/>
            </div>
                <div class="form-group">
                    <form:select path="role" type="text" class="form-control form-select" name="role" placeholder="Role" required="required" autofocus="autofocus">
                        <c:forEach items="${roles}" var="roles">
                        <option>${roles.name}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="form-group">
                    <form:select path="batch" type="text" class="form-control form-select" name="role" placeholder="Role" required="required" autofocus="autofocus">
                        <c:forEach items="${batches}" var="batches">
                            <option>${batches.batchCode}</option>
                        </c:forEach>
                    </form:select>
                </div>
            <div class="form-group">
                <form:input path="password" type="password" class="form-control" name="password" placeholder="Password" required="required" autofocus="autofocus"/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-dark">Sign Up</button>
            </div>
                    </form:form>

    </div>


<%--    <h1>Registration</h1>--%>

<%--    <form:form action="${pageContext.request.contextPath}/admin/processSignup" method="POST" modelAttribute="registerUser">--%>
<%--        <div class="form-group">--%>
<%--            <form:label path="firstName" class="control-label" for="firstName"> First Name </form:label>--%>
<%--            <form:input path="firstName" id="firstName" class="form-control"--%>
<%--                        required="true" autofocus="autofocus" />--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <form:label path="lastName" class="control-label" for="lastName"> Last Name </form:label> <form:input--%>
<%--                id="lastName" class="form-control" path="lastName"--%>
<%--                required="true" autofocus="autofocus" />--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <form:label path="email" class="control-label" for="email"> Email </form:label> <form:input--%>
<%--                id="email" class="form-control"  required="true" path="email"--%>
<%--                autofocus="autofocus" />--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <form:label path="password" class="control-label" for="password"> Password </form:label> <form:input--%>
<%--                id="password" class="form-control" type="password" path="password"--%>
<%--                required="true" autofocus="autofocus" />--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <button type="submit" class="btn btn-success">Register</button>--%>
<%--            <span>Already registered? <a href="/" >Login--%>
<%--								here</a></span>--%>
<%--        </div>--%>
<%--    </form:form>--%>
<%--</div>--%>
<%@include file="common/footer.jsp" %>
</body>
</html>
