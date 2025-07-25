package com.example.chatgptbasedcookingingredients.controller;


import com.example.chatgptbasedcookingingredients.model.OpenAIRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAIResponse;
import com.example.chatgptbasedcookingingredients.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    // TODO: This should return "vegan", "vegetarian" or "regular" depending on the ingredient.

    @PostMapping
    public String sendChatGptRequest(@RequestBody String requestMessage) {
        return ingredientService.sendGptRequest(requestMessage);
    }

}
