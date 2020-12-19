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
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_lists", joinColumns = @JoinColumn(name = "menu_id"), 
									  inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	@Getter @Setter private List<Recipe> recipes;
	
	
}
