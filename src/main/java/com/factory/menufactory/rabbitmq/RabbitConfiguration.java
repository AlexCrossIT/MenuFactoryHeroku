package com.factory.menufactory.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {

	public ConnectionFactory connectionFactory() {
		
		return new CachingConnectionFactory("localhost");
		
	}
	
	public AmqpAdmin amqpAdmin() {
		
		return new RabbitAdmin(connectionFactory());
		
	}
	
	public RabbitTemplate rabbitTemplate() {
		
		return new RabbitTemplate(connectionFactory());
		
	}
	
	public Queue ingredientQueue() {
		
		return new Queue("ingredientQueue");
		
	}
	
}
