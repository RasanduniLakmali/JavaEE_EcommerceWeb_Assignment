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
import lk.ijse.assignment_javaee.entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductListServlet",value = "/product-list")
public class ProductListServlet extends HttpServlet {

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProductDTO> products = productBO.getProducts(req);

//        String productName = req.getParameter("productName");
//        ProductDTO productDTO = productBO.getProductByName(productName,req);
//
//
        System.out.println(products);
        req.setAttribute("products", products);
        req.getRequestDispatcher("product-list.jsp").forward(req, resp);
    }
}
