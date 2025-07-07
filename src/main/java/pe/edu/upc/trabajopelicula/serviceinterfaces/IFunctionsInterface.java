package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.Functions;
import pe.edu.upc.trabajopelicula.entities.MovieCinema;

import java.util.List;

public interface IFunctionsInterface {
    public void insert(Functions functions);
    public List<Functions> list();
    public void delete(int id);
    public void update(Functions functions);
    public Functions listarId(int id);

    public List<String[]> countFunctionsByUser();
    public List<String[]> countTicketsByCinema();
    public List<String[]> countFunctionsByUserAndDate(String username);
}
