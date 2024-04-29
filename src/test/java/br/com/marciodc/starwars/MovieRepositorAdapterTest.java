package br.com.marciodc.starwars;

import br.com.marciodc.starwars.adapter.entity.MovieEntity;
import br.com.marciodc.starwars.adapter.repository.MovieRepositorAdapter;
import br.com.marciodc.starwars.adapter.repository.MovieRepository;
import br.com.marciodc.starwars.domain.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class MovieRepositorAdapterTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieRepositorAdapter movieRepositorAdapter;

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

        Movie result = movieRepositorAdapter.insertMovie(movie);

        assertEquals(movie, result);
        verify(modelMapper, times(1)).map(movie, MovieEntity.class);
        verify(movieRepository, times(1)).save(entity);
        verify(modelMapper, times(1)).map(entity, Movie.class);
    }

    @Test
    public void testUpdateMovie() {
        // TODO: Implement test case for updateMovie method
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

        List<Movie> result = movieRepositorAdapter.listMovies();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Star Wars: A New Hope", result.get(0).getTitle());
        assertEquals("The Empire Strikes Back", result.get(1).getTitle());
        assertEquals("Return of the Jedi", result.get(2).getTitle());
        verify(movieRepository, times(1)).findAll();
        verify(modelMapper, times(3)).map(any(MovieEntity.class), eq(Movie.class));
    }

    @Test
    public void testFindByEpisodeId() {
        // TODO: Implement test case for findByEpisodeId method
    }
}