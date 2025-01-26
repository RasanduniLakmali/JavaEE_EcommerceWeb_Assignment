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

@WebServlet(name = "UserSignUpServlet" , value = "/user-signUp")
public class SignUpServlet extends HttpServlet {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         String usrType = req.getParameter("user_type");
         String usrName = req.getParameter("user_name");
         String usrPassword = req.getParameter("user_password");
         String usrEmail = req.getParameter("user_email");
         String hashedPassword = PasswordUtils.hashPassword(usrPassword);

         try {
            UserDTO userDTO = new UserDTO(usrType,usrName,hashedPassword,usrEmail);
            boolean isSaved = userBO.saveUser(userDTO,req);

            HttpSession session = req.getSession();
            session.setAttribute("user", userDTO);
            session.setAttribute("userType", userDTO.getUserType());
            session.setAttribute("userName", userDTO.getName());
            session.setAttribute("userEmail", userDTO.getEmail());

             System.out.println(userDTO.getUserType());
             System.out.println(userDTO.getEmail());

            if (isSaved) {
                resp.sendRedirect("signUp.jsp?message=User saved successfully");
            }else {
                resp.sendRedirect("signUp.jsp?error=User not saved");
            }

        } catch (Exception e) {
            e.printStackTrace();

            resp.sendRedirect("signUp.jsp?error=User not saved");
        }
    }
}
