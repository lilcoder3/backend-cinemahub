package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.Cities;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.ICitiesRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ICitiesInterface;

import java.io.Serial;
import java.util.List;

@Service
public class CitiesServiceImplements implements ICitiesInterface {
    @Autowired
    private ICitiesRepository ciR;

    @Override
    public void insert(Cities cities) {
        ciR.save(cities);
    }

    @Override
    public List<Cities> list() {
        return ciR.findAll();
    }

    @Override
    public void delete(int id) {
        ciR.deleteById(id);
    }

    @Override
    public void update(Cities cities) {
        ciR.save(cities);
    }

    @Override
    public Cities listarId(int id) {
        return ciR.findById(id).orElse(new Cities());
    }
}
