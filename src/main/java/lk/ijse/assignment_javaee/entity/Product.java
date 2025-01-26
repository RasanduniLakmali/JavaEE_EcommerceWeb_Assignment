package lk.ijse.assignment_javaee.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    private String productId;
    private String productName;
    private double productPrice;
    private int productQty;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cateoryId")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order_Detail> orderDetails = new ArrayList<>();


    public Product(String productId, String productName, double productPrice, int productQty, String image, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.image = image;
        this.category = category;
    }
}
