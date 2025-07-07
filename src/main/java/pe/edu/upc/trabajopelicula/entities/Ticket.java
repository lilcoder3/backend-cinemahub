package pe.edu.upc.trabajopelicula.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "totalpay", nullable = false, length = 254)
    private double totalpay;
    @Column(name = "fechapago", nullable = false, length = 254)
    private LocalDate fechapago;

    @ManyToOne
    @JoinColumn(name = "typepayments_id", nullable = false)
    private TypePayments typepayments_id;

    @ManyToOne
    @JoinColumn(name = "functions_id", nullable = false)
    private Functions functions_id;
}
