package br.com.marciodc.starwars.domain.entity;

import java.util.Objects;

public class Characters {
    private String characterUrl;

    public Characters() {
    }

    public Characters(String characterUrl) {
        this.characterUrl = characterUrl;
    }

    public String getCharacterUrl() {
        return characterUrl;
    }

    public void setCharacterUrl(String characterUrl) {
        this.characterUrl = characterUrl;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "characterUrl='" + characterUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return Objects.equals(characterUrl, that.characterUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(characterUrl);
    }
}
