package br.com.marciodc.starwars.domain.entity;

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
}
