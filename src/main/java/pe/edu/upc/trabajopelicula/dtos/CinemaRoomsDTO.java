package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajopelicula.entities.Cinema;
import pe.edu.upc.trabajopelicula.entities.Rooms;

@Getter
@Setter
public class CinemaRoomsDTO {
    private int id;

    private Cinema cinema;

    private Rooms rooms;
}
