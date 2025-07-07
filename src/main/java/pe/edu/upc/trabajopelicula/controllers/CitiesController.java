package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.CitiesDTO;
import pe.edu.upc.trabajopelicula.entities.Cities;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ICitiesInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class CitiesController {

    @Autowired
    private ICitiesInterface citiesInterface;

    @PostMapping("/Registro") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody CitiesDTO a) {
        ModelMapper m = new ModelMapper();
        Cities ch = m.map(a, Cities.class);
        citiesInterface.insert(ch);
    }

    @GetMapping //listar
    public List<CitiesDTO> list() {
        return citiesInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, CitiesDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody CitiesDTO a) {
        ModelMapper m = new ModelMapper();
        Cities ah = m.map(a, Cities.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        citiesInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        citiesInterface.delete(id);
    }

    @GetMapping("/{id}")
    public CitiesDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        CitiesDTO dto = m.map(citiesInterface.listarId(id), CitiesDTO.class);
        return dto;
    }
}
