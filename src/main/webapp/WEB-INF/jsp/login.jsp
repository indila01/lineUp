<html>
<head>

    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

    <%@ include file="common/header.jsp" %>

    <title>Home</title>
</head>

<body>
<header>


</header>

<div class="h-100 align-items-center justify-content-center">
<section class="login-clean ">
    <form method="post" action="${pageContext.request.contextPath}/loginprocess">
        <h2 class="loginheader text-center">LineUp</h2>
        <c:if test= "${error!= null}">
            <p class="alert alert-danger">${error}</p>
        </c:if>

        <div class="illustration"><img class="login-logo" src="${pageContext.request.contextPath}/assets/img/time-management.png"></div>
        <div class="form-group"><input class="form-control" type="username" name="username" placeholder="Username"></div>
        <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Password"></div>
        <div class="login-btn text-center">
            <button class="btn btn-dark loginbtn" type="submit">Log In</button>
        </div>

    </form>
</section>
</div>
<%@include file="common/footer.jsp" %>
</body>
</html>