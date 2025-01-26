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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryListServlet",value = "/category-list")
public class CategoryListServlet extends HttpServlet {

    CategoryBO categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CATEGORY);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDTO> categories = categoryBO.getAllCategories(req);

        req.setAttribute("categories", categories);
        req.getRequestDispatcher("category_list.jsp").forward(req, resp);

    }
}
