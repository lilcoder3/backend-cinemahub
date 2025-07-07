package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.CinemaRooms;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRoomsRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ICinemaRoomsInterface;

import java.util.List;

@Service
public class CinemaRoomsServiceImplement implements ICinemaRoomsInterface {
    @Autowired
    private ICinemaRoomsRepository coR;

    @Override
    public void insert(CinemaRooms cinemaRooms) {
        coR.save(cinemaRooms);
    }

    @Override
    public List<CinemaRooms> list() {
        return coR.findAll();
    }

    @Override
    public void delete(int id) {
        coR.deleteById(id);
    }

    @Override
    public void update(CinemaRooms cinemaRooms) {
        coR.save(cinemaRooms);
    }

    @Override
    public CinemaRooms listarId(int id) {
        return coR.findById(id).orElse(new CinemaRooms());
    }
}
