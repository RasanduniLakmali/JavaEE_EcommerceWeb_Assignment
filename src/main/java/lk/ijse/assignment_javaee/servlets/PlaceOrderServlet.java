package lk.ijse.assignment_javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.assignment_javaee.bo.BOFactory;
import lk.ijse.assignment_javaee.bo.CartBO;
import lk.ijse.assignment_javaee.bo.OrderBO;
import lk.ijse.assignment_javaee.bo.UserBO;
import lk.ijse.assignment_javaee.dto.CartDTO;
import lk.ijse.assignment_javaee.dto.OrderDTO;
import lk.ijse.assignment_javaee.dto.UserDTO;
import lk.ijse.assignment_javaee.entity.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlaceOrderServlet",value = "/place-order")
public class PlaceOrderServlet extends HttpServlet {

    CartBO cartBO = (CartBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CART);

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         String customerEmail = req.getParameter("customer_email");
         String customerName = req.getParameter("customer_name");
         String mobile = req.getParameter("mobile");
         String address = req.getParameter("address");
         double orderTotal = Double.parseDouble(req.getParameter("total"));
         String paymentMethod = req.getParameter("payment");
         String productName = req.getParameter("productName");
         int quantity = Integer.parseInt(req.getParameter("productQty"));
         double productTotal = Double.parseDouble(req.getParameter("productTotal"));

         UserDTO userDTO = userBO.getUserByEmail(customerEmail,req);

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        OrderDTO orderDTO = new OrderDTO(user,customerName,mobile,address,orderTotal,paymentMethod);

        boolean isPlaced = orderBO.placeOrder(orderDTO,productName,quantity,productTotal,req);

        if (isPlaced) {

            cartBO.removeCartItem(customerEmail,req);

            resp.sendRedirect("placeOrder.jsp?message=Order placed successfully");
        }else {
            resp.sendRedirect("placeOrder.jsp?error=Order not placed");
        }
    }

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

        // Set attributes for JSP
        req.setAttribute("cartItems", cartItems);


        // Forward to JSP
        req.getRequestDispatcher("placeOrder.jsp").forward(req, resp);
    }
}
