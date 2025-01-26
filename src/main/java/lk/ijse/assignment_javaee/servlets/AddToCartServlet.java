package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.CartBO;
import lk.ijse.assignment_javaee.bo.ProductBO;
import lk.ijse.assignment_javaee.bo.UserBO;
import lk.ijse.assignment_javaee.dto.CartDTO;
import lk.ijse.assignment_javaee.dto.ProductDTO;
import lk.ijse.assignment_javaee.dto.UserDTO;
import lk.ijse.assignment_javaee.entity.Cart;
import lk.ijse.assignment_javaee.entity.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddToCartServlet",value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    CartBO cartBO = (CartBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CART);

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Don't create a new session
        if (session == null || session.getAttribute("user_email") == null) {
            resp.sendRedirect("login-page.jsp?message=Session Expired");
            return;
        }

        String userEmail = (String) session.getAttribute("user_email");
        UserDTO userDTO = userBO.getUserByEmail(userEmail, req);

        User user = new User();
        user.setUserType(userDTO.getUserType());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        String productName = req.getParameter("productName");
        double price = Double.parseDouble(req.getParameter("price"));
        String imageUrl = req.getParameter("imageUrl");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double totalPrice = price * quantity;

        CartDTO existingCart = cartBO.getCartByUserAndProductName(userEmail, productName, req);

        String referer = req.getHeader("referer"); // Get the previous page URL

        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            existingCart.setTotalPrice(existingCart.getPrice() * existingCart.getQuantity());
            cartBO.updateCart(existingCart, req);
            session.setAttribute("message", "Product updated in cart successfully!");

        } else {
            CartDTO cartDTO = new CartDTO(user, productName, price, imageUrl, quantity, totalPrice);
            boolean isSaved = cartBO.saveCart(cartDTO, req);

            if (isSaved) {
                session.setAttribute("message", "Product added to cart successfully!");
            } else {
                session.setAttribute("error", "Failed to add product to cart.");
            }
        }

        // Redirect back to the previous page
        resp.sendRedirect(referer);


    }
}
