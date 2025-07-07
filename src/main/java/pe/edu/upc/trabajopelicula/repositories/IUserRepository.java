package pe.edu.upc.trabajopelicula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajopelicula.entities.Users;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<Users,Long> {
    public Users findByUsername(String nameUser);

    /*@Query("select count(u.username) from User u where u.username =:username")
    public int buscarUsername(@Param("username") String nombre);
    @Transactional
    @Modifying
    @Query(value = "insert into rol (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);*/
    @Query("SELECT u FROM Users u JOIN u.roles r WHERE r.rol = :roleName")
    List<Users> findByRoleName(@Param("roleName") String roleName);
}
