package com.factory.menufactory.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.factory.menufactory.model.Ingredient;
import com.factory.menufactory.service.IngredientService;

@EnableRabbit
@Component
public class RabbitMqListener {

	private final IngredientService ingredientService;

	@Autowired
	public RabbitMqListener(IngredientService ingredientService) {
		
		this.ingredientService = ingredientService;
		
	}
	
	@RabbitListener(queues = "ingredientQueue")
	public void processIngredientMainQueue(Ingredient ingredient) {
		
		ingredientService.save(ingredient);
		
	}
	
}
