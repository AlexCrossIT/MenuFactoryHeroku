
var DynamicTable = (function (GLOB) {
    
    var RID = 0;

	var requestUrl = "";
    
    return function (tBody, urlAdress) {
        
		requestUrl = new URL(urlAdress);

        if (!(this instanceof arguments.callee)) {
            return new arguments.callee.apply(arguments);
        }
       
        tBody.onclick = function(e) {
            var evt = e || GLOB.event,
                trg = evt.target || evt.srcElement;
            if (trg.className && trg.className.indexOf("add") !== -1) {
                _addRow(trg.parentNode.parentNode, tBody);				
            } else if (trg.className && trg.className.indexOf("del") !== -1) {
                tBody.rows.length > 1 && _delRow(trg.parentNode.parentNode, tBody);
            }
        };

        var _rowTpl = tBody.rows[0].cloneNode(true);
      
        var _correctNames = function (row, postfix, before, tBody) {
            var elements = row.getElementsByTagName("*");
            for (var i = 0; i < elements.length; i += 1) {
                if (elements.item(i).name) {
						
					elements.item(i).id = elements.item(i).id + postfix + RID;
					
					if(elements.item(i).className === "table-input-id"){
						elements.item(i).setAttribute("name", "recipes[" + RID + "]." + "recipeId");	
					}else if(elements.item(i).className === "table-input-name"){
						elements.item(i).setAttribute("name", "recipes[" + RID + "]." + "recipeName");
					}
					
					if(postfix === "-new"){
						elements.item(i).value = "";
						tBody.insertBefore(row, before.previousSibling);	
					}
                }
            }
            RID++;

			$('.table-input-name').autocomplete({
			
				autoFocus: true,
				delay: 500,
				minLength: 3,
				source: function(request, response) {
			
					let xhr = new XMLHttpRequest();
				    
					requestUrl.searchParams.set("name", request.term);
				    
				    xhr.open("GET", requestUrl, true);
				    
				    xhr.setRequestHeader("Content-Type", "application/json");
				   
					xhr.send();
	
				    xhr.onreadystatechange = function () {
				      
				      if (xhr.readyState === 4) {
				        
						if(xhr.status === 200){
				        	response(JSON.parse(this.responseText));
						} else {
							alert(this.responseText);
						}
				             
				      }
				    };              
				          
					         
				 	}, 
				
				select: function(event, ui){
							$('#' + ui.item.id).val(ui.item.label); 
							$('#' + 'id-' + $(this).attr('id')).val(ui.item.uuid);
							}
				});

        };

        var _addRow = function (before, tBody) {
			RID = $('.table-input-id').length;
           _correctNames(_rowTpl.cloneNode(true), "-new", before, tBody);
        };

        var _delRow = function (row, tBody) {
            tBody.removeChild(row);
        };

        _correctNames(tBody.rows[0], "", "", "");

    };

})(this);
