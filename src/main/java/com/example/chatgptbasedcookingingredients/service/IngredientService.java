package com.example.chatgptbasedcookingingredients.service;

import com.example.chatgptbasedcookingingredients.model.OpenAIRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@Service
public class IngredientService {

    private final RestClient restClient;

    public IngredientService(RestClient.Builder restClientBuilder,
                             @Value("${OPENAI_KEY}") String apiKey)
    {
        this.restClient = restClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    } //constructor

    public String sendGptRequest(String requestMessage) {
        OpenAIResponse newAIResponse = restClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new OpenAIRequest("Give me one word answer among vegan, vegetarian, or regular for the item: " + requestMessage))
        //          .body(new OpenAIRequest(requestMessage))
                .retrieve()
                .body(OpenAIResponse.class);
        return newAIResponse.justText();
    }
}  // Class
