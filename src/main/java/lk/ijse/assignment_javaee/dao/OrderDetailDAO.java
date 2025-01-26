package lk.ijse.assignment_javaee.dao;

import lk.ijse.assignment_javaee.entity.Order_Detail;
import org.hibernate.Session;

public interface OrderDetailDAO extends CrudDAO<Order_Detail> {

    public boolean saveDetail(Order_Detail detail, Session session);
}
