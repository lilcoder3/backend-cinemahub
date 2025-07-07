package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.MoviesDTO;
import pe.edu.upc.trabajopelicula.entities.Movies;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IMoviesInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class MoviesController {
    @Autowired
    private IMoviesInterface moviesInterface;

    @PostMapping("/Registro") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody MoviesDTO a) {
        ModelMapper m = new ModelMapper();
        Movies ch = m.map(a, Movies.class);
        moviesInterface.insert(ch);
    }

    @GetMapping //listar
    public List<MoviesDTO> list() {
        return moviesInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, MoviesDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody MoviesDTO a) {
        ModelMapper m = new ModelMapper();
        Movies ah = m.map(a, Movies.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        moviesInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        moviesInterface.delete(id);
    }

    @GetMapping("/{id}")
    public MoviesDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        MoviesDTO dto = m.map(moviesInterface.listarId(id), MoviesDTO.class);
        return dto;
    }
}
