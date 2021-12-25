<div
        class="modal fade"
        id="editModal${students.id}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="manageGroupModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-centered " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">
                    Edit Student
                </h5>
                <button
                        type="button"
                        class="btn-close"
                        data-dismiss="modal"
                        aria-label="Close"></button>
            </div>


            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">


                        <div class="signup-form adduser">


                            <form method="POST" action="${pageContext.request.contextPath}/student/update">
                                <h2 class="text-center">Update Student Account</h2>

                                <input hidden id="${students.id}" name="studentId" value="${students.id}"/>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col">
                                            <input type="text" class="form-control"
                                                   value="${students.firstName}" name="first_name"
                                                   placeholder="First Name" required="required"
                                                   autofocus="autofocus"/>
                                        </div>
                                        <div class="col">
                                            <input type="text" class="form-control"
                                                   value="${students.lastName}" name="last_name"
                                                   placeholder="Last Name" required="required"
                                                   autofocus="autofocus"/>
                                        </div>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control"
                                           value="${students.username}" name="username"
                                           placeholder="Username" required="required"
                                           autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control"
                                           value="${students.email}" name="email"
                                           placeholder="Email" required="required"
                                           autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <select name="batch"   id="editBatch${students.id}" class="form-control form-select"
                                                 name="batch"
                                                 placeholder="batch"
                                                 required="required" autofocus="autofocus">
                                        <c:forEach items="${batches}" var="batches">
                                            <option value="${batches.batchCode}">${batches.batchCode}</option>
                                        </c:forEach>
                                    </select>
                                </div>


                                <div class="form-group">
                                    <button type="submit" class="btn btn-dark">Update Student</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>

<script>
    document.getElementById("editBatch${students.id}").value = "${students.batch}";
</script>