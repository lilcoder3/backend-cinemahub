package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.trabajopelicula.dtos.MoviesDTO;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IMoviesInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movieregister")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class RegisterController {
    @Autowired
    private IMoviesInterface moviesInterface;

    @GetMapping ("/listar")//listar
    public List<MoviesDTO> list() {
        return moviesInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, MoviesDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }
}
