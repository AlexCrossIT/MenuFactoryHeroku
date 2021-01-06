package com.factory.menufactory.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ingredient_lists")
@IdClass(IngredientListPK.class)
public class IngredientList{
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_id")
	@Getter @Setter Recipe recipe;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	@Getter @Setter Ingredient ingredient;
	
	@Column(name = "ingredient_quantity")
	@Getter @Setter int ingredientQuantity;
	
	public IngredientList() {
		
	}
	
	public IngredientList(Recipe recipe, Ingredient ingredient, int ingredientQuantity) {
		
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.ingredientQuantity = ingredientQuantity;
		
	}
	
	@Override
    public boolean equals(Object obj) {
	    
		if (obj == this) {
	        return true;
	    }
	    if (obj == null || obj.getClass() != this.getClass()) {
	        return false;
	    }
	
	    IngredientList ingredientList = (IngredientList) obj;
	    return recipe.equals(ingredientList.getRecipe()) && ingredient.equals(ingredientList.getIngredient()) 
	    		&& ingredientQuantity == ingredientList.getIngredientQuantity();
	    
    }
	
	@Override
	public int hashCode() {
		
		int result = 17;
		
		result = 31 * result + (recipe == null ? 0: recipe.hashCode()) 
					+ (ingredient == null ? 0: ingredient.hashCode()) + ingredientQuantity;
		
		return result;
		
	}
	
}
