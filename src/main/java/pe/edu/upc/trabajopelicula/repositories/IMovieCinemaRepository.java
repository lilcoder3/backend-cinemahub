package pe.edu.upc.trabajopelicula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajopelicula.entities.MovieCinema;

import java.util.List;

@Repository
public interface IMovieCinemaRepository extends JpaRepository<MovieCinema, Integer> {
    @Query(value = "SELECT c.localname AS cinema, COUNT(f.id) AS quantity " +
            "FROM functions f " +
            "JOIN moviecinema mc ON f.moviecinema_id = mc.id " +
            "JOIN cinemarooms cr ON mc.cinemarooms_id = cr.id " +
            "JOIN cinema c ON cr.cinema_id = c.id " +
            "GROUP BY c.localname", nativeQuery = true)
    public List<String[]> countFunctionsByCinema();

    @Query(value = "SELECT ct.namecity AS city, COUNT(DISTINCT m.id) AS quantity " +
            "FROM movies m " +
            "JOIN moviecinema mc ON mc.movies_id = m.id " +
            "JOIN cinemarooms cr ON cr.id = mc.cinemarooms_id " +
            "JOIN cinema c ON c.id = cr.cinema_id " +
            "JOIN cities ct ON ct.id = c.cities_id " +
            "GROUP BY ct.namecity", nativeQuery = true)
    public List<String[]> countMoviesByCity();

    @Query(value = "SELECT m.namemovie, c.localname AS cine, mc.startinghour, mc.endinghour " +
            "FROM moviecinema mc " +
            "JOIN movies m ON m.id = mc.movies_id " +
            "JOIN cinemarooms cr ON cr.id = mc.cinemarooms_id " +
            "JOIN cinema c ON c.id = cr.cinema_id " +
            "WHERE c.localname = :cinemaName", nativeQuery = true)
    List<String[]> findMovieSchedulesByCinema(@Param("cinemaName") String cinemaName);


}
