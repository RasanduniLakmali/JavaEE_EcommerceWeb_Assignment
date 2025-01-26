package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.UserBO;
import lk.ijse.assignment_javaee.dto.UserDTO;
import lk.ijse.assignment_javaee.entity.User;
import lk.ijse.assignment_javaee.util.PasswordUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserUpdateServlet",value = "/user-update")
public class UserUpdateServlet extends HttpServlet {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String usrName = req.getParameter("user_name");
//        String usrPassword = req.getParameter("user_password");
//        String usrEmail = req.getParameter("user_email");
//
//        UserDTO userDTO = new UserDTO(usrName, usrPassword, usrEmail);
//
//        boolean isUpdated = userBO.updateUser(userDTO,req);
//
//
//        if (isUpdated){
//            resp.sendRedirect("user.jsp?message= User Updated Successfully");
//        }else {
//            resp.sendRedirect("user.jsp?message= User Not Updated");
//        }
//    }



        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("user_email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        // Hash the new password
        String hashedPassword = PasswordUtils.hashPassword(password);

        // Create a new User object and update details
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(hashedPassword);

        UserDTO userDTO = new UserDTO(name,hashedPassword,email);

        boolean isUpdated = userBO.updateUser(userDTO,req);
        if (isUpdated) {
            session.setAttribute("userName", name);
            resp.sendRedirect("user.jsp?message=Details Updated Successfully");
        } else {
            resp.sendRedirect("user.jsp?error=Update Failed");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session1 = req.getSession();
        String email = (String) session1.getAttribute("user_email");
        String userType1 = (String) session1.getAttribute("user_type");
        String userType = userBO.getUserType(email,req);
        session1.setAttribute("userType",userType1);


        if ("Admin".equalsIgnoreCase(userType)) {
            session1.setAttribute("userType",userType1);
            // Fetch customer list for admin
            List<UserDTO> customerList = userBO.getAllCustomers(req,userType);

            req.setAttribute("cuList", customerList);
        } else if ("Customer".equalsIgnoreCase(userType)) {
            // Fetch logged-in user details
            email = (String) session1.getAttribute("user_email");
            String userName = userBO.getUserName(email,req);
            session1.setAttribute("userName", userName);
            session1.setAttribute("userEmail",email);
            session1.setAttribute("userType",userType1);

        }

        // Forward to JSP
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
