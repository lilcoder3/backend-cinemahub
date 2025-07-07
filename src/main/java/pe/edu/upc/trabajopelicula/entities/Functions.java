package pe.edu.upc.trabajopelicula.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "functions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Functions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "totalchair", nullable = false, length = 254)
    private int totalchair;

    @ManyToOne
    @JoinColumn(name = "moviecinema_id", nullable = false)
    private MovieCinema moviecinema_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user_id;

}
