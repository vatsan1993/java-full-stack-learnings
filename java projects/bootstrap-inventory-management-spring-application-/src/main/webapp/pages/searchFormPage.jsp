<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
	<h2>Search Items</h2>
	
	<form action="/searchResults" method="post">
		<label class="form-label" for="searchType">Search By:</label>
		<select class="form-control" name="searchType" value="Title">
	     <c:forEach var="type" items="${searchTypes}">
	     	<option name="${type}" value="${type }" >${type }</option>
	     </c:forEach>
	    </select>
	    <p>${searchType }</p>
	    <label class="form-label" for="searchValue">Search Value:</label>
	    <input  class="form-control" type="text" name="searchValue" />
	    <input class="btn btn-sm btn-primary form-control" type="submit" value="Submit" />
	</form>
	</section>
</body>
</html>