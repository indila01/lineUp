<html>
<head>

    <%@ include file="common/header.jsp" %>

    <title>Home</title>
</head>

<body>
<header class="header-blue">

    <%@ include file="adminNavBar.jsp" %>

    <div class="container hero">
        <img class="rounded mx-auto d-block hero-container-img" src="${pageContext.request.contextPath}/assets/img/time-management.png">
        <h1>Welcome to LineUp</h1>
        <p>Manage Your Time Table</p>
    </div>
</header>

<%@include file="common/footer.jsp" %>
</body>
</html>