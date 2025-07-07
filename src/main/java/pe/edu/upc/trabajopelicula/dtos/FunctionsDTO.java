package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajopelicula.entities.MovieCinema;
import pe.edu.upc.trabajopelicula.entities.Users;

@Getter
@Setter
public class FunctionsDTO {
    private int id;
    private int totalchair;

    private MovieCinema moviecinema_id;

    private Users user_id;

}
