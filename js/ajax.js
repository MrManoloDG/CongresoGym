
function crearEditar(id = "") {
	var name = $("#name").val();
	var plan = $("#plan").val();
	var date_start = $("#fechaini").val();
	var date_end = $("#fechafin").val();
	var telf = $("#telf").val();
	var email = $("#email").val();
	var comment = $("#msg").val();
	alert(id);
	if(id == ""){
		$.ajax({
	        type: "POST",
	        url: "http://localhost:8080/users/",
	        contentType: "application/json",
	        dataType: "text",
	        data: JSON.stringify({
	            "name": name,
	            "plan": plan,
	            "date_start": date_start,
				"date_end": date_end,
				"telf": telf,
				"email": email,
				"comment": comment
	        }),
			success: function(data) {
				swal({
	              title: "Inscripción realizada con exito",
	              icon: "success",
	              button: "Volver",
	            });
	        },
	        error: function(res) {
				swal({
	              title: "Oooops!",
	              text: "ERROR " + res.statusText,
	              icon: "warning",
	              button: "Volver",
	            });
	        }
	    });
	} else {
		$.ajax({
	        type: "PUT",
	        url: "http://localhost:8080/users/"+id,
	        contentType: "application/json",
	        dataType: "text",
	        data: JSON.stringify({
	            "name": name,
	            "plan": plan,
	            "date_start": date_start,
				"date_end": date_end,
				"telf": telf,
				"email": email,
				"comment": comment
	        }),
			success: function(data) {
				swal({
	              title: "Modificación de inscripción realizada con exito",
	              icon: "success",
	              button: "Volver",
	            });
	        },
	        error: function(res) {
				swal({
	              title: "Oooops!",
	              text: "ERROR " + res.statusText,
	              icon: "warning",
	              button: "Volver",
	            });
	        }
	    });
	}
}
