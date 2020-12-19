package com.factory.menufactory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

//

@Data
@Entity
@Table(name = "ingredients")
public class Ingredient {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String ingredientId;
	
	@Column(name = "ingredient_name")
	private String ingredientName;
	
	
}
