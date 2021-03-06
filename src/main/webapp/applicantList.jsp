<%@ page import="com.axon.bank.form.ApplicantForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Applicants</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function expand(id){
		console.log("#Req"+id);
		$("#Req"+id).show();
	}
	function unexpand(id){
		console.log("#Req"+id);
		$("#Req"+id).hide();
	}
	function accept(id){
		console.log("#Req"+id);
		$("#Header"+id).hide();
	}
	function reject(id){
		$("#Header"+id).hide();	
	}
</script>
</head>
<body>

<% ArrayList<ApplicantForm> applicants = (ArrayList<ApplicantForm>) request.getAttribute("applicants"); 
	int id = 1;
	for(ApplicantForm applicant : applicants){%>
		id = applicant.getId();
		<div id="Header<%=id%>"><h2> Request #<%=id%>  <a href="javascript:expand('<%=id%>');">+</a><a href="javascript:unexpand('<%=id%>');">-</a></h2>
		<div id="Req<%=id%>" style="display:none"> 
		Customer Name: <%=applicant.getName() %><br>
		Age: <%=applicant.getAge() %><br>
		Address: <%=applicant.getAddress() %><br>   
		Mobile: <%=applicant.getMobile() %><br> 
		Amount: <%=applicant.getAmount() %><br> 
		Social Security Number: <%=applicant.getSsn() %><br>
		Time Submitted: <%=applicant.getDateCreated() %><br>
		<button onClick="accept('<%=id%>')">Accept</button> <button onClick="reject('<%=id%>')">Decline</button>
		<br />
		</div>
		<br />
		</div>
<% 	
	}
%>

</body>
</html>