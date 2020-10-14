<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page - Yohooo!!</h2>
	<hr>
	<p>Welcome to the luv2code company home page!</p>

	<hr>
		<p>
			User: <security:authentication property="principal.username"/>
			<br><br>
			Role(s): <security:authentication property="principal.authorities"/>
		</p>	
	<hr>

	<hr>
		<!-- Add link to /leaders this is for managers -->
		
		<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders"> Leadership meeting</a>
		</p>
		</security:authorize>
	<hr>
	
	<hr>
		<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">It Systems meeting</a>
		</p>
		</security:authorize>
	<hr>

	<p>
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			
			<input type="submit" value="Logout"/>
			
			</form:form>
	</p>
</body>

</html>