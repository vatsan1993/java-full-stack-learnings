<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/header"></jsp:include>

<c:choose>
	<c:when test="${items == null} ">
		<p>No Items found</p>
	</c:when>
	<c:when test="${items.isEmpty()} ">
		<p>All items deleted</p>
	</c:when>
	<c:otherwise>
		<h3>Items</h3>
		<table border="1" cellspacing="5px" cellpadding="5px">
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
						<button>Edit</button>
					</form>
				</td>
				<td>
					<form action="/deleteItem">
						<input type="hidden" name="icode" value= "${item.getIcode() }">
						<button>Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

</body>
</html>