package pe.edu.upc.trabajopelicula.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "typepayments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypePayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "paymentname", nullable = false, length = 254)
    private String paymentname;
    @Column(name = "urlimage", nullable = false, length = 254)
    private String urlimage;
}
