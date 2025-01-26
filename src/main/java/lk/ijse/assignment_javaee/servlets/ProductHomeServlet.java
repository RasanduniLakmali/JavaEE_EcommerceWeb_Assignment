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
import java.util.List;

@WebServlet(name = "ProductHomeServlet",value = "/product-home")
public class ProductHomeServlet extends HttpServlet {

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<ProductDTO> productList = productBO.getProducts(req);
//
//        System.out.println("Product List: " + productList);
//
//
////        // Store the product list in the session to make it accessible in the JSP
////        HttpSession session = req.getSession();
////        session.setAttribute("productList", productList);
//
//        req.setAttribute("productList", productList);
//        // Redirect to the product details page
//        req.getRequestDispatcher("products.jsp").forward(req, resp);

    }
}
