package lk.ijse.assignment_javaee.dto;

import lk.ijse.assignment_javaee.entity.Orders;
import lk.ijse.assignment_javaee.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {

    private Orders order;
    private Product product;
    private String productName;
    private int quantity;
    private double productTotal;
}
