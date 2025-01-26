package lk.ijse.assignment_javaee.dao.impl;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.dao.OrderDetailDAO;
import lk.ijse.assignment_javaee.entity.Order_Detail;
import org.hibernate.Session;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {


    @Override
    public boolean save(Order_Detail entity, HttpServletRequest request) {

        return false;
    }

    @Override
    public boolean update(Order_Detail entity, HttpServletRequest request) {
        return false;
    }

    @Override
    public String getCurrentId(HttpServletRequest request) {
        return "";
    }

    @Override
    public List<Order_Detail> getAll(HttpServletRequest request) {
        return List.of();
    }

    @Override
    public boolean delete(String id, HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean saveDetail(Order_Detail detail, Session session) {
        try {
            session.save(detail);
            return true;
        } catch (Exception e) {
            throw e;  // Re-throw for handling at a higher level
        }
    }
}
