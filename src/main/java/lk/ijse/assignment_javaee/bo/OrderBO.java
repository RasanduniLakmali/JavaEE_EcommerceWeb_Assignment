package lk.ijse.assignment_javaee.bo;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.assignment_javaee.dto.OrderDTO;

public interface OrderBO extends SuperBO{

    public boolean saveOrder(OrderDTO orderDTO, HttpServletRequest request);

    public  boolean placeOrder(OrderDTO orderDTO,String productName,int quantity,double productTotal,HttpServletRequest request);
}
