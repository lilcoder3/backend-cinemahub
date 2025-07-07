package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.Rooms;

import java.util.List;

public interface IRoomsInterface {
    public void insert(Rooms rooms);
    public List<Rooms> list();
    public void delete(int id);
    public void update(Rooms rooms);
    public Rooms listarId(int id);
}
