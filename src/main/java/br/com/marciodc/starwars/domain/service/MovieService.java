package br.com.marciodc.starwars.domain.service;

import java.util.List;

import br.com.marciodc.starwars.domain.entity.Movie;
import br.com.marciodc.starwars.domain.ports.inbound.MovieServicePort;
import br.com.marciodc.starwars.domain.ports.outbound.MovieRepositoryPort;

public class MovieService implements MovieServicePort {

    private final MovieRepositoryPort movieRepositoryPort;

    public MovieService(MovieRepositoryPort movieRepositoryPort) {
        this.movieRepositoryPort = movieRepositoryPort;
    }

    /**
        * Retrieves a list of all movies.
        *
        * @return a list of Movie objects representing all movies.
        */
    @Override
    public List<Movie> allMovies() {
        return movieRepositoryPort.listMovies();
    }

    /**
        * Updates the opening crawl of a movie.
        *
        * @param id    the ID of the movie
        * @param movie the updated movie object
        * @return the updated movie object
        */
    @Override
    public Movie updatOpeningCrawl(int id, Movie movie) {
        return movieRepositoryPort.updatOpeningCrawl(id, movie);
    }

    /**
        * Finds a movie by its episode ID.
        *
        * @param id the episode ID of the movie
        * @return the movie with the specified episode ID, or null if not found
        */
    @Override
    public Movie findByEpisodeId(int id) {
        return movieRepositoryPort.findByEpisodeId(id);
    }

}
