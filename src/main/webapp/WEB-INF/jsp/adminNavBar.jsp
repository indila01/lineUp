<%--
  Created by IntelliJ IDEA.
  User: indiladineth
  Date: 2021-05-03
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-light navbar-expand-md ">
    <div class="container-fluid"><a class="navbar-brand" href="/"
    >LineUp</a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                class="navbar-toggler-icon"></span></button>

        <div class="collapse navbar-collapse" id="navcol-1">
            <div class="mr-auto">

                <ul class="navbar-nav">
                    <sec:authorize access="hasAnyAuthority('ADMIN','LECTURER')">
                        <li class="nav-item"><a class="nav-link"
                                                href="${pageContext.request.contextPath}/student/viewStudents">Manage
                            Student</a></li>
                        <li class="nav-item"><a class="nav-link"
                                                href="${pageContext.request.contextPath}/batch/batches">Manage Batch</a>
                        </li>
                        <li class="nav-item"><a class="nav-link"
                                                href="${pageContext.request.contextPath}/lecturer/viewLecturers">Manage
                            Lecturers</a></li>
                        <li class="nav-item"><a class="nav-link"
                                                href="${pageContext.request.contextPath}/subject/viewSubjects">Manage
                            Subjects</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('STUDENT','ADMIN','LECTURER')">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/class/searchClassroom">View TimeTable</a></li>
                    </sec:authorize>

                </ul>


            </div>



            <div >
            <sec:authorize access="isAuthenticated()">
            <strong>  <a class="nav-link" href="${pageContext.request.contextPath}/getProfile">Hello <sec:authentication property="principal.username" />  </a></strong>
            </sec:authorize>
            </div>



            <a class="btn btn-primary action-button mx-2" role="button" href="${pageContext.request.contextPath}/logout">logout</a>


        </div>
    </div>
</nav>



