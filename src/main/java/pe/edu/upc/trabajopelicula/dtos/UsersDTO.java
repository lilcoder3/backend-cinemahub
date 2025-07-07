package pe.edu.upc.trabajopelicula.dtos;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajopelicula.entities.Roles;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsersDTO {
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Boolean enabled;
    private List<Roles> roles;
}
