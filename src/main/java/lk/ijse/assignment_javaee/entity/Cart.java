package lk.ijse.assignment_javaee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false)
    private User user;

    private String productName;
    private double price;
    private String imageUrl;
    private int quantity;
    private double totalPrice;

    public Cart(User user, String productName, double price, String imageUrl, int quantity, double totalPrice) {
        this.user = user;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.totalPrice = totalPrice;

    }
}
