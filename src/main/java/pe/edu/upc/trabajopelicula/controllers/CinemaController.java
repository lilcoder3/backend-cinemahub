package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.CinemaDTO;
import pe.edu.upc.trabajopelicula.entities.Cinema;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ICinemaInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinema")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class CinemaController {

    @Autowired
    private ICinemaInterface cinemaInterface;

    @PostMapping("/insert") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody CinemaDTO a) {
        ModelMapper m = new ModelMapper();
        Cinema ch = m.map(a, Cinema.class);
        cinemaInterface.insert(ch);
    }

    @GetMapping //listar
    public List<CinemaDTO> list() {
        return cinemaInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, CinemaDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody CinemaDTO a) {
        ModelMapper m = new ModelMapper();
        Cinema ah = m.map(a, Cinema.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        cinemaInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        cinemaInterface.delete(id);
    }

    @GetMapping("/{id}")
    public CinemaDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        CinemaDTO dto = m.map(cinemaInterface.listarId(id), CinemaDTO.class);
        return dto;
    }
}
