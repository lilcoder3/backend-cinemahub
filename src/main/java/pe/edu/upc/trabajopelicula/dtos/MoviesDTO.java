package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoviesDTO {
    private int id;
    private String namemovie;
    private int yearmovie;
    private String typemovie;
    private String yearold;
    private String director;
    private String description;
    private String urlimage;
}
