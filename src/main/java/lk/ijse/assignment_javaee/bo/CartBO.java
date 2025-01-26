package lk.ijse.assignment_javaee.bo;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.dto.CartDTO;

import java.util.List;

public interface CartBO extends SuperBO{

    public boolean saveCart(CartDTO cartDTO, HttpServletRequest request);

    public List<CartDTO> getAllCartItems(HttpServletRequest request);

    public boolean removeCartItem(String email, HttpServletRequest request);

    public List<CartDTO> getCartItemsByEmail(String email,HttpServletRequest request);

    public boolean updateCart(CartDTO cartDTO, HttpServletRequest request);

    public CartDTO getCartByUserAndProductName(String userEmail, String productName, HttpServletRequest request);
//

}
