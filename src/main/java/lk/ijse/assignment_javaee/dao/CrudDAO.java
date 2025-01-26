package lk.ijse.assignment_javaee.dao;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.entity.User;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    public boolean save(T entity, HttpServletRequest request);

    public boolean update(T entity, HttpServletRequest request);

    public String getCurrentId(HttpServletRequest request);

    public List<T> getAll(HttpServletRequest request);

    public boolean delete(String id, HttpServletRequest request);
}
