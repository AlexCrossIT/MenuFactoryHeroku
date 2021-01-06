package com.factory.menufactory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factory.menufactory.dao.RecipeDao;
import com.factory.menufactory.model.Ingredient;
import com.factory.menufactory.model.IngredientList;
import com.factory.menufactory.model.Recipe;
import com.factory.menufactory.other.Response;
import com.factory.menufactory.repository.RecipeRepository;

@Service
public class RecipeService {

	private final IngredientService ingredientService;
	private final RecipeRepository recipeRepository;
	private final RecipeDao recipeDao;
	
	@Autowired
	public RecipeService(RecipeRepository recipeRepository, RecipeDao recipeDao, IngredientService ingredientService) {
		
		this.ingredientService = ingredientService;
		this.recipeRepository = recipeRepository;
		this.recipeDao = recipeDao;
		
	}
	
	
	public Recipe findById(String recipeId) {
		
		return recipeRepository.getOne(recipeId);
		
	}
	
	public List<Recipe> findAll(){
		
		return recipeRepository.findAll();
				
	}
	
	public Response<String> findRecipesForAutocomplete(String recipeName){
		
		return recipeDao.findRecipesForAutocomplete(recipeName);
				
	}
	
	@Transactional
	public void save(String jsonString) {
		
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
			
			ingredient.setIngredientList(ingredientList);
			
			ingredientList.add(new IngredientList(recipe, ingredient, ingredientQuantity));
			
		}
			
		
		recipeRepository.save(recipe);
				
	}
	
	
	public void deleteById(String recipeId) {
		
		recipeRepository.deleteById(recipeId);
		
	}
	
	public void delete(Recipe recipe) {
		
		recipeRepository.delete(recipe);
		
	}
	
}
