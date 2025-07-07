package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajopelicula.entities.Movies;
import pe.edu.upc.trabajopelicula.entities.Users;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDTO {
    private int id;
    private LocalDateTime reviewdate;
    private String descriptions;
    private int points;
    private Movies movies_id;
    private Users user_id;
}
