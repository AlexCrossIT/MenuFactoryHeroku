package com.factory.menufactory.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.factory.menufactory.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, String>{

	@Query(nativeQuery = true, value = "" +

		"With recipeList AS (SELECT recipe_id, menu_id from recipe_lists where menu_id = :menuId)"+ "\n" +
		
		", ingredientList As ("+ "\n" +
		"	SELECT menu_id, ingredient_id, sum(ingredient_quantity) AS ingredient_quantity"+ "\n" +
		"	FROM ingredient_lists "+ "\n" +
		"	INNER JOIN recipeList ON ingredient_lists.recipe_id = recipeList.recipe_id"+ "\n" +
		"	GROUP BY ingredient_id "+ "\n" +
		")"+ "\n" +
		
		"SELECT ingredientList.menu_id, ingredients.ingredient_name, ingredientList.ingredient_quantity"+ "\n" +
		"FROM ingredientList"+ "\n" +
		"LEFT JOIN ingredients ON ingredientList.ingredient_id = ingredients.ingredient_id"
	)
	
	public String[] findShoppingListByMenuId(@Param("menuId") String menuId);
	
}
