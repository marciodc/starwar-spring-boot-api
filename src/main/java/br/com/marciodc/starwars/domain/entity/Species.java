package br.com.marciodc.starwars.domain.entity;

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
}
