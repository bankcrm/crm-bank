<%@ page import="com.axon.util.JSPHelper" %>
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
		        	window.location.href="http://localhost:8080/crm-bank/bank/viewApplicants";
		        },
		        error:function(data,status,er) {
		            alert("error: "+data+" status: "+status+" er:"+er);
		        }
		    });
		$("#Header"+id).hide();
		$("#Req"+id).hide();
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
			        	window.location.href="http://localhost:8080/crm-bank/bank/viewApplicants";
			        },
			        error:function(data,status,er) {
			            alert("error: "+data+" status: "+status+" er:"+er);
			        }
			    });
			$("#Header"+id).hide();
			$("#Req"+id).hide();
		});
}); 
</script>
</head>
<body>
<%@include file = "header.jsp" %>
<a href="${pageContext.request.contextPath}/mybank/logout">logout</a><br/>

<center><h1>Currently Pending Loan Applications</h1></center> <br>
<div class="container">
<div class="row">
<% ArrayList<ApplicantForm> applicants = (ArrayList<ApplicantForm>) request.getAttribute("applicants"); 
	int id;
	for(ApplicantForm applicant : applicants){
		id = applicant.getId();%>
		 <div class="col-xs-6 col-lg-4" style="margin-left:20px; background-color:#<%=JSPHelper.pickColor()%>">

		<center><div id="Header<%=id%>" class="panel-heading" style="">
		 <h3>Request #<%=id%><button class="btn btn-default"onClick="javascript:expand('<%=id%>');"> + </button>     <button class="btn btn-default" onClick="javascript:unexpand('<%=id%>');">  -  </button></h3></div></center>
		<div id="Req<%=id%>" style="display:none" class="panel-body"> 
		<label> Customer Name: </label> <%=applicant.getName() %><br>
		<label> Age: </label> <%=applicant.getAge() %><br>
		<label> Address: </label> <%=applicant.getAddress() %><br>   
		<label> Mobile: </label> <%=applicant.getMobile() %><br> 
		<label> Amount: </label> <%=applicant.getAmount() %><br> 
		<label> Social Security Number: </label> <%=applicant.getSsn() %><br>
		<label> Time Submitted: </label> <%=applicant.getDateCreated() %><br>
		<button id="accept<%=id%>" class="accept btn btn-success" value="<%=id%>">Accept</button> <button id="reject<%=id%>" class="decline btn btn-danger" value="<%=id%>">Decline</button>
		<br />
		</div>
		<br />
		</div><!--/.col-xs-6.col-lg-4-->
		
	
<% 	id++;
	}
%>
</div> <!-- End of row div -->
</div><!-- End of container div -->
<%@include file = "footer.jsp" %>
</body>
</html>