<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Login Page</h3>

	<p style="color: red;">${errorMessage}</p>

	<form method="POST" action="${pageContext.request.contextPath}/login">

		<table border="0">
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" value="${user.username}" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"
					value="${user.password}" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /> <a
					href="${pageContext.request.contextPath}/">Cancel</a></td>
			</tr>
		</table>
	</form>


	<br>




</body>
</html>