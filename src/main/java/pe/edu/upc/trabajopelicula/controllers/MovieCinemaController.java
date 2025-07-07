package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.FindMovieScheduleDTO;
import pe.edu.upc.trabajopelicula.dtos.MovieCinemaDTO;
import pe.edu.upc.trabajopelicula.dtos.QuantityFunctionsCinemaDTO;
import pe.edu.upc.trabajopelicula.dtos.QuantityMoviesCityDTO;
import pe.edu.upc.trabajopelicula.entities.MovieCinema;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IMovieCinemaInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moviecinema")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class MovieCinemaController {

    @Autowired
    private IMovieCinemaInterface movieCinemaInterface;

    @PostMapping("/Registro") //registrar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void registrar(@RequestBody MovieCinemaDTO a) {
        ModelMapper m = new ModelMapper();
        MovieCinema ch = m.map(a, MovieCinema.class);
        movieCinemaInterface.insert(ch);
    }

    @GetMapping //listar
    public List<MovieCinemaDTO> list() {
        return movieCinemaInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, MovieCinemaDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void actualizar(@PathVariable("id") Integer id, @RequestBody MovieCinemaDTO a) {
        ModelMapper m = new ModelMapper();
        MovieCinema ah = m.map(a, MovieCinema.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        movieCinemaInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    @PreAuthorize("hasAnyAuthority('ADMIN') and !hasAnyAuthority('CLIENTE')") //manejar la auth de USER
    public void eliminar(@PathVariable("id") Integer id){
        movieCinemaInterface.delete(id);
    }

    @GetMapping("/{id}")
    public MovieCinemaDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        MovieCinemaDTO dto = m.map(movieCinemaInterface.listarId(id), MovieCinemaDTO.class);
        return dto;
    }

    @GetMapping("/cantidadFuncionesCine")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<QuantityFunctionsCinemaDTO> cantidadFuncionesCine() {
        List<String[]> rawList = movieCinemaInterface.countFunctionsByCinema();
        List<QuantityFunctionsCinemaDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            QuantityFunctionsCinemaDTO dto = new QuantityFunctionsCinemaDTO();
            dto.setCinema(row[0]);
            dto.setQuantity(Integer.parseInt(row[1]));
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/cantidadPeliculasCiudad")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<QuantityMoviesCityDTO> cantidadPeliculasCiudad() {
        List<String[]> rawList = movieCinemaInterface.countMoviesByCity();
        List<QuantityMoviesCityDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            QuantityMoviesCityDTO dto = new QuantityMoviesCityDTO();
            dto.setCity(row[0]);
            dto.setQuantity(Integer.parseInt(row[1]));
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/funcionesPorCine")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<FindMovieScheduleDTO> funcionesPorCine(@RequestParam("cinema") String cinema) {
        List<String[]> rawList = movieCinemaInterface.findMovieSchedulesByCinema(cinema);
        List<FindMovieScheduleDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            FindMovieScheduleDTO dto = new FindMovieScheduleDTO();
            dto.setMovie(row[0]);
            dto.setCinema(row[1]);
            dto.setStartHour(row[2]);
            dto.setEndHour(row[3]);
            result.add(dto);
        }
        return result;
    }

}
