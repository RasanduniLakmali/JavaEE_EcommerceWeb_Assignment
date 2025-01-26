package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.CategoryBO;
import lk.ijse.assignment_javaee.dto.CategoryDTO;
import lk.ijse.assignment_javaee.dto.ProductDTO;

import java.io.IOException;

@WebServlet(name = "CategorySaveServlet",value = "/category-save")
public class CategorySaveServlet extends HttpServlet {

    CategoryBO categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CATEGORY);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName = req.getParameter("category_name");
        int productQty = Integer.parseInt(req.getParameter("product_qty"));

        String currentId = categoryBO.getCurrentCategoryId(req);

        String nextId = generateNextId(currentId);

        CategoryDTO categoryDTO = new CategoryDTO(nextId, categoryName, productQty);

        boolean isSaved = categoryBO.saveCategory(categoryDTO,req);

        if (isSaved) {
            resp.sendRedirect("category_details.jsp?message=Category saved successfully");
        }else {
            resp.sendRedirect("category_details.jsp?error=Category not saved");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentId = categoryBO.getCurrentCategoryId(req);
        System.out.println(currentId);
        String nextId = generateNextId(currentId);

//        req.setAttribute("nextCategoryId", nextId);
//
//        // Forward to the JSP page
//        req.getRequestDispatcher("category_details.jsp").forward(req, resp);

        System.out.println(nextId);
        HttpSession session = req.getSession();
        session.setAttribute("new_id",nextId);
        resp.sendRedirect("category_details.jsp");
    }

    private String generateNextId(String currentId) {
        if (currentId != null && currentId.matches("C\\d+")) {
            int idNum = Integer.parseInt(currentId.substring(1));
            return "C" + String.format("%03d", ++idNum);
        }
        return "C001";
    }
}
