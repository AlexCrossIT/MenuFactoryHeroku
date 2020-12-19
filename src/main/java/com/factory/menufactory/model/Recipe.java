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
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Getter @Setter List<IngredientList> ingredientList;
	
	@Fetch(value = FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "recipes")
	@Getter @Setter List<Menu> menus;
	
	public Recipe() {
		
	}
	
	public Recipe(String recipeId, String recipeName, List<IngredientList> ingredientList) {
		
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.ingredientList = ingredientList;
		
	}
	
}
