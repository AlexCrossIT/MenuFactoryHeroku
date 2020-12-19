package com.factory.menufactory.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.factory.menufactory.model.ShoppingList;

import lombok.Data;

@Data
@Component
public class StringToShoppingListConverter implements Converter<String, ShoppingList>{


	@Override
	public ShoppingList convert(String source) {
		
		String[] data = source.split(",");
		
		return new ShoppingList(data[0], data[1], Integer.parseInt(data[2]));
		
	}

	
}
