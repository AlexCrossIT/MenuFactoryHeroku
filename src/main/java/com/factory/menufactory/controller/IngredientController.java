package com.factory.menufactory.controller;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.factory.menufactory.model.Ingredient;
import com.factory.menufactory.service.IngredientService;

@Controller
public class IngredientController {
	
	private final IngredientService ingredientService;
	private final AmqpTemplate amqpTemplate;
	
	@Autowired
	public IngredientController(IngredientService ingredientService, AmqpTemplate amqpTemplate) {
		
		this.ingredientService = ingredientService;
		this.amqpTemplate = amqpTemplate;
		
	}
	
	@GetMapping("/ingredient-list")
	public String findAll(Model model) {
				
		List<Ingredient> ingredientList = ingredientService.findAll();
				
		model.addAttribute("ingredientList", ingredientList);
		
		return "ingredient-list";
		
	}
		
	@GetMapping("/ingredient-edit")
	public String editIngredientForm(Ingredient ingredient) {
				
		return "ingredient-edit";
		
	}
	
	@GetMapping("/ingredient-edit/{ingredientId}")
	public String editIngredientForm(@PathVariable("ingredientId") String ingredientId, Model model) {
				
		Ingredient ingredient = ingredientService.findById(ingredientId);
		
		model.addAttribute("ingredient", ingredient);
		
		return "ingredient-edit";
		
	}	
	
	@PostMapping("/ingredient-edit")
	public String createIngredient(Ingredient ingredient) {
		
		amqpTemplate.convertAndSend(ingredient);
		
		return "redirect:/ingredient-list";
		
	}
		
	@GetMapping("/ingredient-delete/{ingredientId}")
	public String delete(@PathVariable("ingredientId") String ingredientId) {
		
		ingredientService.deleteById(ingredientId);
		
		return "redirect:/ingredient-list";
		
	}
	
}
