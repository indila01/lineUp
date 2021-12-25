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
                    Add classroom
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
                    <form:form action="${pageContext.request.contextPath}/class/createClassroom" method="POST"
                               modelAttribute="classroom">
                        <h4 class="text-center py-3">Schedule a classroom </h4>

                        <div class="form-group">

                            <form:input path="className" type="text" class="form-control"
                                        name="class_name"
                                        placeholder="Class Name" required="required"
                                        autofocus="autofocus"/>


                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col">
                                    <form:input path="startTime" type="time" class="form-control" name="start_time"
                                                placeholder="Start time"
                                                required="required" autofocus="autofocus"/>
                                </div>
                                <div class="col">

                                    <form:input path="endTime" type="time" class="form-control" name="end_time"
                                                placeholder="End time"
                                                required="required" autofocus="autofocus"/>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">

                            <form:input path="date" type="date" class="form-control"
                                        name="date"
                                        placeholder="Date" required="required"
                                        autofocus="autofocus"/>
                        </div>

                        <div class="form-group">
                            <form:select path="subject" type="text" class="form-control form-select" name="subjects"
                                         placeholder="subjects"
                                         required="required" autofocus="autofocus">

                                <c:forEach items="${subjects}" var="subjects">
                                    <option>${subjects.name}</option>
                                </c:forEach>

                            </form:select>

                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-dark">Schedule</button>
                        </div>
                    </form:form>

                </div>
            </div>
        </div>
    </div>
</div>
