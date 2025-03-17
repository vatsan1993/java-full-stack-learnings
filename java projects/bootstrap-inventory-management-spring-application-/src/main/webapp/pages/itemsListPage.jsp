<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/header"></jsp:include>
<section class="container-fluid p-4">
<c:choose>
	<c:when test="${items == null} ">
		<p class="alert alert-info p-2 m-2">No Items found</p>
	</c:when>
	<c:when test="${items.isEmpty()} ">
		<p class="alert alert-info p-2 m-2">All items deleted</p>
	</c:when>
	<c:otherwise>
		<h3>Items</h3>
		<table class="table table-stripped table-hover" border="1" cellspacing="5px" cellpadding="5px">
			<tr>
				<th>Icode</th>
				<th>Title</th>
				<th>Is Fragile</th>
				<th>Package Date</th>
				<th>Unit</th>
				<th>Cost Price</th>
				<th>Selling Price</th>
				<th> </th>
				<th> </th>
				
			</tr>
		<c:forEach var="item" items="${ items }">
			<tr>
				<td>${item.getIcode() }</td>
				<td>${item.getTitle() }</td>
				<td>${item.getFragile() }</td>
				<td>${item.getPackageDate() }</td>
				<td>${item.getUnit()} </td>
				<td>${item.getCostPrice() }</td>
				<td>${item.getSellingPrice() }</td>
				<td>
					<form action="/editItem">
						<input type="hidden" name="icode" value= "${item.getIcode() }" }>
						<button class="btn btn-sm btn-primary">Edit</button>
					</form>
				</td>
				<td>
					<form action="/deleteItem">
						<input type="hidden" name="icode" value= "${item.getIcode() }">
						<button class="btn btn-sm btn-danger">Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

</section>

</body>
</html>