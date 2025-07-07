package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.TypePaymentsDTO;
import pe.edu.upc.trabajopelicula.entities.TypePayments;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ITypePaymentsInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/typepayment")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class TypePaymentController {
    @Autowired
    private ITypePaymentsInterface typePaymentsInterface;

    @PostMapping("/Registro") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody TypePaymentsDTO a) {
        ModelMapper m = new ModelMapper();
        TypePayments ch = m.map(a, TypePayments.class);
        typePaymentsInterface.insert(ch);
    }

    @GetMapping //listar
    public List<TypePaymentsDTO> list() {
        return typePaymentsInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, TypePaymentsDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody TypePaymentsDTO a) {
        ModelMapper m = new ModelMapper();
        TypePayments ah = m.map(a, TypePayments.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        typePaymentsInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        typePaymentsInterface.delete(id);
    }

    @GetMapping("/{id}")
    public TypePaymentsDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        TypePaymentsDTO dto = m.map(typePaymentsInterface.listarId(id), TypePaymentsDTO.class);
        return dto;
    }
}
