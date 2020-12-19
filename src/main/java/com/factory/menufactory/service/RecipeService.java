package com.factory.menufactory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.factory.menufactory.dao.RecipeDao;
import com.factory.menufactory.model.Recipe;
import com.factory.menufactory.other.Response;
import com.factory.menufactory.repository.RecipeRepository;

@Service
public class RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeDao recipeDao;
	
	@Autowired
	public RecipeService(RecipeRepository recipeRepository, RecipeDao recipeDao) {
		
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
	public void save(Recipe recipe) {
		
		recipeRepository.save(recipe);
				
	}
	
	public void deleteById(String recipeId) {
		
		recipeRepository.deleteById(recipeId);
		
	}
	
	
}
