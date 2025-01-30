package com.pokedex.model;

public class PokemonResponse {
    private String name;
    private String description;
    private String habitat;
    private boolean isLegendary;

    public PokemonResponse(String name, String description, String habitat, boolean isLegendary) {
        this.name = name;
        this.description = description;
        this.habitat = habitat;
        this.isLegendary = isLegendary;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHabitat() {
        return habitat;
    }

    public boolean isLegendary() {
        return isLegendary;
    }
}
