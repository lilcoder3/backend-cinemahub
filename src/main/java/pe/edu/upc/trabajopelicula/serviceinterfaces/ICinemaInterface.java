package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.Cinema;
import pe.edu.upc.trabajopelicula.entities.CinemaRooms;

import java.util.List;

public interface ICinemaInterface {
    public void insert(Cinema cinema);
    public List<Cinema> list();
    public void delete(int id);
    public void update(Cinema cinema);
    public Cinema listarId(int id);
}
