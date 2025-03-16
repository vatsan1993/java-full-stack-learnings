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
	<jsp:include page="/header"></jsp:include>
	<h3>${isNew? "New Item": "EditItem"} </h3>
	
	<!-- Lets say we type the icode for the first time and the we get an error for one of
	the other properties. When we submit it for the second time we will get an error  if
	we the following commented form. This is because the form changes from addItem to
	 saveItem as the icode already exists. to avoid this, we create a new object in the  
	 in the controller called isNew.
	 -->
	<!--<form:form method="POST" modelAttribute="item" action="${item.icode == null ? '/addItem' : '/saveItem'}"></form:form>-->
	
	<form:form method="POST" modelAttribute="item" action="${isNew ? '/addItem' : '/saveItem'}">
		<div>
			<form:label path="icode">Item code:</form:label>
			<form:input path="icode" type="number"  readonly="${!isNew}"/>
			<form:errors path="icode" />
		</div>
		
		<div>
			<form:label path="title">Title:</form:label>
			<form:input path="title" type="text" />
			<form:errors path="title" />
		</div>
		
		<div>
			<form:label path="unit">Unit:</form:label>
			<form:select path="unit" items= "${units}" />
			<form:errors path="unit" />
		</div>
		
		<div>
			<form:label path="fragile">Fragile:</form:label>
			<form:checkbox path="fragile" />
			<form:errors path="fragile" />
		</div>
		
		<div>
			<form:label path="packageDate">Package Date:</form:label>
			<form:input path="packageDate" type="date"/>
			<form:errors path="packageDate" />
		</div>
		<div>
			<form:label path="costPrice">Cost Price:</form:label>
			<form:input path="costPrice" type="number" min="0.01" step="0.01" />
			<form:errors path="costPrice" />
		</div>
		<div>
			<form:label path="sellingPrice">Selling Price:</form:label>
			<form:input path="sellingPrice" type="number" min="0.01" step="0.01"  />
			<form:errors path="sellingPrice" />
		</div>
		<div>
			<button>Save</button>
		</div>
	</form:form>
</body>
</html>