package pe.edu.upc.trabajopelicula.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class QuantityTotalRevenueByPaymentDTO {
    private String paymentType;
    private double quantity;
}
