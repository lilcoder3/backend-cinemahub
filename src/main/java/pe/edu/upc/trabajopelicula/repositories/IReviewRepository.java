package pe.edu.upc.trabajopelicula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajopelicula.entities.Review;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review,Integer> {
    @Query(value = "SELECT m.namemovie, COUNT(r.id) AS total_reviews, ROUND(AVG(r.points), 2) AS avg_rating " +
            "FROM review r " +
            "JOIN movies m ON r.movies_id = m.id " +
            "WHERE m.namemovie = :movieName " +
            "GROUP BY m.namemovie", nativeQuery = true)
    public List<String[]> getMovieReviewStats(@Param("movieName") String movieName);

}
