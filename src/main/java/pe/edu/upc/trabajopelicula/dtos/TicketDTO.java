package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.trabajopelicula.entities.Functions;
import pe.edu.upc.trabajopelicula.entities.TypePayments;

import java.time.LocalDate;

@Getter
@Setter
public class TicketDTO {
    private int id;
    private double totalpay;
    private LocalDate fechapago;

    private TypePayments typepayments_id;

    private Functions functions_id;
}
