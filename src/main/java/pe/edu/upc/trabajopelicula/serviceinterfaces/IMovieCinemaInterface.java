package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.MovieCinema;
import pe.edu.upc.trabajopelicula.entities.Movies;

import java.util.List;

public interface IMovieCinemaInterface {
    public void insert(MovieCinema movieCinema);
    public List<MovieCinema> list();
    public void delete(int id);
    public void update(MovieCinema movieCinema);
    public MovieCinema listarId(int id);

    public List<String[]> countFunctionsByCinema();
    public List<String[]> countMoviesByCity();
    public List<String[]> findMovieSchedulesByCinema(String cinemaName);
}
