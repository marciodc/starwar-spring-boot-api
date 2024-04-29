package br.com.marciodc.starwars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.marciodc.starwars.domain.entity.Characters;
import br.com.marciodc.starwars.domain.entity.Movie;
import br.com.marciodc.starwars.domain.entity.Planet;
import br.com.marciodc.starwars.domain.entity.Species;
import br.com.marciodc.starwars.domain.entity.Starship;
import br.com.marciodc.starwars.domain.entity.Vehicle;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieTest {

    @Test
    public void testMovieConstructorAndGetters() {
        String title = "Star Wars";
        int episodeId = 4;
        String openingCrawl = "A long time ago in a galaxy far, far away...";
        String director = "George Lucas";
        String producer = "Gary Kurtz, Rick McCallum";
        Date releaseDate = new Date();
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Timestamp edited = new Timestamp(System.currentTimeMillis());
        String url = "https://www.example.com/starwars";
        int version = 1;
        List<Characters> characters = new ArrayList<>();
        List<Planet> planets = new ArrayList<>();
        List<Starship> starships = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        List<Species> species = new ArrayList<>();

        var movie = new Movie(episodeId, title, openingCrawl, director, producer, releaseDate, created, edited,
                url, version, characters, planets, starships, vehicles, species);

        Assertions.assertEquals(title, movie.getTitle());
        Assertions.assertEquals(episodeId, movie.getEpisodeId());
        Assertions.assertEquals(openingCrawl, movie.getOpeningCrawl());
        Assertions.assertEquals(director, movie.getDirector());
        Assertions.assertEquals(producer, movie.getProducer());
        Assertions.assertEquals(releaseDate, movie.getReleaseDate());
        Assertions.assertEquals(created, movie.getCreated());
        Assertions.assertEquals(edited, movie.getEdited());
        Assertions.assertEquals(url, movie.getUrl());
        Assertions.assertEquals(version, movie.getVersion());
        Assertions.assertEquals(characters, movie.getCharacters());
        Assertions.assertEquals(planets, movie.getPlanets());
        Assertions.assertEquals(starships, movie.getStarships());
        Assertions.assertEquals(vehicles, movie.getVehicles());
        Assertions.assertEquals(species, movie.getSpecies());
    }

    @Test
    public void testMovieSetters() {
        Movie movie = new Movie();

        String title = "Star Wars";
        int episodeId = 4;
        String openingCrawl = "A long time ago in a galaxy far, far away...";
        String director = "George Lucas";
        String producer = "Gary Kurtz, Rick McCallum";
        Date releaseDate = new Date();
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Timestamp edited = new Timestamp(System.currentTimeMillis());
        String url = "https://www.example.com/starwars";
        int version = 1;
        List<Characters> characters = new ArrayList<>();
        List<Planet> planets = new ArrayList<>();
        List<Starship> starships = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        List<Species> species = new ArrayList<>();

        movie.setTitle(title);
        movie.setEpisodeId(episodeId);
        movie.setOpeningCrawl(openingCrawl);
        movie.setDirector(director);
        movie.setProducer(producer);
        movie.setReleaseDate(releaseDate);
        movie.setCreated(created);
        movie.setEdited(edited);
        movie.setUrl(url);
        movie.setVersion(version);
        movie.setCharacters(characters);
        movie.setPlanets(planets);
        movie.setStarships(starships);
        movie.setVehicles(vehicles);
        movie.setSpecies(species);

        Assertions.assertEquals(title, movie.getTitle());
        Assertions.assertEquals(episodeId, movie.getEpisodeId());
        Assertions.assertEquals(openingCrawl, movie.getOpeningCrawl());
        Assertions.assertEquals(director, movie.getDirector());
        Assertions.assertEquals(producer, movie.getProducer());
        Assertions.assertEquals(releaseDate, movie.getReleaseDate());
        Assertions.assertEquals(created, movie.getCreated());
        Assertions.assertEquals(edited, movie.getEdited());
        Assertions.assertEquals(url, movie.getUrl());
        Assertions.assertEquals(version, movie.getVersion());
        Assertions.assertEquals(characters, movie.getCharacters());
        Assertions.assertEquals(planets, movie.getPlanets());
        Assertions.assertEquals(starships, movie.getStarships());
        Assertions.assertEquals(vehicles, movie.getVehicles());
        Assertions.assertEquals(species, movie.getSpecies());
    }
}