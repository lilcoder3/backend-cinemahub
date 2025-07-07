package pe.edu.upc.trabajopelicula.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TotalRevenueByPaymentDateDTO {
    private String paymentType;
    private double total;
}
