package com.factory.menufactory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="recipes")
public class Recipe {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Getter @Setter private String recipeId;
	
	@Column(name = "recipe_name")
	@Getter @Setter private String recipeName;
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	@Getter @Setter private List<IngredientList> ingredientList;
	
	@Fetch(value = FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "recipes")
	@Getter @Setter private List<Menu> menus;
	
	public Recipe() {
		
	}
	
	public Recipe(String recipeId, String recipeName, List<IngredientList> ingredientList) {
		
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.ingredientList = ingredientList;
		
	}
	
	@PreRemove
	public void removeMenus() {
		
		menus.forEach(menu -> menu.getRecipes().remove(this));
		
	}
	
	@Override
    public boolean equals(Object obj) {
	    
		if (obj == this) {
	        return true;
	    }
	    if (obj == null || obj.getClass() != this.getClass()) {
	        return false;
	    }
	
	    Recipe recipe = (Recipe) obj;
	    return recipeId.equals(recipe.getRecipeId()) && recipeName.equals(recipe.getRecipeName())
	    		&& ingredientList.equals(recipe.getIngredientList());
	    
    }
	
	@Override
	public int hashCode() {
		
		int result = 17;
		
		result = 31 * result + recipeId.hashCode() + recipeName.hashCode() + ingredientList.hashCode();
		
		return result;
		
	}
	
}
