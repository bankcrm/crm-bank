<%@ page import="com.axon.util.JSPHelper" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan Document Processing</title>
</head>
<body>

<center><h1>Upload Loan File</h1></center> <br>
<div style="background-color:#<%=JSPHelper.pickColor()%>">
<form id="fileform">
	<input type="file" id="file" />
	<br><br><br>
	<input type="submit" id="button" />
</form>


</div>
</body>
</html>