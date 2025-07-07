package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.MovieCinema;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.IMovieCinemaRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IMovieCinemaInterface;

import java.util.List;

@Service
public class MoviesCinemaServiceImplement implements IMovieCinemaInterface {
    @Autowired
    private IMovieCinemaRepository mcR;

    @Override
    public void insert(MovieCinema movieCinema) {
        mcR.save(movieCinema);
    }

    @Override
    public List<MovieCinema> list() {
        return mcR.findAll();
    }

    @Override
    public void delete(int id) {
        mcR.deleteById(id);
    }

    @Override
    public void update(MovieCinema movieCinema) {
        mcR.save(movieCinema);
    }

    @Override
    public MovieCinema listarId(int id) {
        return mcR.findById(id).orElse(new MovieCinema());
    }

    @Override
    public List<String[]> countFunctionsByCinema() {
        return mcR.countFunctionsByCinema();
    }

    @Override
    public List<String[]> countMoviesByCity() {
        return mcR.countMoviesByCity();
    }

    @Override
    public List<String[]> findMovieSchedulesByCinema(String cinemaName) {
        return mcR.findMovieSchedulesByCinema(cinemaName);
    }
}
