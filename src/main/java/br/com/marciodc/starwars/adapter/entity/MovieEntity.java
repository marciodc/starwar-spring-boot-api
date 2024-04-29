package br.com.marciodc.starwars.adapter.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    @Id
    @Column(name = "episode_id")
    private Integer episodeId;

    @Column(nullable = false, length = 250)
    private String title;

    @NotBlank(message = "Openning crawl is required")
    @Column(name = "opening_crawl")
    @Lob
    private String openingCrawl;

    @Column(length = 250)
    private String director;

    @Column(length = 250)
    private String producer;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(nullable = false)
    private Timestamp created;

    @Column(nullable = false)
    private Timestamp edited;

    @Column(length = 250)
    private String url;

    @Column(nullable = false)
    private int version = 1;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CharactersEntity> characters;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlanetEntity> planets;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpeciesEntity> species;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StarshipEntity> starship;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VehicleEntity> vehicles;
}
