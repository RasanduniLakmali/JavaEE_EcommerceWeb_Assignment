<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.assignment_javaee.entity.Cart" %>
<%@ page import="lk.ijse.assignment_javaee.dto.CartDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/18/2025
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="styles/templatemo-hexashop.css">
    <link rel="stylesheet" type="text/css" href="styles/owl-carousel.css">
    <link rel="stylesheet" href="styles/lightbox.css">
    <link href="img/favicon.ico" rel="icon">
</head>
<body>

<%
    List<CartDTO> cartItems = (List<CartDTO>) request.getAttribute("cartItems");
    if (cartItems == null) {
        cartItems = new ArrayList<>(); // Prevent null pointer
    }

    // Handle success/error messages
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

<%
    }
%>
<!-- ***** Header Area Start ***** -->
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

<%--<div class="container-fluid mb-5" id="header">--%>
<%--    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">--%>
<%--        <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>--%>
<%--        <div class="d-inline-flex">--%>
<%--            <p class="m-0"><a href="">Home</a></p>--%>
<%--            <p class="m-0 px-2">-</p>--%>
<%--            <p class="m-0">Shopping Cart</p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<div id="carouselExample" class="carousel slide">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="images/cartImg1.jpg" class="d-block w-100" alt="..." style="height: 480px; object-fit: cover;">
        </div>
        <div class="carousel-item">
            <img src="..." class="d-block w-100" alt="..." style="height: 480px; object-fit: cover;">
        </div>
        <div class="carousel-item">
            <img src="..." class="d-block w-100" alt="..." style="height: 480px; object-fit: cover;" >
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>


<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Image</th>
                    <th>Products</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Remove</th>
                </tr>
                </thead>
                <tbody class="align-middle">

                <%
                    double subtotal = 0.0;
                    double shipping = 10.0;

                    for (CartDTO cartDTO : cartItems) {
                        subtotal += cartDTO.getTotalPrice();
                %>

                <tr>
                    <td class="align-middle"><img src="<%=cartDTO.getImageUrl()%>" alt="" style="width: 50px;"></td>
                    <td class="align-middle"><%=cartDTO.getProductName()%></td>
                    <td class="align-middle"><%=cartDTO.getPrice()%></td>
                    <td class="align-middle"><%=cartDTO.getQuantity()%></td>
                    <td class="align-middle"><%=cartDTO.getTotalPrice()%></td>
                    <td class="align-middle">
                        <form action="cart-remove" method="post">
                            <button type="submit" class="btn btn-sm btn-danger">
                                <i class="fa fa-times"></i>
                            </button>
                        </form>
                    </td>
<%--                    <td class="align-middle"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>--%>
                </tr>

                <%
                    }
                    double total = subtotal + shipping;
                %>

                </tbody>
            </table>


        </div>
   <div class="col-lg-4">
        <div class="card border-secondary mb-5">
            <div class="card-header bg-secondary border-0">
                <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
            </div>
            <div class="card-body">
                <div class="d-flex justify-content-between mb-3 pt-1">
                    <h6 class="font-weight-medium">Subtotal</h6>
                    <h6 class="font-weight-medium">LKR:<%=subtotal%></h6>
                </div>
                <div class="d-flex justify-content-between">
                    <h6 class="font-weight-medium">Shipping</h6>
                    <h6 class="font-weight-medium">LKR:<%=shipping%></h6>
                </div>
            </div>
            <div class="card-footer border-secondary bg-transparent">
                <div class="d-flex justify-content-between mt-2">
                    <h5 class="font-weight-bold">Total</h5>
                    <h5 class="font-weight-bold">LKR:<%=total%></h5>
                </div>
                <a href="place-order">
                   <button class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</button>
                </a>
            </div>
        </div>
        </div>

        </div>
    </div>


</body>
</html>
