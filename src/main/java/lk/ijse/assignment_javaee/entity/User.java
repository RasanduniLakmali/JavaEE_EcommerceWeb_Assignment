package lk.ijse.assignment_javaee.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class User {

    private String userType;
    private String name;
    private String password;
    @Id
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    public User(String userName, String password, String email){
        this.name = userName;
        this.password = password;
        this.email = email;
    }

    public User(String userType, String name, String password, String email){
        this.userType = userType;
        this.name = name;
        this.password = password;
        this.email = email;

    }
//    public User(String userType,String name,String email){
//        this.userType = userType;
//        this.name = name;
//        this.email = email;
//    }

}
