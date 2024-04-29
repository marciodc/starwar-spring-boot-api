package br.com.marciodc.starwars.domain.ports.inbound;

import java.util.List;

import br.com.marciodc.starwars.domain.entity.Movie;

public interface MovieServicePort {

    List<Movie> allMovies();

    Movie updatOpeningCrawl(int id, Movie movie);

    Movie findByEpisodeId(int id);

}
