package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.CinemaRooms;
import pe.edu.upc.trabajopelicula.entities.Cities;

import java.util.List;

public interface ICinemaRoomsInterface {
    public void insert(CinemaRooms cinemaRooms);
    public List<CinemaRooms> list();
    public void delete(int id);
    public void update(CinemaRooms cinemaRooms);
    public CinemaRooms listarId(int id);
}
