package lk.ijse.assignment_javaee.bo;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.dto.UserDTO;
import lk.ijse.assignment_javaee.entity.User;

import java.util.List;

public interface UserBO extends SuperBO{

    public boolean saveUser(UserDTO userDTO, HttpServletRequest request);

    public boolean updateUser(UserDTO userDTO, HttpServletRequest request);

    public String getHashedPassword(String email,HttpServletRequest request);

    public String getUserType(String email,HttpServletRequest request);

    public String getUserName(String email,HttpServletRequest request);

    public List<UserDTO> getAllCustomers(HttpServletRequest request,String userType);

    public UserDTO getUserByEmail(String email,HttpServletRequest request);
}
