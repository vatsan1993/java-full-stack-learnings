<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IMS - Home</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="/header"></jsp:include>
	<section class="container-fluid p-4">
		<div style="background-color: #f8f9fa ;" class="p-5 mb-4 rounded-3">
			<h2>
				Inventory Management System <small>a spring boot App</small>
			</h2>
		</div>
		
		<c:if test="${msg!=null}">
			<p class="alert alert-info p-2 m-2">
				<strong>${msg}</strong>
			</p>
		</c:if>
	</section>
	</body>
</html>