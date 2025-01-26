package lk.ijse.assignment_javaee.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.CategoryBO;
import lk.ijse.assignment_javaee.bo.ProductBO;
import lk.ijse.assignment_javaee.dto.CategoryDTO;
import lk.ijse.assignment_javaee.dto.ProductDTO;
import lk.ijse.assignment_javaee.entity.Category;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductSaveServlet", value = "/product-save")
public class ProductSaveServlet extends HttpServlet {

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    CategoryBO  categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CATEGORY);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product_id");
        String productName = req.getParameter("product_name");
        String categoryId = req.getParameter("category_id");
        double productPrice = Double.parseDouble(req.getParameter("product_price"));
        int productQty = Integer.parseInt(req.getParameter("product_qty"));


        Part file = req.getPart("product_img");
        String imgFilename  = file.getSubmittedFileName();
        String productImage = "images/" + imgFilename;
        String uploadPath = "C://Users//Rasanduni//Downloads//JSP//Assignment_JAVAEE//src//main//webapp//images/"+imgFilename;
        FileOutputStream fos = new FileOutputStream(uploadPath);
        InputStream is = file.getInputStream();

        try {

            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        CategoryDTO categoryDTO = categoryBO.searchById(categoryId,req);

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setProductQty(categoryDTO.getProductQty());

        boolean isSaved = productBO.saveProduct(new ProductDTO(productId,productName,productPrice,productQty,productImage,category),req);

        if (isSaved) {
            String currentId = productBO.getCurrentProductId(req);
            String nextId = generateNextId(currentId);

            // **Set new product ID in session**
            HttpSession session = req.getSession();
            session.setAttribute("new_id", nextId);

            resp.sendRedirect("product_details.jsp?message=Product saved successfully");
        }else {
            resp.sendRedirect("product_details.jsp?error=Product not saved");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CategoryDTO> categoryList = categoryBO.getAllCategories(req);
//        req.setAttribute("categoryList", categoryList);

        String currentId = productBO.getCurrentProductId(req);
        String nextId = generateNextId(currentId);

        HttpSession session = req.getSession();
        session.setAttribute("new_id",nextId);
        session.setAttribute("categoryList",categoryList);

        resp.sendRedirect("product_details.jsp");

    }

    private String generateNextId(String currentId) {
        if (currentId != null && currentId.matches("P\\d+")) {
            int idNum = Integer.parseInt(currentId.substring(1));
            return "P" + String.format("%03d", ++idNum);
        }
        return "P001";
    }

}
