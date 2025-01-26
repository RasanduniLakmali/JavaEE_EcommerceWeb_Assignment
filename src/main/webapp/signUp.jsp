<%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/19/2025
  Time: 9:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles/signUp.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>

<%
    String message = request.getParameter("message");
    String error = request.getParameter("error");
%>

<% if (message != null) { %>
<script>
    Swal.fire({
        position: "top-end",
        icon: "success",
        title: "<%= message %>",
        showConfirmButton: false,
        timer: 1500
    });
</script>

<%
    }
%>

<%
    if (error != null){
%>


<script>
    Swal.fire({
        position: "top-end",
        icon: "error",
        title: "<%= error %>",
        showConfirmButton: false,
        timer: 1500
    });
</script>


<% } %>



<div id="form_rec">
    <div class="card forms" style="width: 25rem" id="signUp_form">
        <div class="card-body details">

            <form action="user-signUp" method="post">
                <h5 align="center">Create an account</h5>

                <div class="mb-3">
                    <label id="cusId">Usertype</label>
                    <select class="form-select" aria-label="Default select example" id="userType" name="user_type">
                        <option >Admin</option>
                        <option >Customer</option>
                    </select>

                </div>
                <div class="mb-3">
                    <label for="user_name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="user_name" name="user_name" placeholder="Enter your name">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password"  name="user_password" placeholder="Create a password">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="user_email" placeholder="Enter your email">
                </div>
                <div class="d-grid col-8 mx-auto">
                    <button class="btn btn-primary" type="submit">Sign up</button>
                    <p id="sign_text">Already have an account ? <a href="login-page.jsp">Login</a></p>
                </div>
            </form>

        </div>
    </div>

    <div>
        <img src="images/signUp_img.jpg" id="signUp_img" style="width: 400px" height="500px">

    </div>



</div>

</body>
</html>
