
window.onload = function(){
	
	function sendFormData(){
				
		var recipeName = "";
		var recipeId = "";		
		var ingredients = [];	
		var ingredient = {};	
		var result = {};
			
		var formData = new FormData(document.forms.recipeForm);	
				
		formData.forEach(function(value, key){
								
			if(key === "recipeName"){
				recipeName = value;	
			}
						
			if(key === "recipeId"){
				recipeId = value;	
			}
			
			if(key === "ingredient-id" || key === "ingredient-quantity"){
								
				if(key === "ingredient-id"){
					ingredient = {};
					ingredient["ingredientId"] = value;
				}
				
				if(key === "ingredient-quantity"){
					ingredient["ingredientQuantity"] = value;
					ingredients.push(ingredient);
				}
				
			}
			
		});
		
		result["recipeId"] = recipeId;
		result["recipeName"] = recipeName;
		result["ingredients"] = ingredients;
				
		let xhr = new XMLHttpRequest();
				    
	    let url = "https://menufactory.herokuapp.com/recipe-edit";
	    
	    xhr.open("POST", url, true);
	    
	    xhr.setRequestHeader("Content-Type", "application/json");
	   
	    xhr.onreadystatechange = function () {
	      
	      if (xhr.readyState === 4) {
	        
			if(xhr.status === 200){
	        	window.location.pathname = this.responseText;
			} else {
				alert(this.responseText);
			}
	             
	      }
	    };              
	          
		xhr.send(JSON.stringify(result));
		
	}
		
	let saveButton = document.getElementById("saveButton");
			
	saveButton.addEventListener('click', sendFormData, false);
		
}
