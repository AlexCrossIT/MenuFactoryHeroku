package com.factory.menufactory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "ingredients")
public class Ingredient {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Getter @Setter private String ingredientId;
	
	@Column(name = "ingredient_name")
	@Getter @Setter private String ingredientName;
	
	@OneToMany(mappedBy = "ingredient")
	@Getter @Setter private List<IngredientList> ingredientList;
	
	@Override
    public boolean equals(Object obj) {
	    
		if (obj == this) {
	        return true;
	    }
	    if (obj == null || obj.getClass() != this.getClass()) {
	        return false;
	    }
	
	    Ingredient ingredient = (Ingredient) obj;
	    return ingredientId.equals(ingredient.getIngredientId()) && ingredientName.equals(ingredient.getIngredientName());
	    
    }
	
	@Override
	public int hashCode() {
		
		int result = 17;
		
		result = 31 * result + ingredientId.hashCode() + ingredientName.hashCode();
		
		return result;
		
	}
	
}
