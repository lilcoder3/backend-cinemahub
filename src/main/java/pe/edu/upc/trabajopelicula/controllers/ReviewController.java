package pe.edu.upc.trabajopelicula.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.trabajopelicula.dtos.GetMovieReviewDTO;
import pe.edu.upc.trabajopelicula.dtos.ReviewDTO;
import pe.edu.upc.trabajopelicula.entities.Review;
import pe.edu.upc.trabajopelicula.serviceinterfaces.IReviewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = {"http://localhost:4200"  }, allowedHeaders = "*", allowCredentials = "true")
public class ReviewController {
    @Autowired
    private IReviewInterface reviewInterface;

    @PostMapping("/Registro") //registrar
    public void registrar(@RequestBody ReviewDTO a) {
        ModelMapper m = new ModelMapper();
        Review ch = m.map(a, Review.class);
        reviewInterface.insert(ch);
    }

    @GetMapping //listar
    public List<ReviewDTO> list() {
        return reviewInterface.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, ReviewDTO.class); //expresion lambda para la transformacion
        }).collect(Collectors.toList()); //lista de tipo Shoe
    }

    @PutMapping("/{id}") // actualizar
    public void actualizar(@PathVariable("id") Integer id, @RequestBody ReviewDTO a) {
        ModelMapper m = new ModelMapper();
        Review ah = m.map(a, Review.class);
        ah.setId(id); // asegurarse de que el objeto tenga el mismo ID que el proporcionado en la URL
        reviewInterface.update(ah);
    }

    @DeleteMapping("/{id}") //reconozca parametros que estamos pasando
    public void eliminar(@PathVariable("id") Integer id){
        reviewInterface.delete(id);
    }

    @GetMapping("/{id}")
    public ReviewDTO listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        ReviewDTO dto = m.map(reviewInterface.listarId(id), ReviewDTO.class);
        return dto;
    }

    @GetMapping("/estadisticasResenas")
    public List<GetMovieReviewDTO> estadisticasResenas(@RequestParam("movie") String movie) {
        List<String[]> rawList = reviewInterface.getMovieReviewStats(movie);
        List<GetMovieReviewDTO> result = new ArrayList<>();
        for (String[] row : rawList) {
            GetMovieReviewDTO dto = new GetMovieReviewDTO();
            dto.setMovie(row[0]);
            dto.setTotalReviews(Integer.parseInt(row[1]));
            dto.setAverageRating(Double.parseDouble(row[2]));
            result.add(dto);
        }
        return result;
    }

}
