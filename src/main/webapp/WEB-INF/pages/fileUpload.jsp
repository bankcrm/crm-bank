<%@ page import="com.axon.util.JSPHelper" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan Document Processing</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<script type="text/javascript">
$(document).ready(function(){

	
	$("#button").click(function(){
		var form = new Object();
		form.name=$("#name").val();
		form.id=$("#id").val();
		form.file=$("#file").val();
		formData = new FormData();
		alert($("#file").val());
		formData.append("file", $("#file").val());
		formData.append('properties', new Blob([JSON.stringify({
		                "name": "root",
		                "password": "root"                    
		            })], {
		                type: "application/json"
		            }));
		var purl = "http://localhost:8080/crm-bank/bank/docupload/"+$("#id").val()+"/"+$("#name").val();
		console.log(purl);
		alert(formData.file + " " + formData.properties);
		$.ajax({
		        url: purl,
		        type: 'POST',
		        headers: {
		            "Content-Type": undefined
		    	},
		   		data: formData,
		   		processData: false, 
		   		contentType: false,
		   		enctype: "multipart/form-data",
		 
		        success: function (data) {
		        	alert(data);
		        },
		        error:function(data,status,er) {
		            alert("error: "+data+" status: "+status+" er:"+er);
		        }
		    });
		alert("ajax");
	});//end of the click event
	
}); //end of ready function
</script>
</head>
<body>

<center><h1>Upload Loan File</h1></center> <br>
<div style="background-color:#<%=JSPHelper.pickColor()%>">
<form:form modelAttribute="uploadItem" name="frm" method="post" action="${name}/upload" enctype="multipart/form-data">
	<center>
	<table>
		<tr>
			<td><form:label for="fileData" path="fileData">File</form:label><br />
			</td>
			<td><form:input path="fileData" id="image" type="file" /></td>
		</tr>
		<tr>
			<td><br />
			</td>
			<td><input type="submit" value="Upload" /></td>
		</tr>
	</table>
	</center>
	</fieldset>
</form:form>


</div>
</body>
</html>