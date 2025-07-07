package pe.edu.upc.trabajopelicula.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.RolesDTO;
import pe.edu.upc.trabajopelicula.entities.Roles;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IRoleInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RolesController {

    @Autowired
    private IRoleInterface roleInterface;

    @PostMapping("/Registro") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody RolesDTO s){
        ModelMapper m = new ModelMapper();
        Roles sh=m.map(s, Roles.class);
        roleInterface.insert(sh);
    }

    @GetMapping //listar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public List<RolesDTO> list(){
        return roleInterface.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, RolesDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Long id, @RequestBody RolesDTO re){
        ModelMapper m = new ModelMapper();
        Roles rh = m.map(re, Roles.class);
        rh.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        roleInterface.update(rh);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Long id){
        roleInterface.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public RolesDTO listarId(@PathVariable("id") Long id) {
        ModelMapper m = new ModelMapper();
        RolesDTO dto = m.map(roleInterface.listarId(id), RolesDTO.class);
        return dto;
    }
}
