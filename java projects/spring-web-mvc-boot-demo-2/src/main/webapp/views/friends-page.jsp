
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header" />
	<section>
		<h3>Friends</h3>
		<!-- Used for debuggin
		<p>${frds }</p>
		<p>${not empty frds }</p>
		 -->
		<c:choose>
		    <c:when test="${not empty frds}">
		        <ol>
				<c:forEach items="${frds }" var="f">
					<!-- <li>${f } <a href="removeFriend?fnm=${f }">X</a> </li> -->
					<li>${f } 
					<form action="removeFriend">
						<input type="hidden" name="fnm" value="${f }"/>
						<button>Remove</button>	
					</form> 
					</li>
				</c:forEach>
				</ol>
		    </c:when>
		    <c:otherwise>
		        <p>❌ frds is EMPTY or NULL</p>
		    </c:otherwise>
		</c:choose>
		
		<form action="addFriend">
			<label>Friend Name</label>
			<input name="fnm" type="text">
			<button>ADD</button>
		</form>
		
	</section>
	

</body>
</html>