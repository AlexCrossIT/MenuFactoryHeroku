package com.factory.menufactory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="ingredient_lists")
public class IngredientList {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
	String ingredientListId;
	
	@ManyToOne
	@JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
	Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	Ingredient ingredient;
	
	@Column(name = "ingredient_quantity")
	int ingredientQuantity;
	
	public IngredientList() {
		
	}
	
	public IngredientList(Recipe recipe, Ingredient ingredient, int ingredientQuantity) {
		
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.ingredientQuantity = ingredientQuantity;
		
	}
	
}
