package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.CategoryBO;
import lk.ijse.assignment_javaee.dto.CategoryDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryUpdateServlet",value = "/category-update")
public class CategoryUpdateServlet extends HttpServlet {

    CategoryBO categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CATEGORY);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryId = req.getParameter("category_id");
        String categoryName = req.getParameter("category_name");
        int qty = Integer.parseInt(req.getParameter("qty"));


        CategoryDTO categoryDTO = new CategoryDTO(categoryId, categoryName, qty);
        boolean isUpdated = categoryBO.updateCategory(categoryDTO,req);

        if (isUpdated) {
            resp.sendRedirect("category_update.jsp?message=Category updated successfully");
        } else {
            resp.sendRedirect("category_update.jsp?error=Category not updated");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("category_name");

        if (categoryName != null && !categoryName.trim().isEmpty()) {
            CategoryDTO categoryDTO = categoryBO.getCategoryByName(categoryName, req);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();
            if (categoryDTO != null) {
                System.out.println("Category found: " + categoryName); // Debugging

                // Return JSON response
                out.print("{\"categoryId\":\"" + categoryDTO.getCategoryId() + "\", \"productQty\":" + categoryDTO.getProductQty() + "}");
                out.flush();
            } else {
                System.out.println("Category not found: " + categoryName); // Debugging
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"error\":\"Category not found\"}");
                out.flush();
            }
        } else {
            // Handle page rendering for JSP
            try {
                List<CategoryDTO> categories = categoryBO.getAllCategories(req);
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("category_update.jsp").forward(req, resp);
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
