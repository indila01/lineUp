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
                    Add Subject
                </h5>
                <button
                        type="button"
                        class="btn-close"
                        data-dismiss="modal"
                        aria-label="Close"
                />
            </div>
            <div class="modal-body">

                <div class="form-body">
                    <div class="signup-form">
                        <form:form action="${pageContext.request.contextPath}/subject/createSubject" method="POST"
                                   modelAttribute="subject">
                            <h2 class="text-center">Create a Subject</h2>

                            <div class="form-group">

                                <form:input path="name" type="text" class="form-control" name="name"
                                            placeholder="Subject Name"
                                            required="required" autofocus="autofocus"/>
                            </div>
                            <div class="form-group">
                                <form:select path="user" type="text" class="form-control form-select" name="lecturer"
                                             placeholder="lecturer"
                                             required="required" autofocus="autofocus">
                                    <c:forEach items="${lecturers}" var="lecturers">
                                        <option>${lecturers.username}</option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-dark">Create subject</button>
                            </div>
                        </form:form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>