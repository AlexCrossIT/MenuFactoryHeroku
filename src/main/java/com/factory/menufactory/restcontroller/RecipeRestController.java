package com.factory.menufactory.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.factory.menufactory.other.Response;
import com.factory.menufactory.service.RecipeService;

@RestController
public class RecipeRestController {

	private final RecipeService recipeService;
	
	@Autowired
	public RecipeRestController(RecipeService recipeService) {
		
		this.recipeService = recipeService;
		
	}
	

	@CrossOrigin
	@RequestMapping("/recipes-autocomplete")
	public ResponseEntity<String> findRecipesForAutocomplete(@RequestParam(name = "name") String recipeName) {
			
		Response<String> response = recipeService.findRecipesForAutocomplete(recipeName);
		
		if(response.getExeptionMessage().isEmpty()) {
			return new ResponseEntity<String>(response.getResult(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(response.getExeptionMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}
	
}
