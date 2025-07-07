package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomsDTO {
    private int id;
    private String nameroom;
}
