package lk.ijse.assignment_javaee.bo.impl;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.bo.UserBO;
import lk.ijse.assignment_javaee.dao.DAOFactory;
import lk.ijse.assignment_javaee.dao.UserDAO;
import lk.ijse.assignment_javaee.dto.UserDTO;
import lk.ijse.assignment_javaee.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO userDTO, HttpServletRequest req) {
        return userDAO.save(new User(userDTO.getUserType(), userDTO.getName(), userDTO.getPassword(), userDTO.getEmail()),req);
    }

    @Override
    public boolean updateUser(UserDTO userDTO, HttpServletRequest request) {
        return userDAO.update(new User(userDTO.getName(),userDTO.getPassword(),userDTO.getEmail()),request);
    }

    @Override
    public String getHashedPassword(String email, HttpServletRequest request) {
        return userDAO.getPasswordByEmail(email,request);
    }

    @Override
    public String getUserType(String email, HttpServletRequest request) {
        return userDAO.getUser(email,request);
    }

    @Override
    public String getUserName(String email, HttpServletRequest request) {
        return userDAO.getName(email,request);
    }

    @Override
    public List<UserDTO> getAllCustomers(HttpServletRequest request,String userType) {
        List<User> customers = userDAO.getAll(request,userType);
        List<UserDTO> cusList = new ArrayList<>();

        for (User user : customers) {
            UserDTO userDTO = new UserDTO(user.getName(),user.getEmail());
            cusList.add(userDTO);
        }
        return cusList;
    }

    @Override
    public UserDTO getUserByEmail(String email, HttpServletRequest request) {
        User user = userDAO.getByEmail(email,request);
        return new UserDTO(user.getUserType(),user.getName(),user.getPassword(),user.getEmail());
    }
}
