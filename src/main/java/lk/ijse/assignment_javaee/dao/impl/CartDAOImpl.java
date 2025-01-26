package lk.ijse.assignment_javaee.dao.impl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.config.FactoryConfiguration;
import lk.ijse.assignment_javaee.dao.CartDAO;
import lk.ijse.assignment_javaee.entity.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CartDAOImpl implements CartDAO {

    @Override
    public boolean save(Cart entity, HttpServletRequest request) {


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Object isSaved = session.save(entity);

        if (isSaved != null) {
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Cart entity, HttpServletRequest request) {
        return false;
    }

    @Override
    public String getCurrentId(HttpServletRequest request) {
        return "";
    }

    @Override
    public List<Cart> getAll(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Cart ");
        List<Cart> carts = query.list();
        transaction.commit();
        session.close();
        return carts;
    }

    @Override
    public boolean delete(String email, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Cart cart = session.get(Cart.class, email);

        if (cart != null) {
            session.delete(cart);
            transaction.commit();
            session.close();
            return true;
        }
      return false;
    }

    @Override
    public List<Cart> getByEmail(String email, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Cart where user.email=?1");
        query.setParameter(1,email);
        List<Cart> carts = query.list();
        transaction.commit();
        session.close();
        return carts;
    }

    @Override
    public Cart getCartByUserProductName(String userEmail, String productName, HttpServletRequest req) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Cart> query = session.createQuery(
                "FROM Cart WHERE user.email = :email AND productName = :productName", Cart.class);
        query.setParameter("email", userEmail);
        query.setParameter("productName", productName);

        return query.uniqueResult();
    }

    @Override
    public boolean updateCartDetail(Cart cart, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(cart); // Updates existing cart by matching cartId
        transaction.commit();
        return true;
    }

    @Override
    public boolean deleteCart(String email, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from Cart where user.email = :email");
        query.setParameter("email", email);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public Cart getCart(String userEmail, String productName, HttpServletRequest req) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Cart> query = session.createQuery("FROM Cart c WHERE c.user.email = :userEmail AND c.productName = :productName", Cart.class);
        query.setParameter("userEmail", userEmail);
        query.setParameter("productName", productName);

        return query.uniqueResult();

    }

    @Override
    public Cart saveDetail(Cart cart, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.beginTransaction();

            cart = (Cart) session.merge(cart); // Use session.merge()

            session.getTransaction().commit();

            return cart;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }



//    @Override
//    public boolean updateCart(CartDTO cartDTO, HttpServletRequest req) {
////        ServletContext servletContext = req.getServletContext();
////        SessionFactory sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactory");
////        Session session = sessionFactory.openSession();
////        Transaction transaction = session.beginTransaction();
////
////        Query query = session.createQuery("UPDATE Cart SET quantity = ?, totalPrice = ? WHERE id = ?");
////        query.setParameter(1, cartDTO.getQuantity());
////        query.setParameter(2, cartDTO.getTotalPrice());
////        query.setParameter(3,cartDTO.)
//    }
}
