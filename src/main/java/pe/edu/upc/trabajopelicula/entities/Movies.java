package pe.edu.upc.trabajopelicula.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "namemovie", nullable = false, length = 254)
    private String namemovie;
    @Column(name = "yearmovie", nullable = false, length = 254)
    private int yearmovie;
    @Column(name = "typemovie", nullable = false, length = 254)
    private String typemovie;
    @Column(name = "yearold", nullable = false, length = 254)
    private String yearold;
    @Column(name = "director", nullable = false, length = 254)
    private String director;
    @Column(name = "description", nullable = false, length = 254)
    private String description;
    @Column(name = "urlimage", nullable = false, length = 254)
    private String urlimage;
}
