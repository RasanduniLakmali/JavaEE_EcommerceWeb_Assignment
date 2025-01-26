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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "ProductSearchServlet",value = "/product-search")
public class ProductSearchServlet extends HttpServlet {

//    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);
//
//    CategoryBO categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CATEGORY);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String productName = req.getParameter("product_name");
//
//        if (productName == null || productName.trim().isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////            resp.getWriter().write("Product name is required");
//            return;
//        }
//
//        ProductDTO productDTO = productBO.getProductByName(productName,req);
//
//        List<CategoryDTO> categories = categoryBO.getAllCategories(req);
//
//        HttpSession session = req.getSession();
//        session.setAttribute("categories", categories);
//
//
//        if (productDTO != null) {
//            resp.setContentType("application/json");
//            resp.setCharacterEncoding("UTF-8");
//            PrintWriter out = resp.getWriter();
//            out.print("{");
//            out.print("\"productId\":\"" + productDTO.getProductId() + "\",");
//            out.print("\"productPrice\":\"" + productDTO.getProductPrice() + "\",");
//            out.print("\"productQty\":\"" + productDTO.getProductQty() + "\",");
//            out.print("\"productImg\":\"" + productDTO.getImage() + "\",");
//            out.print("\"categoryId\":\"" + productDTO.getCategory().getCategoryId() + "\"");
//            out.print("}");
//            out.flush();
//        } else {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//
//    }
}
