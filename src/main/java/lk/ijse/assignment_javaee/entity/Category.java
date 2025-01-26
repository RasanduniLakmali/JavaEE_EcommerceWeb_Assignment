package lk.ijse.assignment_javaee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Category {

    @Id
    private String categoryId;
    private String categoryName;
    private int productQty;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    public Category(String categoryId, String categoryName, int productQty) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productQty = productQty;
    }
}
