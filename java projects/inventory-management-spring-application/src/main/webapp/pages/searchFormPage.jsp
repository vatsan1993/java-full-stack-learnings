<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header"></jsp:include>

	<h2>Search Items</h2>
	
	<form action="/searchResults" method="post">
		<label for="searchType">Search By:</label>
		<select name="searchType" value="Title">
	     <c:forEach var="type" items="${searchTypes}">
	     	<option name="${type}" value="${type }" >${type }</option>
	     </c:forEach>
	    </select>
	    <p>${searchType }</p>
	    <label for="searchValue">Search Value:</label>
	    <input type="text" name="searchValue" />
	    <input type="submit" value="Submit" />
	</form>
	
	
	
</body>
</html>