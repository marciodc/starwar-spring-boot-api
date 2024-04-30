package br.com.marciodc.starwars.domain.entity;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Starship{" +
                "starshipUrl='" + starshipUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Starship starship = (Starship) o;
        return Objects.equals(starshipUrl, starship.starshipUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(starshipUrl);
    }
}
