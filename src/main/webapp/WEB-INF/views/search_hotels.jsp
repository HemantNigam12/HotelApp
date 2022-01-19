<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 5px;
}
th {
  text-align: left;
}
</style>

<title>HotelApp</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div>
	<label>City:</label>
	<input type="text" id="city">
	<label>CheckIn Date:</label>
	<input type="date" id="chDt">
	<button type="button"  id="submit">Submit</button>
	</div>
	<div id="hotels" style="display: none;">
		<jsp:include page="hotels_details.jsp" />
	</div>
	
</body>
<script type="text/javascript">
				$('#submit').on('click', function () {
					var city = $("#city").val();
					var chDt = $("#chDt").val();
				if(city != "" && chDt != ""){
					$.ajax({
						url : 'getHotelsDetails?city=' + city+'&checkindt='+chDt,
						cache : false,
						success : function(data) {
							if (!$.trim(data)){   
								 $("#hotels").hide();
							    alert("Network Error");
							}
							else{ 
								 $("#hotels").show();
								 $("#hotels").html(data);
							}
						},
						error : function(data) {
							 alert("Network Error");
						}
					});
					}
				else{
					alert("Please fill city and date properly.");
				}
			});

	</script>
</html>