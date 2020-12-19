package com.factory.menufactory.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopping_lists")
public class ShoppingList {

	@Id
	private String menuId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@Getter @Setter private Menu menu;
	
	@Getter @Setter private String ingredientName;
	@Getter @Setter private int ingredientQuantity;
	
	public ShoppingList() {
		
	}
	
	public ShoppingList(String menuId, String ingredientName, int ingredientQuantity) {
		
		this.menuId = menuId;
		this.ingredientName = ingredientName;
		this.ingredientQuantity = ingredientQuantity;
		
	}
	
}
