package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.Roles;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.IRoleRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IRoleInterface;

import java.util.List;

@Service
public class RoleServiceImplement implements IRoleInterface {
    @Autowired
    private IRoleRepository roR;

    @Override
    public void insert(Roles roles) {
        roR.save(roles);
    }

    @Override
    public List<Roles> list() {
        return roR.findAll();
    }

    @Override
    public void delete(Long id) {
        roR.deleteById(id);
    }

    @Override
    public void update(Roles roles) {
        roR.save(roles);
    }

    @Override
    public Roles listarId(Long id) {
        return roR.findById(id).orElse(new Roles());
    }
}
