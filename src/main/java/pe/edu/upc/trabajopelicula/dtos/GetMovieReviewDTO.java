package pe.edu.upc.trabajopelicula.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GetMovieReviewDTO {
    private String movie;
    private int totalReviews;
    private double averageRating;
}
