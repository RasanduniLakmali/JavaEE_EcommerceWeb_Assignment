package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.ProductBO;
import lk.ijse.assignment_javaee.dto.ProductDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "KidsProductServlet",value = "/kids-product")
public class KidsProductServlet extends HttpServlet {

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProductDTO> productList = productBO.getKidsProducts(req);

        System.out.println("Product List: " + productList);


        req.setAttribute("productList", productList);
        // Redirect to the product details page
        req.getRequestDispatcher("kid's_products.jsp").forward(req, resp);

    }
}
