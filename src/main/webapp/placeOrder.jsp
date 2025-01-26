<%@ page import="lk.ijse.assignment_javaee.dto.CartDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/18/2025
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link href="styles/owl.carousel.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="styles/templatemo-hexashop.css">
    <link rel="stylesheet" type="text/css" href="styles/owl-carousel.css">
    <link rel="stylesheet" href="styles/lightbox.css">
    <link href="img/favicon.ico" rel="icon">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="styles/style.css" rel="stylesheet">
</head>
<body>

<%
    String username = (String) session.getAttribute("user_name");
    String email = (String) session.getAttribute("user_email");
%>

<%
    List<CartDTO> cartItems = (List<CartDTO>) request.getAttribute("cartItems");
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

<br>
<br>
<br>
<br>
<br>
<br>
<br>

<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 500px; background-image: url('images/checkoutImg.jpg'); background-size: cover; background-position: center center;">
        <h1 class="font-weight-semi-bold text-uppercase mb-3 text-white">Checkout</h1>
        <div class="d-inline-flex">
            <p class="m-0 text-white">Checkout</p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Checkout Start -->
<form action="place-order" method="post">
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-8">
            <div class="mb-4">
                <div class="row">
                    <div class="col-md-6 form-group">
                        <label>Name</label>
                        <input class="form-control" type="text" placeholder="Enter your name" name="customer_name" value="<%= username != null ? username : "" %>">
                    </div>

                    <div class="col-md-6 form-group">
                        <label>E-mail</label>
                        <input class="form-control" type="text" placeholder="Enter your email" name="customer_email" value="<%= email != null ? email : "" %>">
                    </div>
                    <div class="col-md-6 form-group">
                        <label>Mobile No</label>
                        <input class="form-control" type="text" placeholder="Enter your mobile" name="mobile">
                    </div>
                    <div class="col-md-6 form-group">
                        <label>Address</label>
                        <input class="form-control" type="text" placeholder="Enter your address" name="address">
                    </div>

                    <div class="col-md-6 form-group">
                        <label>City</label>
                        <input class="form-control" type="text" placeholder="Enter your city">
                    </div>

                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="card border-secondary mb-5">
                <div class="card-header bg-secondary border-0">
                    <h4 class="font-weight-semi-bold m-0">Order Total</h4>
                </div>
                <div class="card-body">

                    <h5 class="font-weight-medium mb-3">Products</h5>

                    <%
                        double subtotal = 0.0;
                        double shipping = 10.0;

                        if (cartItems != null) {
                        for (CartDTO cartDTO : cartItems) {
                            double productTotal = cartDTO.getPrice() * cartDTO.getQuantity(); // Calculate total for the product
                            subtotal += productTotal;
                    %>

                    <div class="d-flex justify-content-between">
                        <p><%=cartDTO.getProductName()%> (x<%= cartDTO.getQuantity() %>)</p>

                        <p><%= productTotal %></p>

                        <input type="hidden" name="productName" value="<%= cartDTO.getProductName() %>">
                        <input type="hidden" name="productQty" value="<%= cartDTO.getQuantity() %>">
                        <input type="hidden" name="productTotal" value="<%= productTotal %>">
                    </div>

                    <%
                        }
                    } else {
                    %>

                    <p>Your cart is empty. Please add items to your cart.</p>
                    <%
                        }
                        double total = subtotal + shipping;
                    %>

                    <hr class="mt-0">
                    <div class="d-flex justify-content-between mb-3 pt-1">
                        <h6 class="font-weight-medium">Subtotal</h6>
                        <h6 class="font-weight-medium">LKR: <%=subtotal%></h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Shipping</h6>
                        <h6 class="font-weight-medium">LKR: <%=shipping%></h6>
                    </div>
                </div>
                <div class="card-footer border-secondary bg-transparent">
                    <div class="d-flex justify-content-between mt-2">
                        <h5 class="font-weight-bold">Total</h5>
                        <h5 class="font-weight-bold">LKR: <%=total%></h5>
                        <input type="hidden" name="total" value="<%= total %>">
                    </div>
                </div>
            </div>
            <div class="card border-secondary mb-5">
                <div class="card-header bg-secondary border-0">
                    <h4 class="font-weight-semi-bold m-0">Payment</h4>
                </div>

                <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="cash_payment" value="Cash Payment">
                            <label class="custom-control-label" for="cash_payment">Cash payment</label>
                        </div>
                    </div>
                    <div class="">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="banktransfer" value="Bank Transfer">
                            <label class="custom-control-label" for="banktransfer">Bank Transfer</label>
                        </div>
                    </div>
                </div>

                <div class="card-footer border-secondary bg-transparent">
                    <button class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Place Order</button>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- Checkout End -->


</body>
</html>
