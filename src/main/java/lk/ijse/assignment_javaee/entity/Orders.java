package lk.ijse.assignment_javaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "customer_email", referencedColumnName = "email", nullable = false)
    private User user;
    private String customer_name;
    private String mobile;
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    private double orderTotal;
    private String paymentMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order_Detail> orderDetail= new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Orders(User user,String customer_name, String mobile, String address, double orderTotal, String paymentMethod){
        this.user = user;
        this.customer_name = customer_name;
        this.mobile = mobile;
        this.address = address;
        this.orderTotal = orderTotal;
        this.paymentMethod = paymentMethod;

    }
}
