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
  .carousel-inner img {
      width: 100%; /* Set width to 100% */
      margin: auto;
      min-height:200px;
  }
</style>
</head>
<body>
<nav class="navbar navbar-inverse" style="margin-bottom:50px; border-radius:0;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active" ><a href="#">Home</a></li>
        <li><a href="#" >About</a></li>
   
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/mybank/auth"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
      </ul>
    </div>
  </div>
</nav>
	
	<div class="container text-center">   
  <div class="row">
<div id="myCarousel" class="carousel slide col-sm-7" data-ride="carousel" >
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="${pageContext.request.contextPath}/img/bank1.jpg" alt="Image">
        <div class="carousel-caption">
          <h3>Borrow </h3>
          <p>Money Money.</p>
        </div>      
      </div>

      <div class="item">
        <img src="${pageContext.request.contextPath}/img/bank.jpg" alt="Image">
        <div class="carousel-caption">
          <h3>More loan $</h3>
          <p>loan loan...</p>
        </div>      
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>



	<div class="col-sm-5" style="margin-left:30px; width:30%">
		<form id="loanform" action="bank/uploadApplicant" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-2" for="name">Name:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" placeholder="Enter your name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="age">Age:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="age" name="age" placeholder="Enter your age">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="address">Address:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="address" name="address" placeholder="Enter your address">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="mobile">Mobile:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="mobile" name="mobile" placeholder="Enter your mobile">
						</div>
					</div>
				
				<div class="form-group">
						<label class="control-label col-sm-2" for="amount">Amount:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="amount" name="amount" placeholder="Enter your loan amount">
						</div>
					</div>
				
				<div class="form-group">
						<label class="control-label col-sm-2" for="ssn">SSN:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="ssn" name="ssn" placeholder="Enter your ssn">
						</div>
				</div>

		<button type="button" class="btn btn-primary" style="display: inline; margin-right: 40px;" id="upload">Apply Loan</button>
		</form>
		<br>
	</div>
	  </div>
  </div>
  <footer class="footer" style="position: fixed; bottom: 0; left:0;">
      <div class="container">
        <p class="text-muted container-fluid text-center" >@Copyright CRM BANK</p>
      </div>
  </footer>
	
</body>

</html>