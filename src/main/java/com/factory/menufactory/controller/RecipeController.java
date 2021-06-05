package com.factory.menufactory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.factory.menufactory.model.Menu;
import com.factory.menufactory.model.Recipe;
import com.factory.menufactory.service.MenuService;
import com.factory.menufactory.service.RecipeService;

@CrossOrigin
@Controller
public class RecipeController {

	private final MenuService menuService;
	private final RecipeService recipeService;
	
	@Autowired
	public RecipeController(MenuService menuService, RecipeService recipeService) {
		
		this.menuService = menuService;
		this.recipeService = recipeService;
		
	}
	
	@GetMapping("/recipe-list")
	public String findAll(Model model) {
		
		List<Recipe> recipeList = recipeService.findAll();
		
		model.addAttribute("recipeList", recipeList);
		
		return "recipe-list";
	}
		
	@GetMapping("/recipe-edit")
	public String editRecipeForm(Recipe recip) {
				
		return "recipe-edit";
		
	}
	
	@GetMapping("/recipe-edit/{recipeId}")
	public String editRecipeForm(@PathVariable("recipeId") String recipeId, Model model) {
						
		model.addAttribute("recipe", recipeService.findById(recipeId));
		
		return "recipe-edit";
		
	}
	
	@PostMapping("/recipe-edit")
	public ResponseEntity<String> editRecipe(@RequestBody String jsonString) {
						
		recipeService.save(jsonString);
		
		return new ResponseEntity<String>("/recipe-list", HttpStatus.OK);
		
	}
	
	@GetMapping("/recipe-delete/{recipeId}")
	public String delete(@PathVariable("recipeId") String recipeId) {
	
		Recipe recipe = recipeService.findById(recipeId);
		
		List<Menu> menuList = recipe.getMenus();
		
		recipeService.delete(recipe);
		
		for (Menu menu : menuList) {
			if(menu.getRecipes().size() < 2) {
				menuService.delete(menu);
			}
		}
		
		return "redirect:/recipe-list";
		
	}
		
}
