package com.pokedex.model;

public final class PokemonResponseBuilder {
    private String name;
    private String description;
    private String habitat;
    private boolean isLegendary;

    public PokemonResponseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PokemonResponseBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PokemonResponseBuilder withHabitat(String habitat) {
        this.habitat = habitat;
        return this;
    }

    public PokemonResponseBuilder withIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
        return this;
    }

    public PokemonResponse build() {
        return new PokemonResponse(name, description, habitat, isLegendary);
    }
}
