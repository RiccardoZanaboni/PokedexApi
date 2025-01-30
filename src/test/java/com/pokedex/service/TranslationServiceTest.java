package com.pokedex.service;

import com.pokedex.client.FunTranslationsClient;
import com.pokedex.model.PokemonResponse;
import com.pokedex.model.PokemonResponseBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TranslationServiceTest {

    @Test
    void getTranslatedPokemon_ShouldUseYodaTranslationForLegendaryOrCavePokemon() {
        PokemonService pokemonService = mock(PokemonService.class);
        FunTranslationsClient funTranslationsClient = mock(FunTranslationsClient.class);

        TranslationService translationService = new TranslationService(pokemonService, funTranslationsClient);

        PokemonResponse mockPokemon = new PokemonResponseBuilder()
                .withName("mewtwo")
                .withDescription("A very powerful Pokémon")
                .withHabitat("rare")
                .withIsLegendary(true)
                .build();

        when(pokemonService.getPokemon("mewtwo")).thenReturn(mockPokemon);
        when(funTranslationsClient.translateYoda("A very powerful Pokémon")).thenReturn("Powerful, it is.");

        PokemonResponse translatedPokemon = translationService.getTranslatedPokemon("mewtwo");

        assertEquals("mewtwo", translatedPokemon.getName());
        assertEquals("rare", translatedPokemon.getHabitat());
        assertTrue(translatedPokemon.isLegendary());
        assertEquals("Powerful, it is.", translatedPokemon.getDescription());
    }

    @Test
    void getTranslatedPokemon_ShouldUseShakespeareTranslationForNonLegendaryNonCavePokemon() {
        PokemonService pokemonService = mock(PokemonService.class);
        FunTranslationsClient funTranslationsClient = mock(FunTranslationsClient.class);

        TranslationService translationService = new TranslationService(pokemonService, funTranslationsClient);

        PokemonResponse mockPokemon = new PokemonResponseBuilder()
                .withName("pikachu")
                .withDescription("A very energetic Pokémon")
                .withHabitat("forest")
                .withIsLegendary(false)
                .build();

        when(pokemonService.getPokemon("pikachu")).thenReturn(mockPokemon);
        when(funTranslationsClient.translateShakespeare("A very energetic Pokémon")).thenReturn("An energetic Pokémon, thou art.");

        PokemonResponse translatedPokemon = translationService.getTranslatedPokemon("pikachu");

        assertEquals("pikachu", translatedPokemon.getName());
        assertEquals("forest", translatedPokemon.getHabitat());
        assertFalse(translatedPokemon.isLegendary());
        assertEquals("An energetic Pokémon, thou art.", translatedPokemon.getDescription()); // Shakespeare translation expected
    }

    @Test
    void getTranslatedPokemon_ShouldUseOriginalDescriptionIfTranslationFails() {
        PokemonService pokemonService = mock(PokemonService.class);
        FunTranslationsClient funTranslationsClient = mock(FunTranslationsClient.class);

        TranslationService translationService = new TranslationService(pokemonService, funTranslationsClient);

        PokemonResponse mockPokemon = new PokemonResponseBuilder()
                .withName("mewtwo")
                .withDescription("A very powerful Pokémon")
                .withHabitat("rare")
                .withIsLegendary(true)
                .build();

        when(pokemonService.getPokemon("mewtwo")).thenReturn(mockPokemon);
        when(funTranslationsClient.translateYoda("A very powerful Pokémon")).thenReturn(null);

        PokemonResponse translatedPokemon = translationService.getTranslatedPokemon("mewtwo");

        assertEquals("mewtwo", translatedPokemon.getName());
        assertEquals("A very powerful Pokémon", translatedPokemon.getDescription());
    }
}
