package com.factory.menufactory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.factory.menufactory.model.Menu;
import com.factory.menufactory.service.MenuService;

@Controller
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		
		this.menuService = menuService;
		
	}
	
	
	@GetMapping("/menu-list")
	public String findAll(Model model){
		
		model.addAttribute("menuList", menuService.findAll());
		
		return "/menu-list";
		
	}
	
	@GetMapping("/menu-edit")
	public String createMenuEditForm(Menu menu) {
			
		return "/menu-edit";
		
	}
	
	@GetMapping("/menu-edit/{menuId}")
	public String editMenu(@PathVariable("menuId") String menuId, Model model) {
						
		model.addAttribute("menu", menuService.findById(menuId));
		
		return "/menu-edit";
		
	}
	
	@PostMapping("/menu-edit")
	public String editMenu(Menu menu) {
		
		menuService.save(menu);
		
		return "redirect:/menu-list";
		
	}
	
	@GetMapping("/menu-delete/{menuId}")
	public String deleteById(@PathVariable("menuId") String menuId) {
		
		menuService.deleteById(menuId);
		
		return "redirect:/menu-list";
		
	}
	
	@GetMapping("/shopping-list/{menuId}")
	public String shoppingList(@PathVariable("menuId") String menuId, Model model) {
				
		model.addAttribute("shoppingList", menuService.getShoppingListByMenuId(menuId));
		
		return "/shopping-list";
		
	}
	
}
