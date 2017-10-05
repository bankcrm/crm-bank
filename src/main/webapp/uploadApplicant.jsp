<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Information Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
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
		if(validate()){
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
		}
	});//end of the click event
	
}); //end of ready function
	function validate(){
		console.log("validation start");
		if($("#name").val() == ""){
			alert("A name must be given.");
		} else if ($("#age").val() < 1 || isNaN($("#age").val())){
			alert("Age can not be a negative number!");
		} else if ($("#address").val() == ""){
			alert("Address must be inserted.");
		} else if (checkMobile($("#mobile").val())){
			alert("Insert a valid mobile number. A valid phone number consists of 10 numbers and dashes.");
		} else if ($("#amount").val() < 1 || isNaN($("#amount").val())){
			alert("A loan is not a loan without asking for an amount of money.");
		} else if (checkSSN($("#ssn").val())){
			alert("Insert a valid Social Security Number. A valid SSN number consists of 9 numbers and dashes.");
		} else {
			alert("passed!");
			return true;
		}
		return false;	
	}
			
		
		function checkSSN(ssn){
			var numbers = 0;
			for(var i = 0; i < ssn.length; i++){
				if(isNaN(parseInt(ssn.charAt(i)))){
					if(!(ssn.charAt(i) == '-')){
						return true;
					}
				} else {
					numbers = numbers + 1;
				}
			}
			if(numbers == 9){
				return false;
			} else {
				return true;
			}
		}
		
		function checkMobile(mobile){
			var numbers = 0;
			for(var i = 0; i < mobile.length; i++){
				console.log(mobile.charAt(i));
				if(isNaN(parseInt(mobile.charAt(i)))){
					if(!(mobile.charAt(i) == '-')){
						return true;
					}
				} else {
					numbers = numbers + 1;
				}
			}
			console.log(numbers)
			if(numbers == 10){
				return false;
			} else {
				return true;
			}
		}
</script>

<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
</head>
<body>
	<div class="bs-example" style="margin-left: 30px;">
		<form id="loanform" action="bank/uploadApplicant" method="post" class="form-horizontal">
			<table>
				<tr>
					<td>
						<div class="form-group">
							<label>Name : </label> <input type="text" class="form-control"
								id="name" name="name" placeholder="name"
								style="width: 300px; display: inline; margin-left: 40px;" /> <br />
						</div>
						<div class="form-group">
							<label>Age :</label> <input type="text" class="form-control"
								id="age" name="age" placeholder="age"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>

						<div class="form-group">
							<label>Address :</label> <input type="text" class="form-control"
								id="address" name="address" placeholder="address"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>
						<div class="form-group">
							<label>Mobile :</label> <input type="text" class="form-control"
								id="mobile" name="mobile" placeholder="mobile"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>

						<div class="form-group">
							<label>Amount :</label> <input type="text" class="form-control"
								id="amount" name="amount" placeholder="amount"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>

						<div class="form-group">
							<label>SSN :</label> <input type="text" class="form-control"
								id="ssn" name="ssn" placeholder="ssn"
								style="margin-left: 40px; width: 400px; display: inline; margin-right: 40px;" />
							<br />
						</div>
						<button type="button" class="btn btn-danger"
							style="display: inline; margin-right: 40px;" id="upload">Create
							Profile</button>

					</td>

				</tr>

			</table>
		</form>
		<br />
		<br />
	</div>
</body>

</html>