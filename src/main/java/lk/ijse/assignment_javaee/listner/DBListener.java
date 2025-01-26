package lk.ijse.assignment_javaee.listner;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import lk.ijse.assignment_javaee.config.FactoryConfiguration;
import org.hibernate.SessionFactory;


@WebListener
public class DBListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Hibernate SessionFactory Initialized with C3P0 Pooling...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Closing Hibernate Connection Pool...");
        FactoryConfiguration.getInstance().closeSessionFactory();
    }
}



