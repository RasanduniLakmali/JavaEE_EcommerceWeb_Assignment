package lk.ijse.assignment_javaee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private String userType;
    private String name;
    private String password;
    private String email;

    public UserDTO(String userName,String password,String email){
        this.name = userName;
        this.password = password;
        this.email = email;

    }

    public UserDTO(String userName,String email){
        this.name = userName;
        this.email = email;
    }

}


