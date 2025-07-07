package pe.edu.upc.trabajopelicula.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "reviewdate", nullable = false, length = 254)
    private LocalDateTime reviewdate;
    @Column(name = "descriptions", nullable = false, length = 254)
    private String descriptions;
    @Column(name = "points", nullable = false, length = 254)
    private int points;

    @ManyToOne
    @JoinColumn(name = "movies_id", nullable = false)
    private Movies movies_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user_id;

}
