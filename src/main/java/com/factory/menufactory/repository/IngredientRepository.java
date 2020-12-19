package com.factory.menufactory.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.menufactory.model.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, String>{

	
}
