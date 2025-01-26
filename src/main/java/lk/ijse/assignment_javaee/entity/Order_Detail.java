package lk.ijse.assignment_javaee.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String productName;
    private int quantity;
    private double productTotal;

    public Order_Detail(Orders order, Product product, String productName, int quantity, double productTotal) {
        this.order = order;
        this.product = product;
        this.productName = productName;
        this.quantity = quantity;
        this.productTotal = productTotal;
    }
}
