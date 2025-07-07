package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.Cinema;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ICinemaInterface;

import java.util.List;

@Service
public class CinemaServiceImplement implements ICinemaInterface {
    @Autowired
    private ICinemaRepository cR;

    @Override
    public void insert(Cinema cinema) {
        cR.save(cinema);
    }

    @Override
    public List<Cinema> list() {
        return cR.findAll();
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public void update(Cinema cinema) {
        cR.save(cinema);
    }

    @Override
    public Cinema listarId(int id) {
        return cR.findById(id).orElse(new Cinema());
    }
}
