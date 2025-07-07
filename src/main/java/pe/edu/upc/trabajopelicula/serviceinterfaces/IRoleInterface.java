package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.Roles;

import java.util.List;

public interface IRoleInterface {
    public void insert(Roles roles);
    public List<Roles> list();
    public void delete(Long id);
    public void update(Roles roles);
    public Roles listarId(Long id);
}
