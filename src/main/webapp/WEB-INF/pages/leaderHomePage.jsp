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
var myData ="";
var progressContents="";
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
				agentContents="";
				//console.log(jsonData);
				myData = jsonData;
				for(var i=0; i<jsonData.length; i++){
					addAgentResult(jsonData[i]);
				}
				$("#agentTable").append(agentContents);
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		}

		);//end of ajax call		
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
			applicantContents="";
			//console.log(newJsonData);
			for(var i=0; i<newJsonData.length; i++){
				addApplicantResult(newJsonData[i]);
			}
			$("#applicantTable").append(applicantContents);
			assign();
			
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	}

	);//end of ajax call
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
		success : function(progressData) {
			progressContents="";
			//console.log(progressData);
			//console.log(progressData[0]);
			
			for(var i=0; i<progressData.length; i++){
					addprogressResult(progressData[i]);
				
			}
			$("#progressTable").append(progressContents);
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	}

	);//end of ajax call
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
<title>Team Leader Home Page</title>
</head>
<body>
<div class="container" >
  <h2>All the connected Agents are here</h2>
  
<table class="table" style="width:500px" id="agentTable">
	<thead>
      <tr>
      	<th>Username</th>
      	<th>Role</th>
      	<th>Status</th>
     </tr>
  	</thead>
  	<tbody >
  	  
  	</tbody>
</table>
</div>

<div class="container" >
  <h2>All the accepted request are here</h2>
  
<table class="table" style="width:500px" id="applicantTable">
	<thead>
      <tr>
      	<th>Customer Id</th>
      	<th>Name</th>
      	<th>Amount</th>
      	<th>Agent Name</th>
      	<th>Button</th>
     </tr>
  	</thead>
  	<tbody>
  	 
  	</tbody>
</table>
</div>
<div class="container" >
  <h2>All the progress are here</h2>
  
<table class="table" style="width:500px" id="progressTable">
	<thead>
      <tr>
      	<th>Agent Name</th>
      	<th>Customer name</th>
      	<th>Status</th>
     </tr>
  	</thead>
  	<tbody >

  	</tbody>
</table>
</div>
</body>
</html>