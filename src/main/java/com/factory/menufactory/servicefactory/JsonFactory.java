package com.factory.menufactory.servicefactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.factory.menufactory.other.Response;

@Component
public class JsonFactory {
	
	private final DataSource dataSource;
	
	@Autowired
	public JsonFactory(DataSource dataSource) {
	
		this.dataSource = dataSource;
		
	}
	
	public Response<String> getAutocompleteData(String sqlQuery, String[] parametrs){
				
		JSONArray jsonArray = new JSONArray();
		
		Response<String> response = new Response<>("");
		
		try (Connection connection = dataSource.getConnection()){
						
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			
			for(int i = 0; i < parametrs.length; i++) {
				preparedStatement.setString(i + 1, parametrs[i]);
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("label", resultSet.getString("name"));
				jsonObject.put("value", resultSet.getString("name"));
				jsonObject.put("uuid",  resultSet.getString("id"));
				
				jsonArray.put(jsonObject);
				
			}
			
			response.setResult(jsonArray.toString());
			
		} catch (SQLException sqlExeption) {
			
			response.setExeptionMessage("Somthing wrong with database connection " + sqlExeption.toString());
		}
		
		catch (JSONException jsonExeption) {
			
			response.setExeptionMessage("Somthing wrong with JSON factory: " + jsonExeption.toString());
			
		}
				
		return response;
							
	}
	
}
