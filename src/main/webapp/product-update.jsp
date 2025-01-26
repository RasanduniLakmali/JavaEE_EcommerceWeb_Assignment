<%@ page import="lk.ijse.assignment_javaee.dto.CategoryDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/21/2025
  Time: 1:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Update</title>
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
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
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



<div id="form_rec" align="center">
    <div class="card forms" style="width: 25rem" id="signUp_form">
        <div class="card-body details" align="left">

            <form action="update" method="post">

                <div class="mb-3">
                    <label for="product_id" class="form-label">Product ID</label>
                    <input type="text" class="form-control" id="product_id" placeholder="Enter product Id" name="product_id">
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
                    <label for="product_price" class="form-label">Price</label>
                    <input type="text" class="form-control" id="product_price" placeholder="Enter unit price" name="product_price">
                </div>
                <div class="mb-3">
                    <label for="qty" class="form-label">Quantity</label>
                    <input type="text" class="form-control" id="qty" placeholder="Enter quantity" name="product_qty" required>
                </div>

                <div class="mb-3">
                    <label for="imageUrl" class="form-label">Default Image</label>
                    <input type="text" class="form-control" id="imageUrl" placeholder="Input image" name="product_imgUrl" required>
                </div>

                <div class="mb-3">
                    <label for="image" class="form-label">Image</label>
                    <input type="file" class="form-control" id="image" placeholder="Input image" name="product_img">
                </div>


                <button type="submit" class="btn btn-primary" id="itemUpdateBtn">Update</button>

            </form>
        </div>
    </div>
</div>

<script src="js/jquery-3.7.1.min.js"></script>

<script>

    $('#product_name').on('keypress', function () {
            const productName = $(this).val();
            console.log('Input detected:', productName);

            if (productName.trim() !== '') {
                $.ajax({
                    url: 'http://localhost:8080/Assignment_JAVAEE_war_exploded/update',
                    type: 'GET',
                    data: { product_name: productName },
                    success: function (response) {
                        console.log('Success:', response);
                        $('#product_id').val(response.productId);
                        $('#product_price').val(response.productPrice);
                        $('#qty').val(response.productQty);
                        $('#imageUrl').val(response.productImg);
                        $('#category_id').val(response.categoryId).change();
                    },
                    error: function (error) {
                        console.log('Error:', status, error);
                        alert('Product not found or error occurred!');
                        $('#product_id').val('');
                        $('#product_price').val('');
                        $('#qty').val('');
                        $('#category_id').val('').change();
                    }
                });
            } else {
                console.log('Input cleared');
                $('#product_id').val('');
                $('product_price').val('');
                $('#qty').val('');
                $('#category_id').val('').change();
            }
        });




    // let debounceTimer;
    //
    // $('#product_name').on('keypress', function () {
    //     clearTimeout(debounceTimer); // Clear any previous timer
    //     const productName = $(this).val().trim();
    //
    //     if (productName !== '') {
    //         debounceTimer = setTimeout(function () {
    //             console.log('Input detected:', productName);
    //
    //             $.ajax({
    //                 url: 'http://localhost:8080/Assignment_JAVAEE_war_exploded/update',
    //                 type: 'GET',
    //                 data: { product_name: productName },
    //                 success: function (response) {
    //                     console.log('Success:', response);
    //                     $('#product_id').val(response.productId);
    //                     $('#price').val(response.productPrice);
    //                     $('#qty').val(response.productQty);
    //                     $('#imageUrl').val(response.productImg);
    //                     $('#category_id').val(response.categoryId).change();
    //                 },
    //                 error: function (xhr, status, error) {
    //                     console.log('Error:', status, error);
    //                     alert('Product not found or error occurred!');
    //                     $('#product_id').val('');
    //                     $('#price').val('');
    //                     $('#qty').val('');
    //                     $('#category_id').val('').change();
    //                 }
    //             });
    //         }, 300); // Wait for 300ms before making the AJAX call
    //     } else {
    //         console.log('Input cleared');
    //         $('#product_id').val('');
    //         $('#price').val('');
    //         $('#qty').val('');
    //         $('#category_id').val('').change();
    //     }
    // });
    //


</script>

</body>
</html>
