package pe.edu.upc.trabajopelicula.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypePaymentsDTO {
    private int id;
    private String paymentname;
    private String urlimage;
}
