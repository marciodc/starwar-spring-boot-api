package br.com.marciodc.starwars.domain.entity;

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

}
