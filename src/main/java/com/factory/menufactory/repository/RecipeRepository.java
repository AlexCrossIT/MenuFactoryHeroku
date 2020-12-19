package com.factory.menufactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.menufactory.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, String>{

}
