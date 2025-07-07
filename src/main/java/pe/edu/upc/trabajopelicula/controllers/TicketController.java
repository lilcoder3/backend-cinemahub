package pe.edu.upc.trabajopelicula.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.FindTicketsUsernameDTO;
import pe.edu.upc.trabajopelicula.dtos.QuantityTotalRevenueByPaymentDTO;
import pe.edu.upc.trabajopelicula.dtos.TicketDTO;
import pe.edu.upc.trabajopelicula.dtos.TotalRevenueByPaymentDateDTO;
import pe.edu.upc.trabajopelicula.entities.Ticket;
import pe.edu.upc.trabajopelicula.serviceimplements.generatePDF;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ITicketInterface;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class TicketController {
    @Autowired
    private ITicketInterface ticketInterface;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody TicketDTO a) {
        ModelMapper m = new ModelMapper();
        Ticket ch = m.map(a, Ticket.class);
        ticketInterface.insert(ch);
    }

    @GetMapping //listar
    public List<TicketDTO> list() {
        return ticketInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, TicketDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody TicketDTO a) {
        ModelMapper m = new ModelMapper();
        Ticket ah = m.map(a, Ticket.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        ticketInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        ticketInterface.delete(id);
    }

    @GetMapping("/{id}")
    public TicketDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        TicketDTO dto = m.map(ticketInterface.listarId(id), TicketDTO.class);
        return dto;
    }

    @GetMapping("/{id}/boleta")
    public void generarBoleta(@PathVariable("id") int id, HttpServletResponse response) {
        try {
            Ticket ticket = ticketInterface.listarId(id);
            if (ticket == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=boleta_" + id + ".pdf");

            OutputStream out = response.getOutputStream();
            generatePDF.PDFGenerator.generatePDF(ticket, out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recaudacionPorMetodoPago")
    public List<QuantityTotalRevenueByPaymentDTO> recaudacionPorTipoPago() {
        List<String[]> rawList = ticketInterface.totalRevenueByPaymentType();
        List<QuantityTotalRevenueByPaymentDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            QuantityTotalRevenueByPaymentDTO dto = new QuantityTotalRevenueByPaymentDTO();
            dto.setPaymentType(row[0]);
            dto.setQuantity(Double.parseDouble(row[1]));
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/ticketsPorUsuario")
    public List<FindTicketsUsernameDTO> ticketsPorUsuario(@RequestParam("username") String username) {
        List<String[]> rawList = ticketInterface.findTicketsByUsername(username);
        List<FindTicketsUsernameDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            FindTicketsUsernameDTO dto = new FindTicketsUsernameDTO();
            dto.setUsername(row[0]);
            dto.setPay(Double.parseDouble(row[1]));
            dto.setDate(row[2]);
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/recaudacionPorMetodoYFecha")
    public List<TotalRevenueByPaymentDateDTO> recaudacionPorMetodoYFecha(@RequestParam("start") String start, @RequestParam("end") String end) {
        List<String[]> rawList = ticketInterface.totalRevenueByPaymentTypeAndDate(LocalDate.parse(start), LocalDate.parse(end));
        List<TotalRevenueByPaymentDateDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            TotalRevenueByPaymentDateDTO dto = new TotalRevenueByPaymentDateDTO();
            dto.setPaymentType(row[0]);
            dto.setTotal(Double.parseDouble(row[1]));
            result.add(dto);
        }
        return result;
    }


}
