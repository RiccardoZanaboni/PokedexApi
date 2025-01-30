package com.pokedex.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokedex.PokemonPokedexApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PokemonPokedexApplication.class)
public class PokemonControllerIntegrationTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int port;

    @Test
    public void testGetPokemon() throws Exception {
        String url = "http://localhost:" + port + "/pokemon/mewtwo";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(200, response.getStatusCodeValue());

        // Parsing JSON response
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        assertEquals("mewtwo", jsonNode.get("name").asText());
        assertNotNull(jsonNode.get("habitat"));
        assertNotNull(jsonNode.get("legendary"));
    }

    @Test
    public void testGetTranslatedPokemon() throws Exception {
        String url = "http://localhost:" + port + "/pokemon/translated/mewtwo";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(200, response.getStatusCodeValue());

        // Parsing JSON response
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        assertEquals("mewtwo", jsonNode.get("name").asText());
        assertNotNull(jsonNode.get("description"));
        assertNotNull(jsonNode.get("habitat"));
        assertNotNull(jsonNode.get("legendary"));
    }
}
