package pe.edu.upc.trabajopelicula.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "moviecinema")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieCinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "startinghour", nullable = false, length = 254)
    private LocalDateTime startinghour;
    @Column(name = "endinghour", nullable = false, length = 254)
    private LocalDateTime endinghour;

    @ManyToOne
    @JoinColumn(name = "cinemarooms_id", nullable = false)
    private CinemaRooms cinemarooms_id;

    @ManyToOne
    @JoinColumn(name = "movies_id", nullable = false)
    private Movies movies_id;

}
