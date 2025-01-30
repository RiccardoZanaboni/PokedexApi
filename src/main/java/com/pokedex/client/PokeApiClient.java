package com.pokedex.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.pokedex.exception.PokemonNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiClient {

    private final RestTemplate restTemplate;

    public PokeApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode getPokemon(String name) {
        try {
            String url = "https://pokeapi.co/api/v2/pokemon-species/" + name;
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            return null;
        } catch (HttpClientErrorException e) {
            throw new PokemonNotFoundException("Could not get Pokemon: " + name + " -" + e);
        }
    }

}
