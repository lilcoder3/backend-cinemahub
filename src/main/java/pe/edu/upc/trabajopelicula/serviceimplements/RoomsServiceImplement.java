package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.Rooms;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.IRoomsRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IRoomsInterface;

import java.util.List;

@Service
public class RoomsServiceImplement implements IRoomsInterface {
    @Autowired
    private IRoomsRepository romR;

    @Override
    public void insert(Rooms rooms) {
        romR.save(rooms);
    }

    @Override
    public List<Rooms> list() {
        return romR.findAll();
    }

    @Override
    public void delete(int id) {
        romR.deleteById(id);
    }

    @Override
    public void update(Rooms rooms) {
        romR.save(rooms);
    }

    @Override
    public Rooms listarId(int id) {
        return romR.findById(id).orElse(new Rooms());
    }
}
