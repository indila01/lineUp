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
                    Add Lecturer
                </h5>
                <button
                        type="button"
                        class="btn-close"
                        data-dismiss="modal"
                        aria-label="Close"
                />
            </div>
            <div class="modal-body">


                <div class="signup-form adduser">
                    <form:form action="${pageContext.request.contextPath}/lecturer/createLecturer" method="POST"
                               modelAttribute="registerUser">
                        <h2 class="text-center">Create a Lecturer Account</h2>
                        <p class="text-center">Please fill in this form to create an account!</p>

                        <div class="form-group">
                            <div class="row">
                                <div class="col"><form:input path="firstName" type="text" class="form-control"
                                                             name="first_name"
                                                             placeholder="First Name" required="required"
                                                             autofocus="autofocus"/></div>
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
                            <form:input path="email" type="email" class="form-control" name="email" placeholder="Email"
                                        required="required" autofocus="autofocus"/>
                        </div>
                        <p class="text-secondary">Password of the lecturer will be the username</p>


                        <div class="form-group">
                            <button type="submit" class="btn btn-dark">Create Lecturer</button>
                        </div>
                    </form:form>

                </div>
            </div>
        </div>
    </div>
</div>
