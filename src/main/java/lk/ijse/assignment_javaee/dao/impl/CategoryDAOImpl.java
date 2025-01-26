package lk.ijse.assignment_javaee.dao.impl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.config.FactoryConfiguration;
import lk.ijse.assignment_javaee.dao.CategoryDAO;
import lk.ijse.assignment_javaee.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public boolean save(Category entity, HttpServletRequest request) {
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
    public boolean update(Category entity, HttpServletRequest request) {
        return false;
    }

    @Override
    public String getCurrentId(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select categoryId from Category ORDER BY categoryId DESC LIMIT 1");
        String currentId = (String) query.uniqueResult();

        transaction.commit();
        session.close();
        return currentId;
    }

    @Override
    public List<Category> getAll(HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Category ");
        List<Category> categories = query.list();
        transaction.commit();
        session.close();
        return categories;
    }

    @Override
    public boolean delete(String id, HttpServletRequest request) {
        return false;
    }

    @Override
    public Category search(String id, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Category where categoryId=?1");
        query.setParameter(1, id);
        Category category = (Category) query.uniqueResult();
        transaction.commit();
        return category;
    }

    @Override
    public Category searchByName(String name, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Category where categoryName=?1");
        query.setParameter(1, name);
        Category category = (Category) query.uniqueResult();
        transaction.commit();
        return category;
    }
}
