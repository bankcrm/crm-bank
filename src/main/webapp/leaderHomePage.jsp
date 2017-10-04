<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
var contents ="";
var myContents ="";
$(document).ready(function(){
	var purl ="http://localhost:8080/crm-bank/bank/leaderHome";
	$.ajax({
			url : purl,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(jsonData) {
				contents="";
				console.log(jsonData);
				for(var i=0; i<jsonData.length; i++){
					addAgentResult(jsonData[i]);
				}
				$("#agentTable").html(contents);
				showApplicant();
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		}

		);//end of ajax call
		
		
	});
function showApplicant(){
	var murl ="http://localhost:8080/crm-bank/bank/acceptedRequest";
	$.ajax({
		url : murl,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(newJsonData) {
			myContents="";
			console.log(newJsonData);
			for(var i=0; i<newJsonData.length; i++){
				addApplicantResult(newJsonData[i]);
			}
			$("#applicantTable").html(myContents);
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	}

	);//end of ajax call
}
function addAgentResult(row){
	contents = contents + '<tr>';
	contents = contents + '<td>'+row.username+'</td>';
	contents = contents + '<td>'+row.role+'</td>';
	contents = contents + '<td>'+row.status+'</td>';
	contents = contents + '</tr>';
}
function addApplicantResult(row){
	
	myContents = myContents + '<tr>';
	myContents = myContents + '<td>'+row.name+'</td>';
	myContents = myContents + '<td>'+row.amount+'</td>';
	myContents = myContents + '<td>'+row.name+'</td>';
	myContents = myContents + '</tr>';
}
</script>
<title>Team Leader Home Page</title>
</head>
<body>
<div class="container" >
  <h2>All the connected Agents are here</h2>
  
<table class="table" style="width:500px">
	<thead>
      <tr>
      	<th>Username</th>
      	<th>Role</th>
      	<th>Status</th>
     </tr>
  	</thead>
  	<tbody id="agentTable">
  	  <tr>
  	  	<td>gggg</td>
  	  	<td>88888</td>
  	  	<td>8888</td>
  	  </tr>
  	</tbody>
</table>
</div>

<div class="container" >
  <h2>All the accepted request are here</h2>
  
<table class="table" style="width:500px">
	<thead>
      <tr>
      	<th>Name</th>
      	<th>Amount</th>
      	<th>Status</th>
     </tr>
  	</thead>
  	<tbody id="applicantTable">
  	  <tr>
  	  	<td>gggg</td>
  	  	<td>88888</td>
  	  	<td>8888</td>
  	  </tr>
  	</tbody>
</table>
</div>
</body>
</html>