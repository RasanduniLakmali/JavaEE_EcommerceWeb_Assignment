package lk.ijse.assignment_javaee.config;

import lk.ijse.assignment_javaee.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

//    private static FactoryConfiguration factoryConfiguration;
//    private static SessionFactory sessionFactory;
//
//    private FactoryConfiguration(){
//        Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Category.class).addAnnotatedClass(Product.class).addAnnotatedClass(Cart.class).addAnnotatedClass(Orders.class).addAnnotatedClass(Order_Detail.class);
//        sessionFactory = configuration.buildSessionFactory();
//    }
//
//    public static FactoryConfiguration getInstance(){
//        return (factoryConfiguration == null)?factoryConfiguration=
//                new FactoryConfiguration():factoryConfiguration;
//    }
//
//    public Session getSession(){
//        return sessionFactory.openSession();
//    }


    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            synchronized (FactoryConfiguration.class) {
                if (factoryConfiguration == null) {
                    factoryConfiguration = new FactoryConfiguration();
                }
            }
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
