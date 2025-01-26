<%@ page import="lk.ijse.assignment_javaee.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lk.ijse.assignment_javaee.dto.ProductDTO" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/18/2025
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Details</title>

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
    String newId = (String) session.getAttribute("new_id");
    %>
<%
    List<CategoryDTO> categories = (List<CategoryDTO>) session.getAttribute("categoryList");

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



<!-- Navbar Start -->
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

<h2 align="center">PRODUCT MANAGEMENT</h2>

<%--<a href="product-save" class="btn btn-primary">Add New Product</a>--%>

<br>
<br>

<%--<div id="form_rec" align="center">--%>
<%--    <div class="card forms" style="width: 25rem" id="signUp_form">--%>
<%--        <div class="card-body details" align="left">--%>

<%--            <form action="product-save" method="post" enctype="multipart/form-data">--%>

<%--                <div class="mb-3">--%>
<%--                    <label for="product_id" class="form-label">Product ID</label>--%>
<%--                    <input type="text" class="form-control" id="product_id" placeholder="Enter product Id" name="product_id" value="<%= session.getAttribute("new_id") %>" readonly>--%>
<%--                </div>--%>


<%--                <div class="mb-3">--%>
<%--                    <label for="product_name" class="form-label">Product Name</label>--%>
<%--                    <input type="text" class="form-control" id="product_name" placeholder="Enter product name" name="product_name">--%>
<%--                </div>--%>
<%--                <div class="mb-3">--%>
<%--                    <label id="cusId">Category ID</label>--%>
<%--                    <select class="form-select" aria-label="Default select example" id="category_id" name="category_id">--%>

<%--                        <option value="">Select Category</option>--%>
<%--                        <%--%>
<%--//                            List<CategoryDTO> categoryList = categories;--%>
<%--                            if (categories != null) {--%>
<%--                                for (CategoryDTO category : categories) {--%>
<%--                        %>--%>
<%--                        <option value="<%= category.getCategoryId() %>">--%>
<%--                            <%= category.getCategoryId()%>--%>
<%--                        </option>--%>
<%--                        <%--%>
<%--                                }--%>
<%--                            }--%>
<%--                        %>--%>

<%--                    </select>--%>
<%--                </div>--%>

<%--                <div class="mb-3">--%>
<%--                    <label for="price" class="form-label">Price</label>--%>
<%--                    <input type="text" class="form-control" id="price" placeholder="Enter unit price" name="product_price">--%>
<%--                </div>--%>
<%--        <div class="mb-3">--%>
<%--            <label for="qty" class="form-label">Quantity</label>--%>
<%--            <input type="text" class="form-control" id="qty" placeholder="Enter quantity" name="product_qty">--%>
<%--        </div>--%>
<%--                <div class="mb-3">--%>
<%--                    <label for="image" class="form-label">Image</label>--%>
<%--                    <input type="file" class="form-control" id="image" placeholder="Input image" name="product_img">--%>
<%--                </div>--%>

<%--                <button type="submit" class="btn btn-success" id="itemSaveBtn">Save</button>--%>
<%--                <button type="button" class="btn btn-primary" id="itemUpdateBtn">Update</button>--%>
<%--                <button type="button" class="btn btn-danger" id="itemDeleteBtn">Delete</button>--%>
<%--                <button type="button" class="btn btn-warning" id="itemClearBtn">Clear</button>--%>

<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<a href="product-list">--%>
<%--    <button type="button" class="btn btn-primary" id="loadBtn">View Products</button>--%>
<%--</a>--%>

<%--<a href="update">--%>
<%--    <button type="button" class="btn btn-primary" id="updateBtn">Update Products</button>--%>
<%--</a>--%>

<%--<a href="product-delete.jsp" class="btn btn-primary">Delete Product</a>--%>




<div class="container mt-5">
    <h2 class="text-center mb-4">Manage Dress Collection</h2>
    <div class="row">

        <!-- Save Product -->
        <div class="col-md-3">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Save Product</h5>
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#saveModal">Save</button>
                </div>
            </div>
        </div>

        <!-- Update Product -->
        <div class="col-md-3">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Update Product</h5>
                    <a href="update"><button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal">Update</button></a>
                </div>
            </div>
        </div>

        <!-- List Products -->
        <div class="col-md-3">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">View Products</h5>
                   <a href="product-list"> <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#listModal">View</button></a>
                </div>
            </div>
        </div>

        <!-- Delete Product -->
        <div class="col-md-3">
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title">Delete Product</h5>
                    <a href="product-delete"><button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button></a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="saveModal" tabindex="-1" aria-labelledby="saveModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="saveModalLabel">Save Product</h5>
            </div>
            <div class="modal-body">

                <form action="product-save" method="post" enctype="multipart/form-data">

                                    <div class="mb-3">
                                        <label for="product_id" class="form-label">Product ID</label>
                                        <input type="text" class="form-control" id="product_id" placeholder="Enter product Id" name="product_id" value="<%= session.getAttribute("new_id") %>" readonly>
                                    </div>


                                    <div class="mb-3">
                                        <label for="product_name" class="form-label">Product Name</label>
                                        <input type="text" class="form-control" id="product_name" placeholder="Enter product name" name="product_name">
                                    </div>
                                    <div class="mb-3">
                                        <label id="cusId">Category ID</label>
                                        <select class="form-select" aria-label="Default select example" id="category_id" name="category_id">

                                            <option value="">Select Category</option>
                                            <%
                    //                            List<CategoryDTO> categoryList = categories;
                                                if (categories != null) {
                                                    for (CategoryDTO category : categories) {
                                            %>
                                            <option value="<%= category.getCategoryId() %>">
                                                <%= category.getCategoryId()%>
                                            </option>
                                            <%
                                                    }
                                                }
                                            %>

                                        </select>
                                    </div>

                                    <div class="mb-3">
                                        <label for="price" class="form-label">Price</label>
                                        <input type="text" class="form-control" id="price" placeholder="Enter unit price" name="product_price">
                                    </div>
                            <div class="mb-3">
                                <label for="qty" class="form-label">Quantity</label>
                                <input type="text" class="form-control" id="qty" placeholder="Enter quantity" name="product_qty">
                            </div>
                                    <div class="mb-3">
                                        <label for="image" class="form-label">Image</label>
                                        <input type="file" class="form-control" id="image" placeholder="Input image" name="product_img">
                                    </div>

                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#saveModal" type="submit">Save</button>

                </form>
            </div>
        </div>
    </div>
</div>


<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

