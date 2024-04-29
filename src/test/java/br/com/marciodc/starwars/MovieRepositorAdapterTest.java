package br.com.marciodc.starwars;

import br.com.marciodc.starwars.adapter.entity.MovieEntity;
import br.com.marciodc.starwars.adapter.repository.MovieRepositoryAdapter;
import br.com.marciodc.starwars.adapter.repository.MovieRepository;
import br.com.marciodc.starwars.domain.entity.Movie;
import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepositorAdapterTest {
    

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieRepositoryAdapter movieRepositoryAdapter;

    @Spy
    private Logger logger = LoggerFactory.getLogger(MovieRepositoryAdapter.class);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertMovie() {
        Movie movie = new Movie();
        movie.setTitle("Star Wars: A New Hope");
        movie.setEpisodeId(4);
        movie.setDirector("George Lucas");

        MovieEntity entity = new MovieEntity();
        entity.setTitle("Star Wars: A New Hope");
        entity.setEpisodeId(4);
        entity.setDirector("George Lucas");

        when(modelMapper.map(movie, MovieEntity.class)).thenReturn(entity);
        when(movieRepository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, Movie.class)).thenReturn(movie);

        Movie result = movieRepositoryAdapter.insertMovie(movie);

        assertEquals(movie, result);
        verify(modelMapper, times(1)).map(movie, MovieEntity.class);
        verify(movieRepository, times(1)).save(entity);
        verify(modelMapper, times(1)).map(entity, Movie.class);
    }

    @Test
    public void testUpdatOpeningCrawl() {
        int id = 4;
        Movie movie = new Movie();
        movie.setEpisodeId(4);
        movie.setOpeningCrawl("A long time ago in a galaxy far, far away...");

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setEpisodeId(4);
        movieEntity.setOpeningCrawl("A long time ago in a galaxy far, far away...");

        when(modelMapper.map(any(MovieEntity.class), eq(Movie.class))).thenReturn(movie);
        when(movieRepository.findByEpisodeId(id)).thenReturn(Optional.of(movieEntity));
        when(movieRepository.save(movieEntity)).thenReturn(movieEntity);

        Movie result = movieRepositoryAdapter.updatOpeningCrawl(id, movie);

        assertNotNull(result);
        assertEquals(movieEntity.getOpeningCrawl(), result.getOpeningCrawl());

        verify(movieRepository, times(1)).findByEpisodeId(id);
        verify(modelMapper, times(1)).map(movieEntity, Movie.class);
    }

    @Test
    public void testUpdatOpeningCrawl_MovieNotFound() {
        int id = 4;
        Movie movie = new Movie();
        movie.setEpisodeId(4);
        movie.setOpeningCrawl("A long time ago in a galaxy far, far away...");

        when(movieRepository.findByEpisodeId(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> movieRepositoryAdapter.updatOpeningCrawl(id, movie));

        verify(movieRepository, times(1)).findByEpisodeId(id);
        verify(movieRepository, never()).save(any());
        verify(modelMapper, never()).map(any(), eq(Movie.class));
    }

    @Test
    public void testUpdatOpeningCrawl_EmptyOpeningCrawl() {
        int id = 4;
        Movie movie = new Movie();
        movie.setEpisodeId(4);
        movie.setOpeningCrawl("");

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setEpisodeId(4);
        movieEntity.setOpeningCrawl("A long time ago in a galaxy far, far away...");

        when(movieRepository.findByEpisodeId(id)).thenReturn(Optional.of(movieEntity));

        assertThrows(ResponseStatusException.class, () -> movieRepositoryAdapter.updatOpeningCrawl(id, movie));

        verify(movieRepository, times(1)).findByEpisodeId(id);
        verify(movieRepository, never()).save(any());
        verify(modelMapper, never()).map(any(), eq(Movie.class));
    }

    @Test
    public void testListMovies() {
        List<MovieEntity> movieEntities = new ArrayList<>();
        MovieEntity entity = new MovieEntity();
        entity.setTitle("Star Wars: A New Hope");
        entity.setEpisodeId(4);
        entity.setDirector("George Lucas");
        movieEntities.add(entity);
        MovieEntity entity2 = new MovieEntity();
        entity2.setTitle("The Empire Strikes Back");
        entity2.setEpisodeId(5);
        entity2.setDirector("Irvin Kershner");
        movieEntities.add(entity2);
        MovieEntity entity3 = new MovieEntity();
        entity3.setTitle("Return of the Jedi");
        entity3.setEpisodeId(6);
        entity3.setDirector("Richard Marquand");
        movieEntities.add(entity3);

        when(movieRepository.findAll()).thenReturn(movieEntities);

        List<Movie> expectedMovies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setTitle("Star Wars: A New Hope");
        movie.setEpisodeId(4);
        movie.setDirector("George Lucas");
        expectedMovies.add(movie);
        Movie movie2 = new Movie();
        movie2.setTitle("The Empire Strikes Back");
        movie2.setEpisodeId(5);
        movie2.setDirector("Irvin Kershner");
        expectedMovies.add(movie2);
        Movie movie3 = new Movie();
        movie3.setTitle("Return of the Jedi");
        movie3.setEpisodeId(6);
        movie3.setDirector("Richard Marquand");

        when(modelMapper.map(any(MovieEntity.class), eq(Movie.class)))
                .thenReturn(movie)
                .thenReturn(movie2)
                .thenReturn(movie3);

        List<Movie> result = movieRepositoryAdapter.listMovies();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Star Wars: A New Hope", result.get(0).getTitle());
        assertEquals("The Empire Strikes Back", result.get(1).getTitle());
        assertEquals("Return of the Jedi", result.get(2).getTitle());
        verify(movieRepository, times(1)).findAll();
        verify(modelMapper, times(3)).map(any(MovieEntity.class), eq(Movie.class));
    }

    @Test
    void findByEpisodeId_ReturnsMovie_WhenMovieIsFound() {
        // Arrange
        int id = 1;
        MovieEntity movieEntity = new MovieEntity();
        Movie expectedMovie = new Movie();
        when(movieRepository.findByEpisodeId(id)).thenReturn(Optional.of(movieEntity));
        when(modelMapper.map(any(MovieEntity.class), eq(Movie.class))).thenReturn(expectedMovie);


        // Act
        Movie result = movieRepositoryAdapter.findByEpisodeId(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedMovie, result);
        verify(movieRepository).findByEpisodeId(id);
        verify(modelMapper).map(movieEntity, Movie.class);
    }
    
    @Test
    public void testFindByEpisodeId_MovieNotFound() {
        int id = 4;

        when(movieRepository.findByEpisodeId(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> movieRepositoryAdapter.findByEpisodeId(id));

        verify(movieRepository, times(1)).findByEpisodeId(id);
        verify(modelMapper, never()).map(any(), eq(Movie.class));
    }
}