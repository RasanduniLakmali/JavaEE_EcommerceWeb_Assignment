package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.UserBO;
import lk.ijse.assignment_javaee.dto.UserDTO;
import lk.ijse.assignment_javaee.entity.User;
import lk.ijse.assignment_javaee.util.PasswordUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserLoginServlet" , value = "/user-login")
public class LoginServlet extends HttpServlet {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("user_email");
        String plainPassword = req.getParameter("user_password");

        String hashedPassword = userBO.getHashedPassword(email,req);
        String userType = userBO.getUserType(email,req);
        String userName = userBO.getUserName(email,req);

        boolean isPasswordMatch = PasswordUtils.isPasswordMatch(plainPassword,hashedPassword);

        if(isPasswordMatch){

            HttpSession session = req.getSession();
            session.setAttribute("user_type", userType);
            session.setAttribute("user_email", email);
            session.setAttribute("user_name", userName);

            System.out.println(userType);

            if (req.getParameter("rememberMe") != null) { // Check if "Remember Me" is selected
                // Create cookies for email and userType
                Cookie emailCookie = new Cookie("user_email", email);
                Cookie userTypeCookie = new Cookie("user_type", userType);

                // Set cookie expiry to 7 days (604800 seconds)
                emailCookie.setMaxAge(7 * 24 * 60 * 60);
                userTypeCookie.setMaxAge(7 * 24 * 60 * 60);

                // Add cookies to the response
                resp.addCookie(emailCookie);
                resp.addCookie(userTypeCookie);
            } else {
                // "Remember Me" is not selected
                System.out.println("Remember Me option NOT selected by user.");
            }


            if ("Customer".equalsIgnoreCase(userType)) {

                resp.sendRedirect("index.jsp?message= Customer login Successful");

            } else if ("Admin".equalsIgnoreCase(userType)) {

                resp.sendRedirect("index.jsp?message= Admin login Successful");

            }else{
                resp.sendRedirect("index.jsp?error=Login Unsuccessful");
            }

        } else {

            resp.sendRedirect("login-page.jsp?error=Login Failed");
        }

    }
}
