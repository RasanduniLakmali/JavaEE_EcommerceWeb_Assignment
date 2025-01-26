package lk.ijse.assignment_javaee.dto;


import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lk.ijse.assignment_javaee.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String productId;
    private String productName;
    private double productPrice;
    private int productQty;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

}
