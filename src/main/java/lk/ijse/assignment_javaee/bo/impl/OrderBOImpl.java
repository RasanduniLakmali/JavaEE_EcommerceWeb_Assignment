package lk.ijse.assignment_javaee.bo.impl;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.bo.OrderBO;
import lk.ijse.assignment_javaee.dao.DAOFactory;
import lk.ijse.assignment_javaee.dao.OrderDAO;
import lk.ijse.assignment_javaee.dto.OrderDTO;
import lk.ijse.assignment_javaee.entity.Orders;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean saveOrder(OrderDTO orderDTO, HttpServletRequest request) {
        return orderDAO.save(new Orders(orderDTO.getUser(),orderDTO.getCustomer_name(),orderDTO.getMobile(),orderDTO.getAddress(),orderDTO.getOrderTotal(),orderDTO.getPaymentMethod()),request);
    }

    @Override
    public boolean placeOrder(OrderDTO orderDTO, String productName, int quantity, double productTotal, HttpServletRequest request) {
        return  orderDAO.placeOrderDetail(new Orders(orderDTO.getUser(),orderDTO.getCustomer_name(),orderDTO.getMobile(),orderDTO.getAddress(),orderDTO.getOrderTotal(),orderDTO.getPaymentMethod()),productName,quantity,productTotal,request);
    }
}
