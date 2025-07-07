package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.Review;
import pe.edu.upc.trabajopelicula.entities.Rooms;

import java.util.List;

public interface IReviewInterface {
    public void insert(Review review);
    public List<Review> list();
    public void delete(int id);
    public void update(Review review);
    public Review listarId(int id);

    public List<String[]> getMovieReviewStats(String movieName);

}
