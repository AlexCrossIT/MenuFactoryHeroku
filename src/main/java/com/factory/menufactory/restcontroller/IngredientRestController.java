package com.factory.menufactory.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factory.menufactory.other.Response;
import com.factory.menufactory.service.IngredientService;

@RestController
public class IngredientRestController {

	private final IngredientService ingredientService;
	
	@Autowired
	public IngredientRestController(IngredientService ingredientService) {
		
		this.ingredientService = ingredientService;
		
	}
	
	
	@RequestMapping(value = "/ingredients-autocomplete")
	public ResponseEntity<String> findIngredientsForAutocomplete(@RequestParam(value = "name") String ingredientName) {
		
		Response<String> response = ingredientService.findIngredientsForAutocomplete(ingredientName);
		
		if(response.getExeptionMessage().isEmpty()) {
			return new ResponseEntity<String>(response.getResult(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(response.getExeptionMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		
	}
	
}
