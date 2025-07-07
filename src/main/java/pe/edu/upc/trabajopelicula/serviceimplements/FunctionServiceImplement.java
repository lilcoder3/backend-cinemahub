package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.Functions;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.IFunctionsRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ICinemaInterface;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IFunctionsInterface;

import java.util.List;

@Service
public class FunctionServiceImplement implements IFunctionsInterface {
    @Autowired
    private IFunctionsRepository fR;

    @Override
    public void insert(Functions functions) {
        fR.save(functions);
    }

    @Override
    public List<Functions> list() {
        return fR.findAll();
    }

    @Override
    public void delete(int id) {
        fR.deleteById(id);
    }

    @Override
    public void update(Functions functions) {
        fR.save(functions);
    }

    @Override
    public Functions listarId(int id) {
        return fR.findById(id).orElse(new Functions());
    }

    @Override
    public List<String[]> countFunctionsByUser() {
        return fR.countFunctionsByUser();
    }

    @Override
    public List<String[]> countTicketsByCinema() {
        return fR.countTicketsByCinema();
    }

    @Override
    public List<String[]> countFunctionsByUserAndDate(String username) {
        return fR.countFunctionsByUserAndDate(username);
    }


}
