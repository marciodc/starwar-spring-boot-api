package br.com.marciodc.starwars.adapter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "species")
public class SpeciesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
    private String speciesUrl;

    public SpeciesEntity() {
    }

    public SpeciesEntity(MovieEntity movie, String speciesUrl) {
        this.movie = movie;
        this.speciesUrl = speciesUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public String getSpeciesUrl() {
        return speciesUrl;
    }

    public void setSpeciesUrl(String speciesUrl) {
        this.speciesUrl = speciesUrl;
    }
}
