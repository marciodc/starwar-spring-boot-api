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

    @Override
    public List<Movie> allMovies() {
        return movieRepositoryPort.listMovies();
    }

    @Override
    public Movie updatOpeningCrawl(int id, Movie movie) {
        return movieRepositoryPort.updatOpeningCrawl(id, movie);
    }

    @Override
    public Movie findByEpisodeId(int id) {
        return movieRepositoryPort.findByEpisodeId(id);
    }

}
