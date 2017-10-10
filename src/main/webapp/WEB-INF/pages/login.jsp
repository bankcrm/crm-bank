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
<%@include file="header.jsp" %>

<%-- 	<div style="margin-left: 30px;">
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
	</div> --%>
	 <div class="container" style="width:30%">

      <form class="form-signin" name ="loginForm" action="../j_spring_security_check" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label >Useranme</label>
        <input type="text"  class="form-control" placeholder="Username" name="j_username" required autofocus>
        <label >Password</label>
        <input type="password" class="form-control" placeholder="Password" name="j_password" required style="margin-bottom:30px">
 
        <button class="btn btn-lg btn-primary" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->
	<span style="color: red;font-size: 16px;">${message}</span>
	<br>
	<%@include file="footer.jsp"%>

</body>

</html>