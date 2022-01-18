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
				<tr>
					<th></th>
					<th>Hotel Name</th>
					<th>Rating</th>
					<th>Starting Price</th>
					<th>Currency</th>
					<th>Url</th>
				</tr>
			</thead>
			<tbody>
				<%int i = 1;%>
				<c:forEach var="htl" items="${hotelDetails}">
					<tr>
						<td><%=i%>.<%i++;%></td>
						<td>${htl.hotelName}</td>
						<td>${htl.rating}</td>
						<td>${htl.minPrice}</td>
						<td>${htl.currency}</td>
						<td><a href="${htl.hotelUrl}" target="_blank">${htl.hotelUrl}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
