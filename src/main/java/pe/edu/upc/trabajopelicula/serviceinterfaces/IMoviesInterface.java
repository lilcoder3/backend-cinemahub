package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.Movies;
import pe.edu.upc.trabajopelicula.entities.Review;

import java.util.List;

public interface IMoviesInterface {
    public void insert(Movies movies);
    public List<Movies> list();
    public void delete(int id);
    public void update(Movies movies);
    public Movies listarId(int id);
}
