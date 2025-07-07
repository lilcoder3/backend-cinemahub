package pe.edu.upc.trabajopelicula.serviceimplements;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.trabajopelicula.entities.Roles;
import pe.edu.upc.trabajopelicula.entities.Users;
import pe.edu.upc.trabajopelicula.repositories.IRoleRepository;
import pe.edu.upc.trabajopelicula.repositories.IUserRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IUserInterface;

import java.util.List;

@Service
public class UserServiceImplement implements IUserInterface {

    @Autowired
    private IUserRepository uR;

    @Autowired
    private IRoleRepository rR;

    @Override
    @Transactional
    public Users insert(Users user) {
        // Guardar el usuario
        Users savedUser = uR.save(user);

        // Validar si ya tiene el rol "CLIENTE"
        boolean yaTieneRol = rR.existsByUserAndRol(savedUser, "CLIENTE");
        if (!yaTieneRol) {
            Roles role = new Roles();
            role.setRol("CLIENTE");
            role.setUser(savedUser);
            rR.save(role);
        }

        return savedUser;
    }

    @Override
    public List<Users> list() {
        return uR.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        List<Roles> roles = rR.findByUserId(id);
        if (!roles.isEmpty()) {
            rR.deleteAll(roles);
        }
        uR.deleteById(id);
    }

    @Override
    public void update(Users user) {
        uR.save(user);
    }

    @Override
    public Users listarId(Long id) {
        return uR.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Users finduser(String username) {
        return uR.findByUsername(username);
    }

    @Override
    public List<Users> listByRole(String roleName) {
        return uR.findByRoleName(roleName);
    }
}
