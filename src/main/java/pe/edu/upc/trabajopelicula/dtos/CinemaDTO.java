package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajopelicula.entities.Cities;

@Getter
@Setter
public class CinemaDTO {
    private int id;
    private String localname;
    private String urlimage;
    private Cities cities;
}
