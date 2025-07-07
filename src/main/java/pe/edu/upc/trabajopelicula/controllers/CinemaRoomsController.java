package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.CinemaRoomsDTO;
import pe.edu.upc.trabajopelicula.entities.CinemaRooms;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ICinemaRoomsInterface;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cinemaroom")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class CinemaRoomsController {

    @Autowired
    private ICinemaRoomsInterface cinemaRoomsInterface;

    @PostMapping("/insert") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody CinemaRoomsDTO a) {
        ModelMapper m = new ModelMapper();
        CinemaRooms ch = m.map(a, CinemaRooms.class);
        cinemaRoomsInterface.insert(ch);
    }

    @GetMapping //listar
    public List<CinemaRoomsDTO> list() {
        return cinemaRoomsInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, CinemaRoomsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody CinemaRoomsDTO a) {
        ModelMapper m = new ModelMapper();
        CinemaRooms ah = m.map(a, CinemaRooms.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        cinemaRoomsInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        cinemaRoomsInterface.delete(id);
    }

    @GetMapping("/{id}")
    public CinemaRoomsDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        CinemaRoomsDTO dto = m.map(cinemaRoomsInterface.listarId(id), CinemaRoomsDTO.class);
        return dto;
    }
}
