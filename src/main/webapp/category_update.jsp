<%@ page import="lk.ijse.assignment_javaee.dto.CategoryDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rasanduni
  Date: 1/25/2025
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category Update</title>
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
<br>
<br>

<div id="form_rec" align="center">
    <div class="card forms" style="width: 25rem" id="signUp_form">
        <div class="card-body details" align="left">

            <form action="category-update" method="post">

                <div class="mb-3">
                    <label id="cateId">Category ID</label>
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
                    <label for="category_name" class="form-label">Category Name</label>
                    <input type="text" class="form-control" id="category_name" placeholder="Enter category name" name="category_name">
                </div>

                <div class="mb-3">
                    <label for="qty" class="form-label">Quantity</label>
                    <input type="text" class="form-control" id="qty" placeholder="Enter quantity" name="category_qty" required>
                </div>

                <button type="submit" class="btn btn-primary" id="categoryUpdateBtn">Update</button>

            </form>
        </div>
    </div>
</div>


<script src="js/jquery-3.7.1.min.js"></script>

<script>

    $('#category_name').on('keypress', function () {
        const categoryName = $(this).val();
        console.log('Input detected:', categoryName);

        if (categoryName.trim() !== '') {
            $.ajax({
                url: 'http://localhost:8080/Assignment_JAVAEE_war_exploded/category-update',
                type: 'GET',
                data: { category_name: categoryName },
                success: function (response) {
                    console.log('Success:', response);
                    $('#category_id').val(response.categoryId).change();
                    $('#qty').val(response.productQty);


                },
                error: function (error) {
                    console.log('Error:', status, error);
                    alert('Product not found or error occurred!');
                    $('#category_id').val('');
                    $('#qty').val('');

                }
            });
        } else {
            console.log('Input cleared');
            $('#category_id').val('');
            $('#qty').val('');
        }
    });

</script>

</body>
</html>
