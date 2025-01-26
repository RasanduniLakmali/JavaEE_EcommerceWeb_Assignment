package lk.ijse.assignment_javaee.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.ProductBO;
import lk.ijse.assignment_javaee.dto.ProductDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductDeleteServlet", value = "/product-delete")
public class ProductDeleteServlet extends HttpServlet {

    private final ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productID = req.getParameter("product_id");

        boolean isDeleted = productBO.deleteProduct(productID, req);

        if (isDeleted) {
            resp.sendRedirect("product-delete.jsp?message=Product deleted successfully");
        } else {
            resp.sendRedirect("product-delete.jsp?error=Product not deleted");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product_id");

        // Check if request is an AJAX call
        String ajaxHeader = req.getHeader("X-Requested-With");
        boolean isAjaxRequest = ajaxHeader != null && ajaxHeader.equals("XMLHttpRequest");

        if (productId != null && !productId.trim().isEmpty() && isAjaxRequest) {
            ProductDTO productDTO = productBO.getProductById(productId, req); // FIX: Changed method name

            // Set JSON response
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            if (productDTO != null) {
                out.write("{\"product_name\": \"" + productDTO.getProductName() + "\"}");
            } else {
                out.write("{\"product_name\": \"\"}");
            }
            out.flush();
            out.close();
            return; // Prevent further execution
        }

        // Fetch all products for page rendering
        List<ProductDTO> productList = productBO.getProducts(req);
        HttpSession session = req.getSession();
        session.setAttribute("productList", productList);

        // Only redirect if it's not an AJAX request
        if (!isAjaxRequest) {
            resp.sendRedirect("product-delete.jsp");
        }
    }
}


