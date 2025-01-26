package lk.ijse.assignment_javaee.dao;

import jakarta.persistence.criteria.Order;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.entity.Orders;
import org.hibernate.Session;

public interface OrderDAO extends CrudDAO<Orders> {

    public boolean placeOrderDetail(Orders orders, String productName, int quantity, double productTotal, HttpServletRequest request);

    public Orders getOrders(String email, Session session);

    public boolean saveOrder(Orders orders, Session session);
}
