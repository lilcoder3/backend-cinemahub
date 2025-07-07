package pe.edu.upc.trabajopelicula.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FindMovieScheduleDTO {
    private String movie;
    private String cinema;
    private String startHour;
    private String endHour;
}
