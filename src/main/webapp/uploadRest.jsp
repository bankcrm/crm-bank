<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload Rest Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	function sendAjax() {
		 
	    // get inputs
		var article = new Object();
	    article.title = $('#title').val();
	    article.url = $('#url').val();
	    article.categories = $('#categories').val().split(";");
	    article.tags = $('#tags').val().split(";");
	 
	    $.ajax({
	        url: purl,
	        type: 'POST',
	        dataType: 'json',
	        data: JSON.stringify(customer),
	        contentType: 'application/json',
	        mimeType: 'application/json',
	 
	        success: function (data) {
	        	alert(data);
	        },
	        error:function(data,status,er) {
	            alert("error: "+data+" status: "+status+" er:"+er);
	        }
	    });
	}

	
	$("#upload").click(function(){
		var name = $("input[name='name']").val();
		var age = $("input[name='age']").val();
		var address = $("input[name='address']").val();
		var mobile = $("input[name='mobile']").val();
		var amount = $("input[name='amount']").val();
		var ssn = $("input[name='ssn']").val();
		var customer = new Object();
		customer.name=name;
		customer.age=age;
		customer.address=address;
		customer.mobile=mobile;
		customer.amount=amount;
		customer.ssn=ssn;
		alert("$#%#$%$" + name + "######"+ age + "######"+address+ "######"+ mobile + "######" +amount+ "######"+ssn );
		var purl = "http://localhost:8080/crm-bank/bank/application";
		 $.ajax({
		        url: purl,
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify(customer),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		 
		        success: function (data) {
		        	alert(data);
		        },
		        error:function(data,status,er) {
		            alert("error: "+data+" status: "+status+" er:"+er);
		        }
		    });
		
		
		/* $.ajax({url: purl, type:'POST', data:customer, dataType:"json",contentType:"application/json;charset=utf-8",success: function(jsonData){
			alert("Ankit");
			console.log(jsonData);
			if(jsonData.status == "success"){
				alert("##########3  good");
			}else{
				alert("Sorry! data could not be upload");
			}
		}
			
		});//end of the ajax call */
	});//end of the click event
	
}); //end of ready function
	
</script>
</head>
<body>
	<span style="color: red; font-size: 16px;">${message}</span>
	<div class="bs-example" style="margin-left: 30px;">
		<form  class="form-horizontal">
		<!-- action="bank/uploadApplicant" method="post" -->
			<table>
				<tr>
					<td>
						<div class="form-group">
							<label>Name : </label> <input type="text" class="form-control"
								name="name" placeholder="name"
								style="width: 300px; display: inline; margin-left: 40px;" /> <br />
						</div>
						<div class="form-group">
							<label>Age :</label> <input type="text" class="form-control"
								name="age" placeholder="age"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>

						<div class="form-group">
							<label>Address :</label> <input type="text" class="form-control"
								name="address" placeholder="address"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>
						<div class="form-group">
							<label>Mobile :</label> <input type="text" class="form-control"
								name="mobile" placeholder="mobile"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>

						<div class="form-group">
							<label>Amount :</label> <input type="text" class="form-control"
								name="amount" placeholder="amount"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>

						<div class="form-group">
							<label>SSN :</label> <input type="text" class="form-control"
								name="ssn" placeholder="ssn"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>
						<button type="button" class="btn btn-danger"
							style="display: inline; margin-right: 40px;" id = "upload"  >Upload
							Applicant</button>

					</td>

				</tr>

			</table>
		</form>
		<br />
		<br />
	</div>
</body>

</html>