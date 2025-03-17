<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<div class="col-4">
	<h3>${isNew? "New Item": "EditItem"} </h3>
	
	<!-- Lets say we type the icode for the first time and the we get an error for one of
	the other properties. When we submit it for the second time we will get an error  if
	we the following commented form. This is because the form changes from addItem to
	 saveItem as the icode already exists. to avoid this, we create a new object in the  
	 in the controller called isNew.
	 -->
	<!--<form:form method="POST" modelAttribute="item" action="${item.icode == null ? '/addItem' : '/saveItem'}"></form:form>-->
	
	<form:form method="POST" modelAttribute="item" action="${isNew ? '/addItem' : '/saveItem'}">
		<div class="form-group">
			<form:label class="form-label" path="icode">Item code:</form:label>
			<form:input class="form-control" path="icode" type="number"  readonly="${!isNew}"/>
			<form:errors class="alert alert-danger" path="icode" />
		</div>
		
		<div class="form-group">
			<form:label class="form-label" path="title">Title:</form:label>
			<form:input class="form-control"  path="title" type="text" />
			<form:errors  class="alert alert-danger" path="title" />
		</div>
		
		<div class="form-group">
			<form:label class="form-label" path="unit">Unit:</form:label>
			<form:select class="form-control"  path="unit" items= "${units}" />
			<form:errors  class="alert alert-danger" path="unit" />
		</div>
		
		<div class="form-group">
			<form:label class="form-label" path="fragile">Fragile:</form:label>
			<form:checkbox class="form-control"  path="fragile" />
			<form:errors  class="alert alert-danger" path="fragile" />
		</div>
		
		<div class="form-group">
			<form:label class="form-label" path="packageDate">Package Date:</form:label>
			<form:input class="form-control"  path="packageDate" type="date"/>
			<form:errors  class="alert alert-danger" path="packageDate" />
		</div>
		<div class="form-group">
			<form:label class="form-label" path="costPrice">Cost Price:</form:label>
			<form:input class="form-control"  path="costPrice" type="number" min="0.01" step="0.01" />
			<form:errors  class="alert alert-danger" path="costPrice" />
		</div>
		<div class="form-group">
			<form:label class="form-label" path="sellingPrice">Selling Price:</form:label>
			<form:input class="form-control"  path="sellingPrice" type="number" min="0.01" step="0.01"  />
			<form:errors  class="alert alert-danger" path="sellingPrice" />
		</div>
		<div class="form-group">
			<button class="btn btn-sm btn-primary">Save</button>
		</div>
	</form:form>
	</div>
	
	</section>
</body>
</html>