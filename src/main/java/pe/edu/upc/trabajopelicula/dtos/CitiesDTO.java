package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitiesDTO {
    private int id;
    private String namecity;
}
