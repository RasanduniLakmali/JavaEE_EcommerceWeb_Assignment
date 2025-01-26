package lk.ijse.assignment_javaee.bo.impl;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.bo.CategoryBO;
import lk.ijse.assignment_javaee.dao.CategoryDAO;
import lk.ijse.assignment_javaee.dao.DAOFactory;
import lk.ijse.assignment_javaee.dto.CategoryDTO;
import lk.ijse.assignment_javaee.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {

    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CATEGORY);

    @Override
    public boolean saveCategory(CategoryDTO categoryDTO, HttpServletRequest request) {

        return categoryDAO.save(new Category(categoryDTO.getCategoryId(),categoryDTO.getCategoryName(),categoryDTO.getProductQty()),request);
    }

    @Override
    public String getCurrentCategoryId(HttpServletRequest request) {
         return categoryDAO.getCurrentId(request);
    }

    @Override
    public List<CategoryDTO> getAllCategories(HttpServletRequest request) {
        List<Category> categories = categoryDAO.getAll(request);

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories){
            CategoryDTO categoryDTO = new CategoryDTO(category.getCategoryId(),category.getCategoryName(),category.getProductQty());
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO searchById(String id, HttpServletRequest request) {
        Category category = categoryDAO.search(id,request);
        return new CategoryDTO(category.getCategoryId(),category.getCategoryName(),category.getProductQty());
    }

    @Override
    public CategoryDTO getCategoryByName(String categoryName, HttpServletRequest request) {
        Category category = categoryDAO.searchByName(categoryName,request);
        return new CategoryDTO(category.getCategoryId(),category.getCategoryName(),category.getProductQty());
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO, HttpServletRequest request) {
        return categoryDAO.update(new Category(categoryDTO.getCategoryId(),categoryDTO.getCategoryName(),categoryDTO.getProductQty()),request);
    }
}
