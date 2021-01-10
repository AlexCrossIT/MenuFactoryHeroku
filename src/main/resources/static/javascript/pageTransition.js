$(document).ready(function() {
	
    $("body").css("display", "none");	

    window.addEventListener("pageshow", function ( event ) {
	  var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
	  if ( historyTraversal ) {
	    $("body").css("display", "block");
	  }
	});

    $("body").fadeIn(3000);
    
	$("a").click(function(event){
		event.preventDefault();
		linkLocation = this.href;
		$("body").fadeOut(1000, redirectPage);		
	});
	$("area").click(function(event){
		event.preventDefault();
		linkLocation = this.href;
		$("body").fadeOut(500, redirectPage);		
	});
		
	function redirectPage() {
		window.location = linkLocation;
	}
	
});
