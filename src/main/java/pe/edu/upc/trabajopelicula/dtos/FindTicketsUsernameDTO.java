package pe.edu.upc.trabajopelicula.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FindTicketsUsernameDTO {
    private String username;
    private double pay;
    private String date;
}
