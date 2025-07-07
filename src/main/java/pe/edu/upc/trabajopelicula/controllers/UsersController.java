package pe.edu.upc.trabajopelicula.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.UsersDTO;
import pe.edu.upc.trabajopelicula.entities.Users;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IUserInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class UsersController {
    @Autowired
    private IUserInterface userInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/Registro")
    public ResponseEntity<UsersDTO> registrar(@RequestBody UsersDTO userDTO) {
        ModelMapper m = new ModelMapper();
        Users us = m.map(userDTO, Users.class);
        String encodedPassword = passwordEncoder.encode(us.getPassword());
        us.setPassword(encodedPassword);
        Users newUser = userInterface.insert(us);
        UsersDTO userResponse = m.map(newUser, UsersDTO.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping //listar
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<UsersDTO> list(){
        return userInterface.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, UsersDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Long id, @RequestBody UsersDTO u){
        ModelMapper m = new ModelMapper();
        Users uh = m.map(u, Users.class);
        uh.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        userInterface.update(uh);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Long id){
        userInterface.delete(id);
    }

    @GetMapping("/{id}")
    public UsersDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        UsersDTO dto = m.map(userInterface.listarId(id), UsersDTO.class);
        return dto;
    }

    @GetMapping("/role/{roleName}")
    public List<UsersDTO> listByRole(@PathVariable("roleName") String roleName) {
        return userInterface.listByRole(roleName).stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, UsersDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/nombreusuario")
    public UsersDTO encontraruser(@RequestParam String nombreuser){
        ModelMapper m = new ModelMapper();
        UsersDTO dto = m.map(userInterface.finduser(nombreuser), UsersDTO.class);
        return dto;
    }

}
