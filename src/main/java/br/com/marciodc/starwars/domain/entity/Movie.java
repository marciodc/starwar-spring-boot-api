package br.com.marciodc.starwars.domain.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Movie {

    private int episodeId;
    private String title;
    private String openingCrawl;
    private String director;
    private String producer;
    private Date releaseDate;
    private Timestamp created;
    private Timestamp edited;
    private String url;
    private int version;
    private List<Characters> characters;
    private List<Planet> planets;
    private List<Starship> starships;
    private List<Vehicle> vehicles;
    private List<Species> species;

    public Movie() {
    }

    public Movie(int episodeId, String title, String openingCrawl, String director, String producer,
                 Date releaseDate, Timestamp created, Timestamp edited, String url, int version, List<Characters> characters,
                 List<Planet> planets, List<Starship> starships, List<Vehicle> vehicles, List<Species> species) {
        this.episodeId = episodeId;
        this.title = title;
        this.openingCrawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.created = created;
        this.edited = edited;
        this.url = url;
        this.version = version;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
        this.vehicles = vehicles;
        this.species = species;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.util.Date releaseDate2) {
        this.releaseDate = releaseDate2;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getEdited() {
        return edited;
    }

    public void setEdited(Timestamp edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Characters> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Characters> characters) {
        this.characters = characters;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public List<Starship> getStarships() {
        return starships;
    }

    public void setStarships(List<Starship> starships) {
        this.starships = starships;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "episodeId=" + episodeId +
                ", title='" + title + '\'' +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", releaseDate=" + releaseDate +
                ", created=" + created +
                ", edited=" + edited +
                ", url='" + url + '\'' +
                ", version=" + version +
                ", characters=" + characters +
                ", planets=" + planets +
                ", starships=" + starships +
                ", vehicles=" + vehicles +
                ", species=" + species +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return episodeId == movie.episodeId && version == movie.version && Objects.equals(title, movie.title) && Objects.equals(openingCrawl, movie.openingCrawl) && Objects.equals(director, movie.director) && Objects.equals(producer, movie.producer) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(created, movie.created) && Objects.equals(edited, movie.edited) && Objects.equals(url, movie.url) && Objects.equals(characters, movie.characters) && Objects.equals(planets, movie.planets) && Objects.equals(starships, movie.starships) && Objects.equals(vehicles, movie.vehicles) && Objects.equals(species, movie.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeId, title, openingCrawl, director, producer, releaseDate, created, edited, url, version, characters, planets, starships, vehicles, species);
    }
}
