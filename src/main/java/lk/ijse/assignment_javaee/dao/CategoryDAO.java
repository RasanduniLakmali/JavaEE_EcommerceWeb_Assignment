package lk.ijse.assignment_javaee.dao;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.entity.Category;

public interface CategoryDAO extends CrudDAO<Category> {

    public Category search(String id, HttpServletRequest request);

    public Category searchByName(String name, HttpServletRequest request);
}
