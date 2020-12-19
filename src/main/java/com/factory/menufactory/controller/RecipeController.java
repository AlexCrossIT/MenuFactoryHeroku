package com.factory.menufactory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.factory.menufactory.model.Ingredient;
import com.factory.menufactory.model.IngredientList;
import com.factory.menufactory.model.Recipe;
import com.factory.menufactory.service.IngredientService;
import com.factory.menufactory.service.RecipeService;

@Controller
public class RecipeController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	
	@Autowired
	public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
		
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		
	}
	
	@GetMapping("/recipe-list")
	public String findAll(Model model) {
		
		List<Recipe> recipeList = recipeService.findAll();
		
		model.addAttribute("recipeList", recipeList);
		
		return "recipe-list";
	}
		
	@GetMapping("/recipe-edit")
	public String editRecipeForm(Recipe recip) {
				
		return "recipe-edit";
		
	}
	
	@GetMapping("/recipe-edit/{recipeId}")
	public String editRecipeForm(@PathVariable("recipeId") String recipeId, Model model) {
						
		model.addAttribute("recipe", recipeService.findById(recipeId));
		
		return "recipe-edit";
		
	}
	
	@PostMapping("/recipe-edit")
	public ResponseEntity<String> editRecipe(@RequestBody String jsonString) {
		
		JSONObject jsonData = new JSONObject(jsonString);
					
		JSONArray jsonArray = jsonData.getJSONArray("ingredients");
		
		List<IngredientList> ingredientList = new ArrayList<>();
		
		String recipeId = (String) jsonData.get("recipeId");
		String recipeName = (String) jsonData.get("recipeName");
		
		if(recipeId.isEmpty()) {
			recipeId = UUID.randomUUID().toString();
		}
		
		Recipe recipe = new Recipe(recipeId, recipeName, ingredientList);
		
		for(int i = 0; i < jsonArray.length(); i++) {
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			int ingredientQuantity = jsonObject.getInt("ingredientQuantity");
			
			Ingredient ingredient = ingredientService.findById(jsonObject.getString("ingredientId"));
			
			ingredientList.add(new IngredientList(recipe, ingredient, ingredientQuantity));
			
		}
				
		recipeService.save(recipe);
		
		return new ResponseEntity<String>("/menufactory/recipe-list", HttpStatus.OK);
		
	}
	
	@GetMapping("/recipe-delete/{recipeId}")
	public String delete(@PathVariable("recipeId") String recipeId) {
	
		recipeService.deleteById(recipeId);
		
		return "redirect:/recipe-list";
		
	}
		
}
