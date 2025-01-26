package lk.ijse.assignment_javaee.dao;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.entity.Product;
import org.hibernate.Session;

import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

    public Product search(String name, HttpServletRequest request);
//    public Product getById(String productId, HttpServletRequest request);
//    public String getProductID(String productName, HttpServletRequest request);

    public Product getAllByName(String productName, Session session);

    public Product searchByName(String productName, Session session);

    public  boolean updateProduct(Product product, Session session);

    public List<Product> getAllMenProducts(HttpServletRequest request);

    public List<Product> getAllWomenProducts(HttpServletRequest request);

    public List<Product> getAllKidsProducts(HttpServletRequest request);

    public Product searchById(String productId, HttpServletRequest request);
}
