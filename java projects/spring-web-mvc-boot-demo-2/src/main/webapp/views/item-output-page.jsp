<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header" />
	<section>
		<table>
			<tr><td>Icode: </td><td>${item.icode }</td></tr>
			<tr><td>name: </td><td>${item.name }</td></tr>
			<tr><td>Price </td><td>${item.price }</td></tr>
			<tr><td>Category: </td><td>${item.category }</td></tr>
			
		</table>
	</section>
</body>
</html>