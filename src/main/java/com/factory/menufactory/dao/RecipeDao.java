package com.factory.menufactory.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.factory.menufactory.other.Response;
import com.factory.menufactory.servicefactory.JsonFactory;

@Repository
public class RecipeDao {

	private final JsonFactory jsonFactory;
	
	@Autowired
	public RecipeDao(JsonFactory jsonFactory) {

		this.jsonFactory = jsonFactory;
		
	}
		
	public Response<String> findRecipesForAutocomplete(String recipeName){
		
		String sqlQuery = "SELECT recipe_id AS id, recipe_name AS name FROM recipes WHERE recipe_name LIKE ?";
		String[] parametrs = {"%" + recipeName + "%"};
		
		return jsonFactory.getAutocompleteData(sqlQuery, parametrs);
				
	}
					
}
