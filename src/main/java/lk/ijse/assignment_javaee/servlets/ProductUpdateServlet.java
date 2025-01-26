package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.CategoryBO;
import lk.ijse.assignment_javaee.bo.ProductBO;
import lk.ijse.assignment_javaee.dto.CategoryDTO;
import lk.ijse.assignment_javaee.dto.ProductDTO;
import lk.ijse.assignment_javaee.entity.Category;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductUpdateServlet",value = "/update")
public class ProductUpdateServlet extends HttpServlet {

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    CategoryBO categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CATEGORY);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product_id");
        String productName = req.getParameter("product_name");
        String categoryId = req.getParameter("category_id");
//          double productPrice = Double.parseDouble(req.getParameter("product_price"));
//          int productQty = Integer.parseInt(req.getParameter("product_qty"));
        String image = req.getParameter("product_img");

        String productPriceStr = req.getParameter("product_price");
        double productPrice = 0.0;
        if (productPriceStr != null && !productPriceStr.isEmpty()) {
            productPrice = Double.parseDouble(productPriceStr);
        }

        // Validate and parse product quantity
        String productQtyStr = req.getParameter("product_qty");
        int productQty = 0;
        if (productQtyStr != null && !productQtyStr.isEmpty()) {
            productQty = Integer.parseInt(productQtyStr);
        }

        CategoryDTO categoryDTO = categoryBO.searchById(categoryId, req);

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setProductQty(categoryDTO.getProductQty());

        boolean isSaved = productBO.updateProduct(new ProductDTO(productId, productName, productPrice, productQty, image, category), req);

        if (isSaved) {
            resp.sendRedirect("product-update.jsp?message=Product updated successfully");
        } else {
            resp.sendRedirect("product_update.jsp?error=Product not updated");
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String productName = req.getParameter("product_name");
//
//        if (productName != null && !productName.trim().isEmpty()) {
//            // Handle AJAX request for product details
//            ProductDTO productDTO = productBO.getProductByName(productName, req);
//
//            if (productDTO != null) {
//                resp.setContentType("application/json");
//                resp.setCharacterEncoding("UTF-8");
//                PrintWriter out = resp.getWriter();
//                out.print("{");
//                out.print("\"productId\":\"" + productDTO.getProductId() + "\",");
//                out.print("\"productPrice\":\"" + productDTO.getProductPrice() + "\",");
//                out.print("\"productQty\":\"" + productDTO.getProductQty() + "\",");
//                out.print("\"productImg\":\"" + productDTO.getImage() + "\",");
//                out.print("\"categoryId\":\"" + productDTO.getCategory().getCategoryId() + "\"");
//                out.print("}");
//                out.flush();
//            } else {
//                // Product not found
//                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            }
//        } else {
//            // Handle page rendering for JSP
//            List<CategoryDTO> categories = categoryBO.getAllCategories(req);
//            req.setAttribute("categories", categories);
//            req.getRequestDispatcher("product-update.jsp").forward(req, resp);
//        }
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("product_name");
        System.out.println("Received product name: " + productName); // Debugging

        if (productName != null) {
            // Handle AJAX request for product details
            try {
                ProductDTO productDTO = productBO.getProductByName(productName, req);
                if (productDTO != null) {
                    // Product found, return JSON response
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    PrintWriter out = resp.getWriter();
                    out.print("{");
                    out.print("\"productId\":\"" + productDTO.getProductId() + "\",");
                    out.print("\"productPrice\":\"" + productDTO.getProductPrice() + "\",");
                    out.print("\"productQty\":\"" + productDTO.getProductQty() + "\",");
                    out.print("\"productImg\":\"" + productDTO.getImage() + "\",");
                    out.print("\"categoryId\":\"" + productDTO.getCategory().getCategoryId() + "\"");
                    out.print("}");
                    out.flush();

                    System.out.println("Product found: " + productName); // Debugging
                } else {
                    // Product not found
                    System.out.println("Product not found: " + productName); // Debugging
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\": \"Product not found\"}");
                }
            } catch (Exception e) {
                // Handle any unexpected exceptions
                System.out.println("Error retrieving product: " + e.getMessage()); // Debugging
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Internal server error\"}");
            }
        } else {
            // Handle page rendering for JSP
            try {
                List<CategoryDTO> categories = categoryBO.getAllCategories(req);
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("product-update.jsp").forward(req, resp);
                System.out.println("Categories loaded and JSP rendered."); // Debugging
            } catch (Exception e) {
                System.out.println("Error loading categories: " + e.getMessage()); // Debugging
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute("error", "Unable to load categories. Please try again later.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }
    }

}

