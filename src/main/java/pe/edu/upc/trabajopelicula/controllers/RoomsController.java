package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.RoomsDTO;
import pe.edu.upc.trabajopelicula.entities.Rooms;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IRoomsInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class RoomsController {
    @Autowired
    private IRoomsInterface roomsInterface;

    @PostMapping("/Registro") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody RoomsDTO a) {
        ModelMapper m = new ModelMapper();
        Rooms ch = m.map(a, Rooms.class);
        roomsInterface.insert(ch);
    }

    @GetMapping //listar
    public List<RoomsDTO> list() {
        return roomsInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, RoomsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody RoomsDTO a) {
        ModelMapper m = new ModelMapper();
        Rooms ah = m.map(a, Rooms.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        roomsInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        roomsInterface.delete(id);
    }

    @GetMapping("/{id}")
    public RoomsDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        RoomsDTO dto = m.map(roomsInterface.listarId(id), RoomsDTO.class);
        return dto;
    }
}
