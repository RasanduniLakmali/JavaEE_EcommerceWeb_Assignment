package lk.ijse.assignment_javaee.dao.impl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.config.FactoryConfiguration;
import lk.ijse.assignment_javaee.dao.*;
import lk.ijse.assignment_javaee.entity.Order_Detail;
import lk.ijse.assignment_javaee.entity.Orders;
import lk.ijse.assignment_javaee.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;



public class OrderDAOImpl implements OrderDAO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    @Override
    public boolean save(Orders entity, HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean update(Orders entity, HttpServletRequest request) {
        return false;
    }

    @Override
    public String getCurrentId(HttpServletRequest request) {
        return "";
    }

    @Override
    public List<Orders> getAll(HttpServletRequest request) {
        return List.of();
    }

    @Override
    public boolean delete(String id, HttpServletRequest request) {
        return false;
    }

//    @Override
//    public boolean placeOrderDetail(Orders orders, String productName, int quantity, double productTotal, HttpServletRequest request) {
//
//        ServletContext servletContext = request.getServletContext();
//        SessionFactory sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactory");
//
//        Session session = null;
//        Transaction transaction = null;
//
//        try {
//            session = sessionFactory.openSession();
//            // Begin a transaction
//            transaction = session.beginTransaction();
//
//            OrderDAO orderDAO = new OrderDAOImpl();
//            boolean isSaved = orderDAO.save(orders,request);
//
//            if (!isSaved) {
//               transaction.rollback();
//               session.close();
//               return false;
//            }
//
//            Orders order = orderDAO.getOrders(orders.getUser().getEmail(),request);
//
//            Product product = productDAO.getAllByName(productName,request);
//
//            Order_Detail orderDetail = new Order_Detail(order,product,productName,quantity,productTotal);
//
//            OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
//
//            boolean isDetailSaved = orderDetailDAO.save(orderDetail,request);
//
//            if (!isDetailSaved) {
//                transaction.rollback();
//                session.close();
//                return false;
//            }
//
//            Product product1 = productDAO.search(productName,request);
//            product1.setProductQty(product1.getProductQty() - quantity);
//
//            boolean isUpdated = productDAO.update(product1,request);
//
//            if (!isUpdated) {
//                transaction.rollback();
//                session.close();
//                return false;
//            }
//
//            transaction.commit();
//            session.close();
//            return true;
//
//        } catch (HibernateException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    public boolean placeOrderDetail(Orders orders, String productName, int quantity, double productTotal, HttpServletRequest request) {

//        ServletContext servletContext = request.getServletContext();
//        SessionFactory sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactory");

        Session session = null;
        Transaction transaction = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            OrderDAO orderDAO = new OrderDAOImpl();
            ProductDAO productDAO = new ProductDAOImpl();
            OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

            // Use the same session for all operations
            boolean isSaved = orderDAO.saveOrder(orders, session);
            if (!isSaved) {
                transaction.rollback();
                return false;
            }

            Orders order = orderDAO.getOrders(orders.getUser().getEmail(), session);
            Product product = productDAO.getAllByName(productName, session);

            Order_Detail orderDetail = new Order_Detail(order, product, productName, quantity, productTotal);
            boolean isDetailSaved = orderDetailDAO.saveDetail(orderDetail, session);
            if (!isDetailSaved) {
                transaction.rollback();
                return false;
            }

            // Update product quantity

            product.setProductQty(product.getProductQty() - quantity);

            boolean isUpdated = productDAO.updateProduct(product, session);

            if (!isUpdated) {
                transaction.rollback();
                return false;
            }

            // Commit and close
            transaction.commit();
            session.close();
            return true;

        }  catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Orders getOrders(String email, Session session) {
//        try {
//            Query query = session.createQuery("FROM Orders WHERE user.email = :email");
//            query.setParameter("email", email);
//            return (Orders) query.uniqueResult();
//        } catch (Exception e) {
//            throw e;  // Re-throw exception for handling at a higher level
//        }

        Query<Orders> query = session.createQuery("FROM Orders WHERE user.email = :email");
        query.setParameter("email", email);
        query.setMaxResults(1); // ✅ Ensures only one order is returned
        return query.uniqueResult();
    }


    @Override
    public boolean saveOrder(Orders orders, Session session) {
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();  // Start transaction
//            Object isSaved = session.save(orders);
//
//            if (isSaved != null) {
//                transaction.commit();  // Commit transaction
//                return true;
//            }
//
//            transaction.rollback();  // Rollback if save fails
//            return false;
//
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();  // Ensure rollback on failure
//            }
//            throw e;  // Re-throw exception for handling
//        }

        try {
            session.save(orders);
            return true;
        } catch (Exception e) {
            throw e;  // Re-throw for handling at a higher level
        }
    }


}
