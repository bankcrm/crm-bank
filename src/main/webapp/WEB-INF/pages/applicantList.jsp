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
$(document).ready(function(){
	$(".accept").click(function(){
		var id = $(document.activeElement).val();
		console.log(id);
		
		var customer = new Object();
		customer.id=id;
		customer.status="accepted";
		var purl = "http://localhost:8080/crm-bank/bank/statuschange";
		 $.ajax({
		        url: purl,
		        type: 'PUT',
		        dataType: 'json',
		        data: JSON.stringify(customer),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		 
		        success: function (data) {
		        	console.log("success");
		        },
		        error:function(data,status,er) {
		            alert("error: "+data+" status: "+status+" er:"+er);
		        }
		    });
		$("#Header"+id).hide();
	});
	$(".decline").click(function(){
			var id = $(document.activeElement).val();
			console.log(id);
			
			var customer = new Object();
			customer.id=id;
			customer.status="rejected";
			var purl = "http://localhost:8080/crm-bank/bank/statuschange";
			 $.ajax({
			        url: purl,
			        type: 'PUT',
			        dataType: 'json',
			        data: JSON.stringify(customer),
			        contentType: 'application/json',
			        mimeType: 'application/json',
			 
			        success: function (data) {
			        	console.log("success");
			        },
			        error:function(data,status,er) {
			            alert("error: "+data+" status: "+status+" er:"+er);
			        }
			    });
			$("#Header"+id).hide();
		});
}); 
</script>
</head>
<body>

<% ArrayList<ApplicantForm> applicants = (ArrayList<ApplicantForm>) request.getAttribute("applicants"); 
	int id;
	for(ApplicantForm applicant : applicants){
		id = applicant.getId();%>
		<div id="Header<%=id%>"><h2> Request #<%=id%>  <a href="javascript:expand('<%=id%>');">+</a><a href="javascript:unexpand('<%=id%>');">-</a></h2>
		<div id="Req<%=id%>" style="display:none"> 
		Customer Name: <%=applicant.getName() %><br>
		Age: <%=applicant.getAge() %><br>
		Address: <%=applicant.getAddress() %><br>   
		Mobile: <%=applicant.getMobile() %><br> 
		Amount: <%=applicant.getAmount() %><br> 
		Social Security Number: <%=applicant.getSsn() %><br>
		Time Submitted: <%=applicant.getStatus() %><br>
		<button id="accept<%=id%>" class="accept" value="<%=id%>">Accept</button> <button id="reject<%=id%>" class="decline" value="<%=id%>">Decline</button>
		<br />
		</div>
		<br />
		</div>
<% 	id++;
	}
%>

</body>
</html>