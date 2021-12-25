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
                    Add Batch
                </h5>
                <button
                        type="button"
                        class="btn-close"
                        data-dismiss="modal"
                        aria-label="Close"
                />
            </div>
            <div class="modal-body">

                <div class="signup-form">
                    <form:form action="${pageContext.request.contextPath}/batch/createBatch" method="POST"
                               modelAttribute="batch">
                        <h2 class="text-center">Create a Batch</h2>

                        <div class="form-group">
                            <form:label path="batchCode" class="form-label">Batch Code</form:label>
                            <form:input path="batchCode" type="tex" class="form-control" name="Batch Code"
                                        placeholder="Batch Code"
                                        required="required" autofocus="autofocus"/>
                        </div>
                        <div class="form-group">
                            <form:label path="course" class="form-label">Course</form:label>
                            <form:input path="course" type="text" class="form-control" name="Course"
                                        placeholder="Course"
                                        required="required" autofocus="autofocus"/>
                        </div>
                        <div class="form-group">
                            <form:label path="year" class="form-label">Year</form:label>
                            <form:input path="year" type="text" class="form-control" name="year" placeholder="Year"
                                        required="required" autofocus="autofocus"/>
                        </div>

                        <div class="form-group">
                            <form:label path="subjects" class="form-label">subjects</form:label>
                            <form:select multiple="multiple" path="subjects" type="text" class="form-control"
                                         name="subjects" placeholder="subjects"
                                         required="required" autofocus="autofocus">
                                <c:forEach items="${subjects}" var="subjects">
                                    <form:option value="${subjects.name}">${subjects.name}</form:option>
                                </c:forEach>
                            </form:select>

                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-dark">Create Batch</button>
                        </div>
                    </form:form>

                </div>
            </div>
        </div>
    </div>
</div>

