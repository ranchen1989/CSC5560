$(document).ready(function(){
	$("#getvalue").click(function(){
		$('#answer').html('');
		
		$.ajax({
			type:"POST",
			url:"api/hello",
			contentType: "text",
			dataType:"application/json",
			data:$("#stemvalue").val(),
			success:function(data){
				$('#answer').html(data);
			}
		});
	});
	
});