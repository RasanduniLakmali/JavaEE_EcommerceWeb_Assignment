<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.assignment_javaee.entity.Product" %>
<%@ page import="lk.ijse.assignment_javaee.dto.ProductDTO" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/21/2025
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Delete</title>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
    List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("productList");
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

<%
    }
%>

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

<%--<a href="product-delete" class="btn btn-primary">Delete</a>--%>

<div id="form_rec" align="center">
    <div class="card forms" style="width: 25rem" id="signUp_form">
        <div class="card-body details" align="left">

            <form action="product-delete" method="post">

                <div class="mb-3">
                    <label id="cusId">Product ID</label>
                    <select class="form-select" aria-label="Default select example" id="product_id" name="product_id">

                        <option value="">Select Product</option>
                        <%
                            //                            List<CategoryDTO> categoryList = categories;
                            if (productList != null) {
                                for (ProductDTO productDTO : productList) {
                        %>
                        <option value="<%= productDTO.getProductId() %>">
                            <%= productDTO.getProductId()%>
                        </option>
                        <%
                                }
                            }
                        %>

                    </select>
                </div>


                <div class="mb-3">
                    <label for="product_name" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="product_name" placeholder="Enter product name" name="product_name">
                </div>

                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#saveModal" type="submit">Delete</button>
            </form>
        </div>
    </div>
</div>


<script src="js/jquery-3.7.1.min.js"></script>

<script>

    $(document).ready(function () {
        $('#product_id').on('change', function () {  // Use change event instead of keypress
            var productId = $(this).val(); // Get selected product ID

            if (productId) {
                $.ajax({
                    url: 'http://localhost:8080/Assignment_JAVAEE_war_exploded/product-delete', // The servlet URL
                    type: 'GET',
                    data: { product_id: productId },
                    dataType: 'json', // Expecting JSON response
                    success: function (data) {
                        if (data.product_name) {
                            $('#product_name').val(data.product_name); // Set product name field
                        } else {
                            $('#product_name').val(''); // Clear if no product found
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error('Error fetching product details:', error);
                        alert('Failed to fetch product details. Please try again.');
                    }
                });
            } else {
                $('#product_name').val(''); // Clear input if no product is selected
            }
        });
    });

</script>

</body>
</html>
