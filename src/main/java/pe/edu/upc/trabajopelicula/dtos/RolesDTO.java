package pe.edu.upc.trabajopelicula.dtos;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajopelicula.entities.Users;

@Getter
@Setter
public class RolesDTO {
    private Long id;

    private String rol;

    private Users user;
}
