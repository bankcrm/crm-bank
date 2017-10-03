<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<h1>Hello, This is my login page</h1>

<div style="margin-left: 30px;">
		<form name ="loginForm" action="../j_spring_security_check" method="post">
			<div class="form-group">
				<label>Username........</label> 
				<input type="text" class="form-control" name="j_username" placeholder="Username"
					style="width: 300px;" />
			</div>

			<div class="form-group">
				<label>Password........</label> <input type="password"
					class="form-control" name="j_password" placeholder="password"
					style="width: 300px;" />
			</div>

			<button type="submit" class="btn btn-danger">Login</button>
		</form>
		<br />
		<br /> <span style="color: red; font-size: 16px;">${param.message}</span>
		<label style="color: blue"></label><img alt="" src="" height="100px;">
	</div>
	<span style="color: red;font-size: 16px;">${message}</span>
</body>
</html>