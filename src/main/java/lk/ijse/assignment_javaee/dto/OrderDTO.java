package lk.ijse.assignment_javaee.dto;

import lk.ijse.assignment_javaee.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private User user;
    private String customer_name;
    private String mobile;
    private String address;
    private double orderTotal;
    private String paymentMethod;
}
