package com.factory.menufactory.other;

import lombok.Data;

@Data
public class Response<T> {
	
	private T result;
	private String exeptionMessage;
	
	public Response() {
				
	}
	
	public Response(T result) {
		
		this.result = result;
		
	}

	public Response(String exeptionMessage) {
		
		this.exeptionMessage = exeptionMessage;
		
	}
	
	public Response(T result, String exeptionMessage) {
		
		this.result = result;
		this.exeptionMessage = exeptionMessage;
		
	}
	
}
