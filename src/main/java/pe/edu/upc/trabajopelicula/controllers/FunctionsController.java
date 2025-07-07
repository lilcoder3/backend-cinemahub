package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.FunctionsDTO;
import pe.edu.upc.trabajopelicula.dtos.QuantityFunctionsUserDateDTO;
import pe.edu.upc.trabajopelicula.dtos.QuantityFunctionsUsersDTO;
import pe.edu.upc.trabajopelicula.dtos.QuantityTicketsCinemaDTO;
import pe.edu.upc.trabajopelicula.entities.Functions;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IFunctionsInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/functions")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class FunctionsController {

    @Autowired
    private IFunctionsInterface functionsInterface;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody FunctionsDTO a) {
        ModelMapper m = new ModelMapper();
        Functions ch = m.map(a, Functions.class);
        functionsInterface.insert(ch);
    }

    @GetMapping //listar
    public List<FunctionsDTO> list() {
        return functionsInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, FunctionsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody FunctionsDTO a) {
        ModelMapper m = new ModelMapper();
        Functions ah = m.map(a, Functions.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        functionsInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        functionsInterface.delete(id);
    }

    @GetMapping("/{id}")
    public FunctionsDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        FunctionsDTO dto = m.map(functionsInterface.listarId(id), FunctionsDTO.class);
        return dto;
    }

    @GetMapping("/cantidadFuncionesUsuario")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<QuantityFunctionsUsersDTO> cantidadFuncionesUsuario() {
        List<String[]> rawList = functionsInterface.countFunctionsByUser();
        List<QuantityFunctionsUsersDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            QuantityFunctionsUsersDTO dto = new QuantityFunctionsUsersDTO();
            dto.setUsername(row[0]);
            dto.setQuantity(Integer.parseInt(row[1]));
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/ticketsVendidosPorCine")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<QuantityTicketsCinemaDTO> ticketsPorCine() {
        List<String[]> rawList = functionsInterface.countTicketsByCinema();
        List<QuantityTicketsCinemaDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            QuantityTicketsCinemaDTO dto = new QuantityTicketsCinemaDTO();
            dto.setCinema(row[0]);
            dto.setQuantity(Integer.parseInt(row[1]));
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/funcionesPorUsuarioFecha")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<QuantityFunctionsUserDateDTO> funcionesPorUsuarioFecha(@RequestParam("username") String username) {
        List<String[]> rawList = functionsInterface.countFunctionsByUserAndDate(username);
        List<QuantityFunctionsUserDateDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            QuantityFunctionsUserDateDTO dto = new QuantityFunctionsUserDateDTO();
            dto.setUsername(row[0]);
            dto.setQuantity(Integer.parseInt(row[1]));
            dto.setDate(row[2]);
            result.add(dto);
        }
        return result;
    }
}
