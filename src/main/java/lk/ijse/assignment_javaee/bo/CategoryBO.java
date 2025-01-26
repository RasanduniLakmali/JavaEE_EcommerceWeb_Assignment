package lk.ijse.assignment_javaee.bo;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.dto.CategoryDTO;

import java.util.List;

public interface CategoryBO extends SuperBO{

    public boolean saveCategory(CategoryDTO category, HttpServletRequest request);

    public String getCurrentCategoryId(HttpServletRequest request);

    public List<CategoryDTO> getAllCategories(HttpServletRequest request);

    public CategoryDTO searchById(String id, HttpServletRequest request);

    public CategoryDTO getCategoryByName(String categoryName, HttpServletRequest request);

    public boolean updateCategory(CategoryDTO categoryDTO, HttpServletRequest request);
}
