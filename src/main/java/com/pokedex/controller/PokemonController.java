package com.pokedex.controller;

import com.pokedex.model.PokemonResponse;
import com.pokedex.service.PokemonService;
import com.pokedex.service.TranslationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;
    private final TranslationService translationService;

    public PokemonController(PokemonService pokemonService, TranslationService translationService) {
        this.pokemonService = pokemonService;
        this.translationService = translationService;
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public PokemonResponse getPokemon(@PathVariable String name) {
        return pokemonService.getPokemon(name);
    }

    @GetMapping(value = "/translated/{name}", produces = "application/json")
    public PokemonResponse getTranslatedPokemon(@PathVariable String name) {
        return translationService.getTranslatedPokemon(name);
    }
}
