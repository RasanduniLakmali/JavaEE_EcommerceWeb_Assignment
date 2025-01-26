package lk.ijse.assignment_javaee.dao;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User>{

    public boolean save(User user, HttpServletRequest request);

    public String getPasswordByEmail(String email, HttpServletRequest request);

    public String getUser(String email, HttpServletRequest request);

    public String getName(String email, HttpServletRequest request);

    public List<User> getAll(HttpServletRequest request,String userType);

    public User getByEmail(String email, HttpServletRequest request);
}
