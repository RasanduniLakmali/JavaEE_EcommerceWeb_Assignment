package lk.ijse.assignment_javaee.dto;

import lk.ijse.assignment_javaee.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDTO {

    private User user;
    private String productName;
    private double price;
    private String imageUrl;
    private int quantity;
    private double totalPrice;

}
