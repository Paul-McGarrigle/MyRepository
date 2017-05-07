$(document).ready(function(){	
$("#btn").click(function(){	
		
		//-- retrieves IMSI from form --//
		name = $("#name").val();
		password = $("#password").val();
		var vdata = "name=ravi&age=31";
		$.ajax({
			type:"POST",
			url:"rest/tracks/register",
			dataType:"json",
			success: function(data) {
				console.log("SDFGHJHGFDSDFGHJHGFDSASDFGHJHGFDS");
				$("#responseHolder").empty();
				
				$.each(data.baseDataList, function(index, value){
					$("#responseHolder").append
						("<li> Event Id: "+value.event_id+" Cause Code: "+value.cause_code+"</li>");
				});	
			}
	
		});
	
	});
});