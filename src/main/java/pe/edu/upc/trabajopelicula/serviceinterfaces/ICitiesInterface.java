package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.Cities;
import pe.edu.upc.trabajopelicula.entities.Functions;

import java.util.List;

public interface ICitiesInterface {
    public void insert(Cities cities);
    public List<Cities> list();
    public void delete(int id);
    public void update(Cities cities);
    public Cities listarId(int id);
}
