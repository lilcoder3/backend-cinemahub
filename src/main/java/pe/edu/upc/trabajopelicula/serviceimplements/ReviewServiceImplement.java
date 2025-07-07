package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.Review;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.IReviewRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IReviewInterface;

import java.util.List;

@Service
public class ReviewServiceImplement implements IReviewInterface {
    @Autowired
    private IReviewRepository rR;

    @Override
    public void insert(Review review) {
        rR.save(review);
    }

    @Override
    public List<Review> list() {
        return rR.findAll();
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public void update(Review review) {
        rR.save(review);
    }

    @Override
    public Review listarId(int id) {
        return rR.findById(id).orElse(new Review());
    }

    @Override
    public List<String[]> getMovieReviewStats(String movieName) {
        return rR.getMovieReviewStats(movieName);
    }
}
