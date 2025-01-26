package lk.ijse.assignment_javaee.dao;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.dto.CartDTO;
import lk.ijse.assignment_javaee.entity.Cart;

import java.util.List;

public interface CartDAO extends CrudDAO<Cart> {

    public List<Cart> getByEmail(String email, HttpServletRequest request);

    public Cart getCartByUserProductName(String userEmail, String productName, HttpServletRequest req);

    public boolean updateCartDetail(Cart cart,HttpServletRequest request);

    public boolean deleteCart(String email,HttpServletRequest request);

//    public boolean updateCart(CartDTO cartDTO, HttpServletRequest req);

    public Cart getCart(String userEmail,String productName, HttpServletRequest req);

//    public void updateCartDetail(String productName, HttpServletRequest req);

    public Cart saveDetail(Cart cart, HttpServletRequest request);
}
