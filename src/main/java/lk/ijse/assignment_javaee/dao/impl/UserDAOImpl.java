package lk.ijse.assignment_javaee.dao.impl;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.config.FactoryConfiguration;
import lk.ijse.assignment_javaee.dao.UserDAO;
import lk.ijse.assignment_javaee.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User user, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Object isSaved = session.save(user);

        if (isSaved != null) {
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public String getPasswordByEmail(String email, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u.password FROM User u WHERE u.email = :email";

        Query<String> query = session.createQuery(hql, String.class);
        query.setParameter("email", email);
        transaction.commit();
        return query.uniqueResult();
    }

    @Override
    public String getUser(String email, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select userType from User where email = :email");
        query.setParameter("email",email);

        String role = (String) query.uniqueResult();

        transaction.commit();
        session.close();
        return role;
    }

    @Override
    public String getName(String email, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select name from User where email = :email");
        query.setParameter("email",email);

        String name = (String) query.uniqueResult();

        transaction.commit();
        session.close();
        return name;
    }

    @Override
    public List<User> getAll(HttpServletRequest request,String user_type) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from User where userType = :user_type");
        List<User> customers = query.list();
        transaction.commit();
        session.close();
        return customers;
    }

    @Override
    public User getByEmail(String email, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where  email=?1");
        query.setParameter(1, email);
        User user = (User) query.uniqueResult();
        transaction.commit();
        return user;
    }

    @Override
    public boolean update(User user, HttpServletRequest request) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

       session.update(user);
       transaction.commit();
       session.close();

       return true;
    }

    @Override
    public String getCurrentId(HttpServletRequest request) {
        return "";
    }

    @Override
    public List<User> getAll(HttpServletRequest request) {
        return List.of();
    }

    @Override
    public boolean delete(String id, HttpServletRequest request) {
        return false;
    }
}
