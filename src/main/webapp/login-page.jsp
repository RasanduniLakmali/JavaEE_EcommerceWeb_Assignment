<%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/16/2025
  Time: 9:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles/login.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="fonts/icomoon/style.css">

    <link rel="stylesheet" href="styles/owl.carousel.min.css">



    <!-- Style -->
    <link rel="stylesheet" href="styles/style2.css">
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


<%--<div  class="d-flex flex-column justify-content-center align-items-center vh-100">--%>
<%--    <div class="col-md-3">--%>
<%--        <div class="card loginCard">--%>
<%--            <div class="card-body login_cover">--%>

<%--                <form id="loginForm" action="user-login" method="post">--%>
<%--                    <h5 id="heading1" class="text-center">Sign in with email</h5>--%>
<%--                    <div class="mb-3" id="field1">--%>
<%--                        <label for="inputEmail" class="form-label labels">Email address</label>--%>
<%--                        <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" name="user_email">--%>
<%--                    </div>--%>
<%--                    <div class="mb-2" id="field2">--%>
<%--                        <label for="inputPassword" class="form-label labels">Password</label>--%>
<%--                        <input type="password" class="form-control" id="inputPassword" name="user_password">--%>
<%--                    </div>--%>

<%--                    <div>--%>
<%--                        <input type="checkbox" name="rememberMe" id="rememberMe">--%>
<%--                        <label for="rememberMe">Remember Me</label>--%>
<%--                    </div>--%>

<%--                    <div class="d-grid col-8 mx-auto">--%>


<%--                        <button type="submit" value="Login" id="login_btn" class="btn btn-primary">Get Started</button>--%>

<%--                    </div>--%>

<%--                </form>--%>


<%--                <div class="d-grid col-8 mx-auto">--%>

<%--                     <p>Don't have an account ?<a href="index.jsp">Sign up</a></p>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>



<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="images/undraw_remotely_2j6y.svg" alt="Image" class="img-fluid">
            </div>
            <div class="col-md-6 contents">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="mb-4">
                            <h3>Sign In</h3>

                        </div>
                        <form action="user-login" method="post">
                            <div class="form-group first">
                                <label for="username">Email</label>
                                <input type="email" class="form-control" id="username" name="user_email">

                            </div>
                            <div class="form-group last mb-4">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="user_password">

                            </div>

                            <div class="d-flex mb-5 align-items-center">
                                <label class="control control--checkbox mb-0"><span class="caption">Remember me</span>
                                    <input type="checkbox" checked="checked"/>
                                    <div class="control__indicator"></div>
                                </label>

                            </div>

                            <p>Don't have an account ?<a href="signUp.jsp" style="color: blue">Sign up</a></p>

                            <input type="submit" value="Log In" class="btn btn-block btn-primary">

                            <span class="d-block text-left my-4 text-muted">&mdash; or login with &mdash;</span>

                            <div class="social-login">
                                <a href="#" class="facebook">
                                    <span class="icon-facebook mr-3"></span>
                                </a>
                                <a href="#" class="twitter">
                                    <span class="icon-twitter mr-3"></span>
                                </a>
                                <a href="#" class="google">
                                    <span class="icon-google mr-3"></span>
                                </a>
                            </div>

<%--                            <div class="d-grid col-8 mx-auto">--%>

<%--                                                     <p>Don't have an account ?<a href="signUp.jsp" style="color: blue">Sign up</a></p>--%>

<%--                                                </div>--%>
                        </form>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>

<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main2.js"></script>
</body>
</html>