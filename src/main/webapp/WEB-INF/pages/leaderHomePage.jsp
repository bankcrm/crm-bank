<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
var contextPath="${pageContext.request.contextPath}";
var agentContents ="";
var applicantContents ="";
var progressContents="";
var agentData ="";
var applicantData="";
var progressData="";
//var selectedName = "";
//function that render the online agents
$(document).ready(function(){
	
	var purl ="http://localhost:8080/crm-bank/bank/leaderHome";
	$.ajax({
			url : purl,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(jsonData) {
				agentData = jsonData;
				myData = jsonData;
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		}

		);//end of ajax call
	$("#img1").on('click', function(){
		$("#progressTable").hide();
		$("#applicantTable").hide();
		agentContents="";
		//console.log(jsonData);
		agentContents = agentContents + "<thead>";
		agentContents = agentContents + "<tr>";
		agentContents = agentContents + "<th>Username</th>";
		agentContents = agentContents + "<th>Role</th>";
		agentContents = agentContents + "<th>Status</th>";
		agentContents = agentContents + "</tr>";
		agentContents = agentContents + "</thead>";
		agentContents = agentContents + "<tbody >";
		for(var i=0; i<agentData.length; i++){
			addAgentResult(agentData[i]);
		}
		agentContents = agentContents + "</tbody >";
		$("#agentTable").html(agentContents);
	});//click event
});

//Helper function for add agent result
function addAgentResult(row){
	agentContents = agentContents + '<tr>';
	agentContents = agentContents + '<td>'+row.username+'</td>';
	agentContents = agentContents + '<td>'+row.role+'</td>';
	agentContents = agentContents + '<td>'+row.status+'</td>';
	agentContents = agentContents + '</tr>';
}
//Function for accepted Request applicants
$(document).ready(function(){
	
	var murl ="http://localhost:8080/crm-bank/bank/acceptedRequest";
	$.ajax({
		url : murl,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(newJsonData) {
			applicantData = newJsonData;
			
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	}

	);//end of ajax call
	$("#img2").on('click', function(){
		$("#agentTable").hide();
		$("#progressTable").hide();
		applicantContents="";
		//console.log(newJsonData);
		applicantContents = applicantContents + '<thead>';
		applicantContents = applicantContents + '<tr>';
		applicantContents = applicantContents + '<th>Customer Id</th>';
		applicantContents = applicantContents + '<th>Name</th>';
		applicantContents = applicantContents + '<th>Amount</th>';
		applicantContents = applicantContents + '<th>Agent Name</th>';
		applicantContents = applicantContents + '<th>Button</th>';
		applicantContents = applicantContents + '</tr>';
		applicantContents = applicantContents + '</thead>';
		applicantContents = applicantContents + '<tbody>';
		for(var i=0; i<applicantData.length; i++){
			addApplicantResult(applicantData[i]);
		}
		applicantContents = applicantContents + '</tbody>';
		$("#applicantTable").html(applicantContents);
		assign();
	});// end of click event
});
//Helper function to add applicant result
function addApplicantResult(row){

	applicantContents = applicantContents + '<tr>';
	applicantContents = applicantContents + '<td>'+row.id+'</td>';
	applicantContents = applicantContents + '<td>'+row.name+'</td>';
	applicantContents = applicantContents + '<td>'+row.amount+'</td>';
	applicantContents = applicantContents + '<td> <select  class="myselect">';
	for(var i=0; i < myData.length; i++){
		applicantContents = applicantContents + '<option>' + myData[i].username + '</option>';
	}
	applicantContents = applicantContents  + '</select></td>';
	applicantContents = applicantContents + '<td><input type="button" class="btn btn-danger assignButton" style="display: inline; margin-right: 40px;"value="assign" ></td>';
	applicantContents = applicantContents + '</tr>';
}

function assign(){
	$('.assignButton').on('click', function(){
		var selectedName =$(this).parent().parent().children("td").eq(3).find("select option:selected").val();
		console.log(selectedName);
		var id = $(this).parent().parent().children("td").eq(0).html();
		console.log(id);
		var purl = "http://localhost:8080/crm-bank/bank/assignedRequest/"+id+"/"+selectedName;
		$.ajax({
			url : purl,
			type : 'PUT',
			dataType : 'json',
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(assignJsonData) {			
				console.log(assignJsonData.status);
				window.location.href="http://localhost:8080/crm-bank/mybank/redirectHome";
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		}
		);//end of ajax call 
	});
	
	
}

//Function for fetch the progress status;
$(document).ready(function(){
	
	var purl ="http://localhost:8080/crm-bank/bank/progressStatus";
	$.ajax({
		url : purl,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(pData) {
			progressData = pData;
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	}

	);//end of ajax call
	$("#img3").on('click', function(){
		$("#agentTable").hide();
		$("#applicantTable").hide();
		progressContents="";
		//console.log(progressData);
		//console.log(progressData[0]);
		
		progressContents = progressContents + '<thead>';
		progressContents = progressContents + '<tr>';
		progressContents = progressContents + '	<th>Agent Name</th>';
		progressContents = progressContents + '	<th>Customer name</th>';
		progressContents = progressContents + '	<th>Status</th>';
		progressContents = progressContents + '</tr>';
		progressContents = progressContents + '</thead>';
		progressContents = progressContents + '<tbody >';
		for(var i=0; i<progressData.length; i++){
				addprogressResult(progressData[i]);
			
		}
		progressContents = progressContents + '</tbody >';
		$("#progressTable").html(progressContents);
	});//end of click event
});

//Helper function to add progress result
function addprogressResult(row){

	progressContents = progressContents + '<tr>';
	progressContents = progressContents + '<td>' + row[0] +'</td>' ;
	progressContents = progressContents + '<td>' + row[1] +'</td>';
	progressContents = progressContents + '<td class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:'+row[2]+'%">'+row[2]+'%</td>';
	progressContents = progressContents + '</tr>';
	
}

</script>
<style>
  .jumbotron {   
      margin-bottom: 50px;
    }
</style>
<title>Team Leader Home Page</title>
</head>
<body>

<%@include file="header.jsp" %>
<div class="jumbotron">
  <div class="container text-center">
    <h1>Online Banking </h1>      
    <p>Fast, Easy & Low interest</p>
  </div>
</div>

<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-success">
        <div class="panel-heading">Connected Agents</div>
        <div class="panel-body"><img id = "img1" alt="" src="https://placehold.it/150x80?text=MyImage" class="img-responsive" style="width:100%"></div>
        <div class="panel-footer">All the connected Agents are here</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-danger">
        <div class="panel-heading">Accepted Request</div>
        <div class="panel-body"><img id="img2" src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">All the accepted request are here</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">Progress</div>
        <div class="panel-body"><img id="img3" src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">All the progress are here</div>
      </div>
    </div>
  </div>
</div><br>

<div class="container" >
  <table class="table" style="width:500px" id="agentTable">

</table>
</div>

<div class="container" >

  
<table class="table" style="width:500px" id="applicantTable">

</table>
</div>
<div class="container" >
  
<table class="table" style="width:500px" id="progressTable">

  	</tbody>
</table>
</div>
<%@include file="footer.jsp" %>
</body>
</html>