package br.com.marciodc.starwars.domain.ports.outbound;

import java.util.List;

import br.com.marciodc.starwars.domain.entity.Movie;

public interface MovieRepositoryPort {

    Movie insertMovie(Movie movie);

    Movie updatOpeningCrawl(int id, Movie movie);

    List<Movie> listMovies();

    Movie findByEpisodeId(int id);
}
