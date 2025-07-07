package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.dtos.TicketDTO;
import pe.edu.upc.trabajopelicula.entities.Ticket;
import pe.edu.upc.trabajopelicula.repositories.ITicketRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ITicketInterface;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketServiceImplement implements ITicketInterface {

    @Autowired
    private ITicketRepository tR;

    @Override
    public void insert(Ticket ticket) {
        tR.save(ticket);
    }

    @Override
    public List<Ticket> list() {
        return tR.findAll();
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public void update(Ticket tickets) {
        tR.save(tickets);
    }

    /*@Override
    public TicketDTO getTicketById(Integer id) {
        return tR;
    }*/

    @Override
    public Ticket listarId(int id) {
        return tR.findById(id).orElse(new Ticket());
    }

    @Override
    public List<String[]> totalRevenueByPaymentType() {
        return tR.totalRevenueByPaymentType();
    }

    @Override
    public List<String[]> findTicketsByUsername(String username) {
        return tR.findTicketsByUsername(username);
    }

    @Override
    public List<String[]> totalRevenueByPaymentTypeAndDate(LocalDate start, LocalDate end) {
        return tR.totalRevenueByPaymentTypeAndDate(start, end);
    }
}
