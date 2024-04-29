package br.com.marciodc.starwars;

import br.com.marciodc.starwars.adapter.controller.MovieController;
import br.com.marciodc.starwars.domain.entity.Movie;
import br.com.marciodc.starwars.domain.ports.inbound.MovieServicePort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    private MovieController movieController;

    @Mock
    private MovieServicePort movieServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieController = new MovieController(movieServicePort, null);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Movie> movies = Arrays.asList(
                new Movie(4, "Star Wars", "A long time ago in a galaxy far, far away...", "George Lucas",
                        "Gary Kurtz, Rick McCallum", new Date(), new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis()), "https://www.example.com/starwars", 1,
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>()),
                new Movie(5, "The Empire Strikes Back", "It is a dark time for the Rebellion...",
                        "Irvin Kershner", "Gary Kurtz, Rick McCallum", new Date(),
                        new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
                        "https://www.example.com/starwars", 1, new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        when(movieServicePort.allMovies()).thenReturn(movies);

        // Act
        ResponseEntity<List<Movie>> result = movieController.findAll();

        // Assert
        assertEquals(movies, result);
        verify(movieServicePort, times(1)).allMovies();
    }

    @Test
    void testFindByEpisodeId() {
        // Arrange
        int episodeId = 4;
        Movie movie = new Movie(episodeId, "Star Wars", "A long time ago in a galaxy far, far away...",
                "George Lucas", "Gary Kurtz, Rick McCallum", new Date(),
                new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
                "https://www.example.com/starwars", 1, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        when(movieServicePort.findByEpisodeId(episodeId)).thenReturn(movie);

        // Act
        ResponseEntity<Movie> result = movieController.findByEpisodeId(episodeId);

        // Assert
        assertEquals(movie, result);
        verify(movieServicePort, times(1)).findByEpisodeId(episodeId);
    }
}