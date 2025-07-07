package pe.edu.upc.trabajopelicula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajopelicula.entities.Roles;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Roles,Long> {
    List<Roles> findByUserId(Long userId);
    boolean existsByUserAndRol(pe.edu.upc.trabajopelicula.entities.Users user, String rol);

}
