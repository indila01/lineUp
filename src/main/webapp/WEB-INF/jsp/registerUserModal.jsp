<%--
  Created by IntelliJ IDEA.
  User: indiladineth
  Date: 2021-05-04
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>

<div class="modal" id="createUserModal">


    <div class="modal-header" >
        Create User Account
    </div>

    <div class="modal-body">
        <form onSubmit={this.handleSubmit} noValidate>
            <div class="firstName">
                <label htmlFor="firstName">First Name</label>
                <input
                placeholder="First Name"
                type="text"
                name="firstName"
                />

            </div>
            <div class="lastName">
                <label htmlFor="lastName">Last Name</label>
                <input
                placeholder="Last Name"
                type="text"
                name="lastName"
                />

            </div>
            <div class="email">
                <label htmlFor="email">Email</label>
                <input
                placeholder="Email"
                type="email"
                name="email"
                />

            </div>
            <div className="password">
                <label htmlFor="password">Password</label>
                <input

                placeholder="Password"
                type="password"
                name="password"
                />

            </div>
            <div class="createAccount">
                <button type="submit">Create Account</button>
                <small>Already Have an Account?</small>
            </div>

        </form>
    </div>


</div>

