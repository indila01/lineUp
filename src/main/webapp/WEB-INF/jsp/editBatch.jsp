<div
        class="modal fade"
        id="editModal${batches.id}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="manageGroupModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-centered " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">
                    Edit batch
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


                            <form method="POST" action="${pageContext.request.contextPath}/batch/update">
                                <h2 class="text-center">Update batch </h2>

                                <input hidden id="${batches.id}" name="batchId" value="${batches.id}"/>


                                <div class="form-group">
                                    <input type="text" class="form-control"
                                           value="${batches.batchCode}" name="batchCode"
                                           placeholder="batch code" required="required"
                                           autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control"
                                           value="${batches.course}" name="course"
                                           placeholder="course" required="required"
                                           autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control"
                                           value="${batches.year}" name="year"
                                           placeholder="year" required="required"
                                           autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <select multiple="multiple" id="editsubject${batches.id}" type="text" class="form-control"
                                                 name="subjects" placeholder="subjects"
                                                 required="required" autofocus="autofocus">
                                        <c:forEach items="${subjects}" var="subjects">
                                            <option value="${subjects.name}">${subjects.name}</option>
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
    document.getElementById("editBatch${batches.id}").value = "${batches.batchCode}";
</script>