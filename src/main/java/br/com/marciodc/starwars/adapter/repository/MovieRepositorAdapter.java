package br.com.marciodc.starwars.adapter.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.marciodc.starwars.adapter.entity.MovieEntity;
import br.com.marciodc.starwars.domain.entity.Movie;
import br.com.marciodc.starwars.domain.ports.outbound.MovieRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

@Component
public class MovieRepositorAdapter implements MovieRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(MovieRepositorAdapter.class);

    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Movie insertMovie(Movie movie) {
        MovieEntity entity = modelMapper.map(movie, MovieEntity.class);

        MovieEntity save = movieRepository.save(entity);

        return modelMapper.map(save, Movie.class);
    }

    @Override
    public Movie updatOpeningCrawl(int id, Movie movie) {
        Optional<MovieEntity> movieEntityOpt = movieRepository.findByEpisodeId(id);

        if (movieEntityOpt.isPresent()) {
            MovieEntity movieEntity = movieEntityOpt.get();
    
            if (movie.getOpeningCrawl() == null || movie.getOpeningCrawl().isEmpty()) {
                logger.error("movie.openingCraws is empty. The opening crawl mus be have a value.");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The opening crawl mus be have a value");
            }

            if (movieEntity.getOpeningCrawl().equals(movie.getOpeningCrawl())) {
                return modelMapper.map(movieEntity, Movie.class);
            }

            movieEntity.setOpeningCrawl(movie.getOpeningCrawl());
            movieEntity.setVersion(movieEntity.getVersion() + 1);
            MovieEntity savedEntity = movieRepository.save(movieEntity);
    
            return modelMapper.map(savedEntity, Movie.class);
        }
        
        logger.error("Movie not found with id: " + movie.getEpisodeId());
        throw new EntityNotFoundException("Movie not found with id: " + movie.getEpisodeId());
    }

    @Override
    public List<Movie> listMovies() {
        var movies = movieRepository.findAll();
        return movies.stream()
            .map( movie -> modelMapper.map(movie, Movie.class))
            .collect(Collectors.toList());
    }

    @Override
    public Movie findByEpisodeId(int id) {
        Optional<MovieEntity> movieEntity = movieRepository.findByEpisodeId(id);

        if (movieEntity.isPresent()) {
            return modelMapper.map(movieEntity, Movie.class);
        }
        
        logger.error("Movie not found with id: " + id);
        throw new EntityNotFoundException("Movie not found with id: " + id);
    }

}
