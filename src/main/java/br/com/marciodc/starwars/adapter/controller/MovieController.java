package br.com.marciodc.starwars.adapter.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.marciodc.starwars.adapter.dto.MovieRequestDTO;
import br.com.marciodc.starwars.domain.entity.Movie;
import br.com.marciodc.starwars.domain.ports.inbound.MovieServicePort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final MovieServicePort movieServicePort;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movies = movieServicePort.allMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findByEpisodeId(@PathVariable @NotNull int id) {
        Movie movie = movieServicePort.findByEpisodeId(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOpeningCrawl(@PathVariable @NotNull int id, @RequestBody @Valid MovieRequestDTO movieDTO, BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Errors in movie DTO: " + result.getAllErrors());
            return ResponseEntity.badRequest().body("Invalid movie data");
        }
        
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        Movie updatedMovie = movieServicePort.updatOpeningCrawl(id, movie);
        if (updatedMovie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMovie);
    }
}
