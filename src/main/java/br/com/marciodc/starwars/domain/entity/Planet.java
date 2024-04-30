package br.com.marciodc.starwars.domain.entity;

import java.util.Objects;

public class Planet {
    private String planetUrl;

    public Planet() {
    }

    public Planet(String planetUrl) {
        this.planetUrl = planetUrl;
    }
    
    public String getPlanetUrl() {
        return planetUrl;
    }
    
    public void setPlanetUrl(String planetUrl) {
        this.planetUrl = planetUrl;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "planetUrl='" + planetUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(planetUrl, planet.planetUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(planetUrl);
    }
}
