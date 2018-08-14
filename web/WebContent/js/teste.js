$().ready( function(){
	eventsRegistration();
});

var eventsRegistration = function(){
	
	$("#btnTeste").click(function(){
		alert("Teste clicked");
		$("#btnTeste").attr("disabled","disabled");
	});
	
	$("#btnAnotherTeste").click(function(){
		alert("AnotherTeste clicked");
		$("#btnAnotherTeste").attr("disabled","disabled");
	});

	
	
	
} ;