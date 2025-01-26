<%@ page import="lk.ijse.assignment_javaee.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/19/2025
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link href="styles/owl.carousel.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="styles/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="styles/templatemo-hexashop.css">
    <link rel="stylesheet" type="text/css" href="styles/owl-carousel.css">
    <link rel="stylesheet" href="styles/lightbox.css">
    <link href="img/favicon.ico" rel="icon">
</head>
<body>


<%
    String userType = (String) session.getAttribute("userType1");
    String userName = (String) session.getAttribute("userName");
    String email = (String) session.getAttribute("userEmail");

%>

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

<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="index.jsp" class="logo">
                        <img src="images/logo.png">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li class="scroll-to-section"><a href="index.jsp" class="active">Home</a></li>
                        <li class="scroll-to-section"><a href="men-product">Men's</a></li>
                        <li class="scroll-to-section"><a href="women-product">Women's</a></li>
                        <li class="scroll-to-section"><a href="kids-product">Kid's</a></li>
                        <li class="submenu">
                            <a href="javascript:;">Pages</a>
                            <ul>
                                <li><a href="product-save">Product Details</a></li>
                                <%--                                <li><a href="single-product.html">Single Product</a></li>--%>
                                <li><a href="category-save">Category Details</a></li>
                                <li><a href="cart-list">Cart</a></li>
                                <li><a href="place-order">Checkout</a></li>
                            </ul>
                        </li>

                        <li class="scroll-to-section"><a href="login-page.jsp">Login</a></li>
                        <li class="scroll-to-section"><a href="signUp.jsp">Register</a></li>
                    </ul>
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>

<%--<div class="card forms" style="width: 25rem" id="signUp_form">--%>
<%--    <div class="card-body details">--%>

<%--        <form action="" method="post">--%>

<%--            <div class="mb-3">--%>
<%--                <label for="user_name" class="form-label">Name</label>--%>
<%--                <input type="text" class="form-control" id="user_name" name="user_name" placeholder="Enter your name">--%>
<%--            </div>--%>
<%--            <div class="mb-3">--%>
<%--                <label for="password" class="form-label">Password</label>--%>
<%--                <input type="password" class="form-control" id="password"  name="user_password" placeholder="Create a password">--%>
<%--            </div>--%>
<%--            <div class="mb-3">--%>
<%--                <label for="email" class="form-label">Email</label>--%>
<%--                <input type="email" class="form-control" id="email" name="user_email" placeholder="Enter your email">--%>
<%--            </div>--%>

<%--                <button class="btn btn-primary" type="submit">Update</button>--%>
<%--                <button type="button" class="btn btn-secondary">Delete</button>--%>
<%--        </form>--%>

<%--    </div>--%>
<%--</div>--%>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<% if ("Admin".equalsIgnoreCase(userType)) { %>

<h3>Customer Details</h3>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<User> customerList = (List<User>) request.getAttribute("customerList");
        // Ensure `cuList` is not null
        if (customerList != null) {
            for (User customer : customerList) {
    %>
    <tr>
        <td><%= customer.getName() %></td>
        <td><%= customer.getEmail() %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<% } else if ("Customer".equalsIgnoreCase(userType)) { %>


<div id="form_rec" align="center">
    <div class="card forms" style="width: 25rem" id="signUp_form">
        <div class="card-body details" align="left">
        <form action="user-update" method="post">
    <div class="mb-3">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" value="<%= userName %>" required>
    </div>
    <div class="mb-3">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" value="<%= email %>" readonly>
    </div>
    <div class="mb-3">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Enter New Password">
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
</form>
        </div>
    </div>
</div>


<% } else { %>
<p>No account details available.</p>
<% } %>


</body>
</html>
