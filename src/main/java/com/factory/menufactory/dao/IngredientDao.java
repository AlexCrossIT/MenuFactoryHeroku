package com.factory.menufactory.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.factory.menufactory.other.Response;
import com.factory.menufactory.servicefactory.JsonFactory;

@Repository
public class IngredientDao {

	private final JsonFactory jsonFactory;
	
	@Autowired
	public IngredientDao(JsonFactory jsonFactory) {

		this.jsonFactory = jsonFactory;
		
	}
		
	public Response<String> findIngredientsForAutocomplete(String ingredientName){
		
		String sqlQuery = "SELECT ingredient_id AS id, ingredient_name AS name FROM ingredients WHERE ingredient_name LIKE ?";
		String[] parametrs = {"%" + ingredientName + "%"};		
						
		return jsonFactory.getAutocompleteData(sqlQuery, parametrs);
							
	}
						
}
