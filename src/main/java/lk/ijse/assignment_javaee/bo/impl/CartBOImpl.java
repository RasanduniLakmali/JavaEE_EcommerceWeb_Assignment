package lk.ijse.assignment_javaee.bo.impl;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.bo.CartBO;
import lk.ijse.assignment_javaee.dao.CartDAO;
import lk.ijse.assignment_javaee.dao.DAOFactory;
import lk.ijse.assignment_javaee.dto.CartDTO;
import lk.ijse.assignment_javaee.entity.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartBOImpl implements CartBO {

    CartDAO cartDAO = (CartDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CART);

    @Override
    public boolean saveCart(CartDTO cartDTO, HttpServletRequest request) {
        try {
            Cart cart = new Cart(cartDTO.getUser(), cartDTO.getProductName(), cartDTO.getPrice(), cartDTO.getImageUrl(), cartDTO.getQuantity(), cartDTO.getTotalPrice());

            // Let the CartDAO handle the database interaction
            boolean isSaved = cartDAO.save(cart, request);

            return isSaved;

        } catch (Exception e) {
            // Handle exception (log, rollback transaction - this might be handled in the DAO)
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CartDTO> getAllCartItems(HttpServletRequest request) {
        List<Cart> cartList = cartDAO.getAll(request);

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (Cart cart : cartList) {
            CartDTO cartDTO = new CartDTO(cart.getUser(), cart.getProductName(), cart.getPrice(), cart.getImageUrl(), cart.getQuantity(), cart.getTotalPrice());
            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }

    @Override
    public boolean removeCartItem(String email, HttpServletRequest request) {
        return cartDAO.deleteCart(email, request);
    }

    @Override
    public List<CartDTO> getCartItemsByEmail(String email, HttpServletRequest request) {
        List<Cart> cartList = cartDAO.getByEmail(email, request);

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (Cart cart : cartList) {
            CartDTO cartDTO = new CartDTO(cart.getUser(), cart.getProductName(), cart.getPrice(), cart.getImageUrl(), cart.getQuantity(), cart.getTotalPrice());
            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }

    @Override
    public boolean updateCart(CartDTO cartDTO, HttpServletRequest req) {
        Cart cart = cartDAO.getCartByUserProductName(cartDTO.getUser().getEmail(), cartDTO.getProductName(), req);

        if (cart != null) {
            cart.setQuantity(cartDTO.getQuantity());
            cart.setTotalPrice(cartDTO.getTotalPrice());
            return cartDAO.updateCartDetail(cart, req);
        }
        return false;
    }

    @Override
    public CartDTO getCartByUserAndProductName(String userEmail, String productName, HttpServletRequest request) {
        Cart cart = cartDAO.getCartByUserProductName(userEmail, productName, request);
        if (cart != null) {
            return new CartDTO(
                    cart.getUser(),
                    cart.getProductName(),
                    cart.getPrice(),
                    cart.getImageUrl(),
                    cart.getQuantity(),
                    cart.getTotalPrice()
            );
        }
        return null;
    }



//    @Override
//    public boolean updateCartQuantity(int cartId, int quantityChange, HttpServletRequest req) {
//
//        Cart cartItem = cartDAO.getCartItemById(cartId, req);
//        if (cartItem == null) {
//            return false; // No item found for the given cartId
//        }
//
//        double newTotalPrice = cartItem.getPrice() * quantityChange; // Recalculate total price
//        cartItem.setQuantity(quantityChange);
//        cartItem.setTotalPrice(newTotalPrice);
//
//        return cartDAO.updateCart(cartItem, req);
//    }
//}
}