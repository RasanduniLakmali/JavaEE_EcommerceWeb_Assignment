package lk.ijse.assignment_javaee.dao.impl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.config.FactoryConfiguration;
import lk.ijse.assignment_javaee.dao.ProductDAO;
import lk.ijse.assignment_javaee.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean save(Product entity, HttpServletRequest request) {
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
    public boolean update(Product entity, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String getCurrentId(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select productId from Product ORDER BY productId DESC LIMIT 1");
        String currentId = (String) query.uniqueResult();

        transaction.commit();
        session.close();
        return currentId;
    }

    @Override
    public List<Product> getAll(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Product ");
        List<Product> products = query.list();
        transaction.commit();
        session.close();
        return products;
    }

    @Override
    public boolean delete(String id, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {

            session.delete(product);
            transaction.commit(); // Commit the transaction if successful
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public Product search(String name, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Product where productName =?1");
        query.setParameter(1, name);
        Product product = (Product) query.uniqueResult();
        transaction.commit();
        return product;
    }

//    @Override
//    public Product getById(String productId, HttpServletRequest request) {
//        ServletContext servletContext = request.getServletContext();
//        SessionFactory sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactory");
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Query query = session.createQuery("from Product where productId =?1");
//        query.setParameter(1, productId);
//        Product product = (Product) query.uniqueResult();
//        transaction.commit();
//        return product;
//    }

//    @Override
//    public String getProductID(String productName, HttpServletRequest request) {
//        ServletContext servletContext = request.getServletContext();
//        SessionFactory sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactory");
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Query query = session.createQuery("select productId from Product where productName =?1");
//        query.setParameter(1, productName);
//        String productID = (String) query.uniqueResult();
//        transaction.commit();
//        session.close();
//        return productID;
//    }

    @Override
    public Product getAllByName(String productName, Session session) {
        try {
            Query query = session.createQuery("FROM Product WHERE productName = :productName");
            query.setParameter("productName", productName);
            return (Product) query.uniqueResult();
        } catch (Exception e) {
            throw e;  // Re-throw exception for handling at a higher level
        }
    }

    @Override
    public Product searchByName(String productName, Session session) {
        Query query = session.createQuery("from Product where productName = :p");
        query.setParameter("productName", productName);
        return (Product) query.uniqueResult();
    }

    @Override
    public boolean updateProduct(Product product, Session session) {
        session.update(product);
        return true;
    }

    @Override
    public List<Product> getAllMenProducts(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Product> products = session.createQuery("from Product where category.categoryId = :categoryId", Product.class)
                .setParameter("categoryId", "C004")
                .getResultList();

        transaction.commit();
        session.close();
        return products;
    }

    @Override
    public List<Product> getAllWomenProducts(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Product> products = session.createQuery("from Product where category.categoryId = :categoryId", Product.class)
                .setParameter("categoryId", "C005")
                .getResultList();

        transaction.commit();
        session.close();
        return products;
    }

    @Override
    public List<Product> getAllKidsProducts(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Product> products = session.createQuery("from Product where category.categoryId = :categoryId", Product.class)
                .setParameter("categoryId", "C006")
                .getResultList();

        transaction.commit();
        session.close();
        return products;
    }

    @Override
    public Product searchById(String productId, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Product where productId =?1");
        query.setParameter(1, productId);
        Product product = (Product) query.uniqueResult();
        transaction.commit();
        return product;
    }
}
