package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.CartBO;
import lk.ijse.assignment_javaee.dto.CartDTO;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "RemoveFromCartServlet",value = "/cart-remove")
public class RemoveFromCartServlet extends HttpServlet {

    CartBO cartBO = (CartBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CART);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false); // Don't create a new session
        if (session == null || session.getAttribute("user_email") == null) {
            resp.sendRedirect("login-page.jsp?message=Session Expired");
            return;
        }

        String userEmail = (String) session.getAttribute("user_email");

        boolean isRemoved = cartBO.removeCartItem(userEmail,req);

//        if (isRemoved) {
//
//            resp.sendRedirect("cart.jsp?message=Product removed successfully");
//
//        }else {
//            resp.sendRedirect("cart.jsp?error=Product not removed");
//        }

        List<CartDTO> updatedCartItems = cartBO.getCartItemsByEmail(userEmail,req);
        session.setAttribute("cartItems", updatedCartItems); // Update session

        // Redirect to the cart page and force a refresh
        resp.sendRedirect("cart.jsp?message=" + (isRemoved ? "Product removed successfully" : "Product not removed"));

    }
}
