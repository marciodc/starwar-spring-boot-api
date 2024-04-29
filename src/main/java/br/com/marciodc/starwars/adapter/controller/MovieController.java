package br.com.marciodc.starwars.adapter.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.marciodc.starwars.adapter.dto.MovieRequestDTO;
import br.com.marciodc.starwars.domain.entity.Movie;
import br.com.marciodc.starwars.domain.ports.inbound.MovieServicePort;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movie Controller", description = "List and manage all Swar Wars movies")
@AllArgsConstructor
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    
    @Autowired
    private MovieServicePort movieServicePort;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all movies")
    @ApiResponse(responseCode = "200", description = "Found movies", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = Movie.class)) })
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movies = movieServicePort.allMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a movie by episode id")
    @ApiResponse(responseCode = "200", description = "Found movie", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = Movie.class)) })
    public ResponseEntity<Movie> findByEpisodeId(@PathVariable @NotNull int id) {
        Movie movie = movieServicePort.findByEpisodeId(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update the opening crawl of a movie by episode id")
    @ApiResponse(responseCode = "200", description = "Movie updated successfully", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = Movie.class)) })
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
