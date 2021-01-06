package com.factory.menufactory.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class IngredientListPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private String recipe;
	@Getter @Setter private String ingredient;
	
	@Override
	public int hashCode() {
		
		return recipe.hashCode() + ingredient.hashCode();
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == this) {
			return true;
		}
		
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		IngredientListPK ingredientListPK = (IngredientListPK) obj;
		
		return recipe.equals(ingredientListPK.getRecipe()) && ingredient.equals(ingredientListPK.getIngredient());
		
	}
}
