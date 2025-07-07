package pe.edu.upc.trabajopelicula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajopelicula.entities.Rooms;

@Repository
public interface IRoomsRepository extends JpaRepository<Rooms, Integer> {
}
