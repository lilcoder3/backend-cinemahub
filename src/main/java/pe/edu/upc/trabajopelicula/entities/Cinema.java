package pe.edu.upc.trabajopelicula.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cinema")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "localname", nullable = false, length = 254)
    private String localname;
    @Column(name = "urlimage", nullable = false, length = 254)
    private String urlimage;

    @ManyToOne
    @JoinColumn(name = "cities_id", nullable = false)
    private Cities cities;
}
