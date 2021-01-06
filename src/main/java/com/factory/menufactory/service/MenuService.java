package com.factory.menufactory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.factory.menufactory.model.Menu;
import com.factory.menufactory.model.ShoppingList;
import com.factory.menufactory.repository.MenuRepository;

@Service
public class MenuService {

	private final MenuRepository menuRepository;
	private final ConversionService conversionService;
	
	@Autowired
	public MenuService(MenuRepository menuRepository, ConversionService conversionService) {
		
		this.menuRepository = menuRepository;
		this.conversionService = conversionService;
		
	}
	
	public Menu findById(String menuId) {
		
		return menuRepository.getOne(menuId);
		
	}
	
	public List<Menu> findAll(){
		
		return menuRepository.findAll();
		
	}
	
	public void save(Menu menu) {
		
		menuRepository.save(menu);
		
	}
	
	public void deleteById(String menuId) {
		
		menuRepository.deleteById(menuId);
		
	}
	
	public void delete(Menu menu) {
		
		menuRepository.delete(menu);
		
	}
	
	public List<ShoppingList> getShoppingListByMenuId(String menuId) {
		
		List<ShoppingList> shoppingList = new ArrayList<>();
		
		String[] result = menuRepository.findShoppingListByMenuId(menuId);
		
		for(int i = 0; i < result.length; i++) {
			shoppingList.add(conversionService.convert(result[i], ShoppingList.class));
		}
		
		return shoppingList;
		
	}
	
}
