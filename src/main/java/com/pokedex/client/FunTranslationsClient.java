package com.pokedex.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.pokedex.exception.TranslationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
public class FunTranslationsClient {

    private final RestTemplate restTemplate;

    public FunTranslationsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String translateYoda(String text) {
        return translate("https://api.funtranslations.com/translate/yoda.json", text);
    }

    public String translateShakespeare(String text) {
        return translate("https://api.funtranslations.com/translate/shakespeare.json", text);
    }

    private String translate(String url, String text) {
        try {
            JsonNode response = restTemplate.postForObject(url, Map.of("text", text), JsonNode.class);
            return Optional.ofNullable(response)
                    .map(resp -> resp.get("contents"))
                    .map(contents -> contents.get("translated"))
                    .map(JsonNode::asText)
                    .orElseThrow(() -> new IllegalStateException("Error translating pokemon description"));

        } catch (Exception e) {
            throw new TranslationException("Error translating pokemon description - " + e);
        }
    }
}
