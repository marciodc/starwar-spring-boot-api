package br.com.marciodc.starwars.config;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.marciodc.starwars.adapter.dto.MovieResponseDTO;
import br.com.marciodc.starwars.adapter.entity.CharactersEntity;
import br.com.marciodc.starwars.adapter.entity.MovieEntity;
import br.com.marciodc.starwars.adapter.entity.PlanetEntity;
import br.com.marciodc.starwars.adapter.entity.SpeciesEntity;
import br.com.marciodc.starwars.adapter.entity.StarshipEntity;
import br.com.marciodc.starwars.adapter.entity.VehicleEntity;
import br.com.marciodc.starwars.adapter.repository.MovieRepository;
import jakarta.annotation.PostConstruct;

@Component
public class AppStart {

    private static final Logger logger = LoggerFactory.getLogger(AppStart.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepository movieRepository;

    @PostConstruct
    public void fetchUserData() {
        String url = "https://swapi.dev/api/films/";
        MovieResponseDTO movieResponse = restTemplate.getForObject(url, MovieResponseDTO.class);
        if (movieResponse != null && movieResponse.getResults() != null && !movieResponse.getResults().isEmpty()) {
            for (var movieDTO : movieResponse.getResults()) {
                try {
                    MovieEntity entity = modelMapper.map(movieDTO, MovieEntity.class);

                    var characters = movieDTO.getCharacters().stream()
                        .map(characterUrl -> new CharactersEntity(entity, characterUrl))
                        .collect(Collectors.toList());
                    entity.setCharacters(characters);

                    var planets = movieDTO.getCharacters().stream()
                        .map(planetUrl -> new PlanetEntity(entity, planetUrl))
                        .collect(Collectors.toList());
                    entity.setPlanets(planets);

                    var species = movieDTO.getCharacters().stream()
                        .map(specieUrl -> new SpeciesEntity(entity, specieUrl))
                        .collect(Collectors.toList());
                    entity.setSpecies(species);

                    var starships = movieDTO.getCharacters().stream()
                        .map(starshipUrl -> new StarshipEntity(entity, starshipUrl))
                        .collect(Collectors.toList());
                    entity.setStarship(starships);

                    var vehicles = movieDTO.getCharacters().stream()
                        .map(vehicleUrl -> new VehicleEntity(entity, vehicleUrl))
                        .collect(Collectors.toList());
                    entity.setVehicles(vehicles);

                    logger.info("Salvando o filme: " + entity.getTitle());
                    movieRepository.save(entity);
                    logger.info("Filme salvo: " + entity.getTitle());
                } catch (Exception e) {
                    logger.error("Falha ao salvar o filme: " + e.getMessage());
                }
            }
        }
    }
}
