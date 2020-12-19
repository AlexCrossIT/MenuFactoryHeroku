package com.factory.menufactory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factory.menufactory.dao.IngredientDao;
import com.factory.menufactory.model.Ingredient;
import com.factory.menufactory.other.Response;
import com.factory.menufactory.repository.IngredientRepository;

@Service
public class IngredientService {

	private final IngredientRepository ingredientRepository;
		
	private final IngredientDao ingredientDao;
		
	@Autowired
	public IngredientService(IngredientRepository ingredientRepository, IngredientDao ingredientDao) {
	
		this.ingredientRepository = ingredientRepository;
		this.ingredientDao = ingredientDao;
		
	}
	
	public Ingredient findById(String ingredientId) {
		
		return ingredientRepository.getOne(ingredientId);
		
	}
	
	public Response<String> findIngredientsForAutocomplete(String ingredientName) {
		
		return ingredientDao.findIngredientsForAutocomplete(ingredientName);
		
	}
	
	public List<Ingredient> findAll(){
		
		return ingredientRepository.findAll();
		
	}
		
	public void save(Ingredient ingredient) {
		
		ingredientRepository.save(ingredient);
		
	}
	
	public void deleteById(String ingredientId) {
		
		ingredientRepository.deleteById(ingredientId);
		
	}
	
}
