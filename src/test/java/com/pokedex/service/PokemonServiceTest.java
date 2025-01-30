package com.pokedex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.pokedex.client.PokeApiClient;
import com.pokedex.model.PokemonResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PokemonServiceTest {

    @Test
    void getPokemon() {
        PokeApiClient pokeApiClient = mock(PokeApiClient.class);
        JsonNode mockResponse = mock(JsonNode.class);

        when(mockResponse.get("name")).thenReturn(mock(JsonNode.class));
        when(mockResponse.get("name").asText()).thenReturn("mewtwo");

        PokemonService pokemonService = new PokemonService(pokeApiClient);

        Mockito.when(pokeApiClient.getPokemon(anyString())).thenReturn(mockResponse);

        PokemonResponse result = pokemonService.getPokemon("mewtwo");

        assertEquals("mewtwo", result.getName());
    }
}
