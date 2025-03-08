<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header"/>
	<form:form method="POST" modelAttribute="item">
		<div>
			<form:label path="icode">Item Code</form:label>
			<form:input path="icode" type="number"/>
		</div>
		<div>
			<form:label path="name">Item name</form:label>
			<form:input path="name" type="text"/>
		</div>
		<div>
			<form:label path="price">Item Price</form:label>
			<form:input path="price" type="decimal"/>
		</div>
		<div>
			<form:label path="category">Item Category</form:label>
			<form:select path="category" items="${categories }"/>
		</div>
		<button>OK</button>
	</form:form>
</body>
</html>