<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="hotels" class="row">
	<div class="col-md-12 mb-4">
		<label><h2>Hotels Details</h2></label>
	</div>
	
	<div class="col-md-12">
		<table style="width:100%">
			<thead>
				<tr class="hthd">
					<th></th>
					<th>Hotel Name</th>
					<th>Rating</th>
					<th>Starting Price</th>
					<th>Currency</th>
					<th>Url</th>
				</tr>
				<tr class="rmhd" style="display: none;">
					<th></th>
					<th>RommUrl</th>
					<th>Price</th>
					<th>Available Rooms</th>
					<th>Type</th>
				</tr>
			</thead>
			<tbody id="hbdy">
				<%int i = 1;%>
				<c:forEach var="htl" items="${hotelDetails}">
					<tr class="hrow">
						<td><%=i%></td>
						<td>
						<a href="#" onclick="hotelDetails(<%=i%>)">${htl.hotelName}</a>
						</td>
						<td>${htl.rating}</td>
						<td>${htl.minPrice}</td>
						<td>${htl.currency}</td>
						<td><a href="${htl.hotelUrl}" target="_blank">${htl.hotelUrl}</a></td>
					</tr>
					<%int j = 1;%>
					<c:forEach var="rm" items="${htl.roomDetails}">
					<tr class="rmrow<%=i%>" style="display: none;">
						<td><%=j%>.<%j++;%></td>
						<td><a href="${rm.roomUrl}" target="_blank">${rm.roomUrl}</a></td>
						<td>${rm.price}</td>
						<td>${rm.availableRooms}</td>
						<td>${rm.roomType}</td> 
					</tr>
				</c:forEach> 
				<%i++;%>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">

function hotelDetails(val){
    $(".hthd").hide();
    $(".hrow").hide();
    $(".rmhd").show();
    $(".rmrow"+val).show();
}
	</script>
