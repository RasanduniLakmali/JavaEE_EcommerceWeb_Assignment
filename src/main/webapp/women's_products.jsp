<%@ page import="lk.ijse.assignment_javaee.dto.ProductDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/25/2025
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Women's Products</title>
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
    // Retrieve the product list from the session
    List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("productList");


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

<section class="section" id="men">
    <div class="container">

        <div class="row justify-content-center"> <!-- Centering the row of products -->
            <% if (productList != null && !productList.isEmpty()) { %>
            <% for (ProductDTO product : productList) { %>
            <div class="col-lg-4 col-md-6 col-sm-12 d-flex justify-content-center"> <!-- Center each card -->
                <div class="card product-item border-0 mb-4" style="width: 18rem;">
                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0 text-center">
                        <img class="img-fluid w-auto" src="<%= product.getImage() %>" alt="<%= product.getProductName() %>" id="imageUrl">
                    </div>
                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                        <h6 class="text-truncate mb-3" id="productName"><%= product.getProductName() %></h6>
                        <div class="d-flex justify-content-center">
                            <h6 id="price">LKR<%= String.format("%.2f", product.getProductPrice()) %></h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <span id="quantity">Qty: <%= product.getProductQty() %></span>
                        <form action="add-to-cart" method="POST" class="m-0 p-0">
                            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                            <input type="hidden" name="productName" value="<%= product.getProductName() %>">
                            <input type="hidden" name="price" value="<%= product.getProductPrice() %>">
                            <input type="hidden" name="imageUrl" value="<%= product.getImage() %>">
                            <input type="hidden" name="quantity" value="1"> <!-- Default quantity to 1 -->
                            <button type="submit" class="btn btn-sm text-dark p-0">
                                <i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <% } %>
            <% } else { %>
            <div class="col-12 text-center">
                <p>No products available</p>
            </div>
            <% } %>
        </div>
    </div>
</section>

<script src="js/jquery-3.7.1.min.js"></script>
<script>
    // Display success message
    <% if (session.getAttribute("message") != null) { %>
    alert("<%= session.getAttribute("message") %>");
    <% session.removeAttribute("message"); %> // Clear message after displaying
    <% } %>

    // Display error message
    <% if (session.getAttribute("error") != null) { %>
    alert("<%= session.getAttribute("error") %>");
    <% session.removeAttribute("error"); %> // Clear error after displaying
    <% } %>



        $(document).ready(function() {
        $('.product-img').hover(function () {
            $(this).css('transform', 'scale(1.1)');
        }, function () {
            $(this).css('transform', 'scale(1)');
        });
    });


</script>


</body>
</html>
