package com.factory.menufactory.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.factory.menufactory.model.Ingredient;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IngredientRepositoryIngtegrationTest {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Test
	@Transactional
	public void shouldCreateAndDeleteNewIngredient() throws Exception{
	
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientName("CreateAndRemoveNewIngredient");
		ingredientRepository.save(ingredient);
		ingredientRepository.flush();
				
		Ingredient actualIngredient = ingredientRepository.findById(ingredient.getIngredientId()).get();		
		
		assertEquals(ingredient, actualIngredient);
		
		ingredientRepository.delete(ingredient);
		ingredientRepository.flush();
		
		assertEquals(false, ingredientRepository.findById(ingredient.getIngredientId()).isPresent());
		
	}

	
}
