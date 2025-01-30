package com.pokedex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.pokedex.client.PokeApiClient;
import com.pokedex.model.PokemonResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonService {

    private final PokeApiClient pokeApiClient;

    public PokemonService(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    public PokemonResponse getPokemon(String name) {
        return Optional.of(pokeApiClient.getPokemon(name))
                .map(this::parseResponse)
                .orElseThrow(IllegalStateException::new);
    }

    private PokemonResponse parseResponse(JsonNode response) {
        String name = Optional.ofNullable(response.get("name"))
                .map(JsonNode::asText)
                .orElse("Unknown");

        String habitatName = Optional.ofNullable(response.get("habitat"))
                .map(node -> node.get("name"))
                .map(JsonNode::asText)
                .orElse("Unknown habitat");

        Boolean isLegendary = Optional.ofNullable(response.get("is_legendary"))
                .map(JsonNode::asBoolean)
                .orElse(false);

        JsonNode flavorTextEntries = response.get("flavor_text_entries");
        String description = "No description available";

        if (flavorTextEntries != null && flavorTextEntries.isArray()) {
            for (JsonNode entry : flavorTextEntries) {
                JsonNode language = entry.get("language");
                if (language != null && "en".equals(language.get("name").asText())) {
                    description = entry.get("flavor_text").asText();
                    description = cleanDescription(description);
                    break;
                }
            }
        }

        return new PokemonResponse(name, description, habitatName, isLegendary);
    }

    private String cleanDescription(String description) {
        description = description.replace("\n", " ")
                .replace("\f", " " )
                .replace("\t", " ");
        return description;
    }

}
