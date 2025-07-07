package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.Movies;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.IMoviesRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IMoviesInterface;

import java.util.List;

@Service
public class MovieServiceImplement  implements IMoviesInterface {
    @Autowired
    private IMoviesRepository mR;

    @Override
    public void insert(Movies movies) {
        mR.save(movies);
    }

    @Override
    public List<Movies> list() {
        return mR.findAll();
    }

    @Override
    public void delete(int id) {
        mR.deleteById(id);
    }

    @Override
    public void update(Movies movies) {
        mR.save(movies);
    }

    @Override
    public Movies listarId(int id) {
        return mR.findById(id).orElse(new Movies());
    }
}
