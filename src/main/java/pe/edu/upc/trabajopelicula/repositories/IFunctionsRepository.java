package pe.edu.upc.trabajopelicula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajopelicula.entities.Functions;

import java.util.List;

@Repository
public interface IFunctionsRepository extends JpaRepository<Functions,Integer> {
    //List<Functions> findByUser_Id(Long userId);  // Cambiado 'userId' por 'user_Id'
    @Query(value = "SELECT ut.username, COUNT(f.id) AS quantity " +
            "FROM functions f " +
            "JOIN user_table ut ON f.user_id = ut.id " +
            "GROUP BY ut.username", nativeQuery = true)
    public List<String[]> countFunctionsByUser();

    @Query(value = "SELECT c.localname AS cinema, COUNT(t.id) AS quantity " +
            "FROM ticket t " +
            "JOIN functions f ON f.id = t.functions_id " +
            "JOIN moviecinema mc ON f.moviecinema_id = mc.id " +
            "JOIN cinemarooms cr ON mc.cinemarooms_id = cr.id " +
            "JOIN cinema c ON cr.cinema_id = c.id " +
            "GROUP BY c.localname", nativeQuery = true)
    public List<String[]> countTicketsByCinema();

    @Query(value = "SELECT ut.username, COUNT(f.id) AS funciones, mc.startinghour::date AS fecha " +
            "FROM functions f " +
            "JOIN moviecinema mc ON f.moviecinema_id = mc.id " +
            "JOIN user_table ut ON ut.id = f.user_id " +
            "WHERE ut.username = :username " +
            "GROUP BY ut.username, mc.startinghour::date", nativeQuery = true)
    List<String[]> countFunctionsByUserAndDate(@Param("username") String username);

}
