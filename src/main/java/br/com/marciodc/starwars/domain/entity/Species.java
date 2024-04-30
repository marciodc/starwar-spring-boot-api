package br.com.marciodc.starwars.domain.entity;

import java.util.Objects;

public class Species {
    private String speciesUrl;

    public Species() {
    }

    public Species(String speciesUrl) {
        this.speciesUrl = speciesUrl;
    }

    public String getSpeciesUrl() {
        return speciesUrl;
    }

    public void setSpeciesUrl(String speciesUrl) {
        this.speciesUrl = speciesUrl;
    }

    @Override
    public String toString() {
        return "Species{" +
                "speciesUrl='" + speciesUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return Objects.equals(speciesUrl, species.speciesUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(speciesUrl);
    }
}
