<div
        class="modal fade"
        id="addModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="manageGroupModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-centered " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">
                    Add Student
                </h5>
                <button
                        type="button"
                        class="btn-close"
                        data-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">

                    <div class="form-body">
                        <div class="signup-form adduser">
                            <form:form action="${pageContext.request.contextPath}/student/createStudent" method="POST"
                                       modelAttribute="registerUser">
                                <h2 class="text-center">Create a Student Account</h2>
                                <p class="text-center">Please fill in this form to create an account!</p>


                                <div class="form-group">
                                    <div class="row">
                                        <div class="col"><form:input path="firstName" type="text" class="form-control"
                                                                     name="first_name"
                                                                     placeholder="First Name" required="required"
                                                                     autofocus="autofocus"/>
                                            <form:errors path="firstName"/>

                                        </div>
                                        <div class="col"><form:input path="lastName" type="text" class="form-control"
                                                                     name="last_name"
                                                                     placeholder="Last Name" required="required"
                                                                     autofocus="autofocus"/></div>
                                    </div>
                                </div>

                                <div class="form-group">

                                    <form:input path="username" type="text" class="form-control" name="username"
                                                placeholder="Username"
                                                required="required" autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <form:input path="email" type="email" class="form-control" name="email"
                                                placeholder="Email"
                                                required="required" autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <form:select path="batch" type="text" class="form-control form-select" name="role"
                                                 placeholder="Role"
                                                 required="required" autofocus="autofocus">
                                        <c:forEach items="${batches}" var="batches">
                                            <option>${batches.batchCode}</option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="form-group">
<%--                                    <form:input path="password" type="password" class="form-control" name="password"--%>
<%--                                                placeholder="Password"--%>
<%--                                                required="required" autofocus="autofocus"/>--%>
                                    <p class="text-secondary">Password of the student will be the username</p>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-dark">Create Student</button>
                                </div>
                            </form:form>

                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>