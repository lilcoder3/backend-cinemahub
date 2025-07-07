package pe.edu.upc.trabajopelicula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.trabajopelicula.entities.Ticket;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Integer> {

    @Query(value = "SELECT tp.paymentname AS payment_type, SUM(t.totalpay) AS quantity " +
            "FROM ticket t " +
            "JOIN typepayments tp ON tp.id = t.typepayments_id " +
            "GROUP BY tp.paymentname", nativeQuery = true)
    public List<String[]> totalRevenueByPaymentType();

    @Query(value = "SELECT ut.username AS Usuario, t.totalpay AS Pago, t.fechapago AS Fecha " +
            "FROM ticket t " +
            "JOIN functions f ON t.functions_id = f.id " +
            "JOIN user_table ut ON ut.id = f.user_id " +
            "WHERE ut.username = :username", nativeQuery = true)
    List<String[]> findTicketsByUsername(@Param("username") String username);

    @Query(value = "SELECT tp.paymentname, SUM(t.totalpay) AS total " +
            "FROM ticket t " +
            "JOIN typepayments tp ON t.typepayments_id = tp.id " +
            "WHERE t.fechapago BETWEEN :start AND :end " +
            "GROUP BY tp.paymentname", nativeQuery = true)
    List<String[]> totalRevenueByPaymentTypeAndDate(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end);


}
