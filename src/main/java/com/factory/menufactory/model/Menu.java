package com.factory.menufactory.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menus")
public class Menu {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Getter @Setter private String menuId;
	
	@Column(name = "menu_name")
	@Getter @Setter String menuName;
	
	@Fetch(value = FetchMode.SELECT)
	@ManyToMany
	@JoinTable(name = "recipe_lists", joinColumns = @JoinColumn(name = "menu_id"), 
									  inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	@Getter @Setter private List<Recipe> recipes;
	
	@Override
    public boolean equals(Object obj) {
	    
		if (obj == this) {
	        return true;
	    }
	    if (obj == null || obj.getClass() != this.getClass()) {
	        return false;
	    }
	
	    Menu menu = (Menu) obj;
	    return menuId.equals(menu.getMenuId()) && menuName.equals(menu.getMenuName())
	    		&& recipes.equals(menu.getRecipes());
	    
    }
	
	@Override
	public int hashCode() {
		
		int result = 17;
		
		result = 31 * result + menuId.hashCode() + menuName.hashCode() + recipes.hashCode();
		
		return result;
		
	}
	
}
