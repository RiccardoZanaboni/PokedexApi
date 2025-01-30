package com.pokedex.service;

import com.pokedex.client.FunTranslationsClient;
import com.pokedex.model.PokemonResponse;
import com.pokedex.model.PokemonResponseBuilder;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    private final PokemonService pokemonService;
    private final FunTranslationsClient funTranslationsClient;

    public TranslationService(PokemonService pokemonService, FunTranslationsClient funTranslationsClient) {
        this.pokemonService = pokemonService;
        this.funTranslationsClient = funTranslationsClient;
    }

    public PokemonResponse getTranslatedPokemon(String name) {
        PokemonResponse pokemon = pokemonService.getPokemon(name);

        String description;
        if ("cave".equalsIgnoreCase(pokemon.getHabitat()) || pokemon.isLegendary()) {
            description = funTranslationsClient.translateYoda(pokemon.getDescription());
        } else {
            description = funTranslationsClient.translateShakespeare(pokemon.getDescription());
        }
        return new PokemonResponseBuilder()
                .withHabitat(pokemon.getHabitat())
                .withName(pokemon.getName())
                .withIsLegendary(pokemon.isLegendary())
                .withDescription(description != null ? description : pokemon.getDescription())
                .build();
    }
}