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

@WebServlet(name = "CartListServlet",value = "/cart-list")
public class CartListServlet extends HttpServlet {

    CartBO cartBO = (CartBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CART);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("user_email") == null) {
            resp.sendRedirect("login-page.jsp?message=Session Expired");
            return;
        }

        // Get user email from session
        String userEmail = (String) session.getAttribute("user_email");
        System.out.println(userEmail);


        // Fetch cart items from the database for the logged-in user
        List<CartDTO> cartItems = cartBO.getCartItemsByEmail(userEmail, req);
        System.out.println(cartItems.toString());

//        double subtotal = cartItems.stream().mapToDouble(CartDTO::getTotalPrice).sum();
//        double shipping = 10.0; // Example flat shipping cost
//        double total = subtotal + shipping;

        // Set attributes for JSP
        req.setAttribute("cartItems", cartItems);


        // Forward to JSP
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
