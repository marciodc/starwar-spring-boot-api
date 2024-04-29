package br.com.marciodc.starwars.domain.entity;

public class Starship {
    private String starshipUrl;

    public Starship() {
    }

    public Starship(String starshipUrl) {
        this.starshipUrl = starshipUrl;
    }

    public String getStarshipUrl() {
        return starshipUrl;
    }

    public void setStarshipUrl(String starshipUrl) {
        this.starshipUrl = starshipUrl;
    }
}
