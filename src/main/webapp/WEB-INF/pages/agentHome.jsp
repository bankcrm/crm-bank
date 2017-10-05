<%@ page import="com.axon.bank.form.CustomerForm" %>
<%@ page import="com.axon.util.JSPHelper" %>
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
<script type="text/javascript">
function expand(id){
	console.log("#Cst"+id);
	$("#Cst"+id).show();
}
function unexpand(id){
	console.log("#Cst"+id);
	$("#Cst"+id).hide();
}
$(document).ready(function(){
	$(".save").click(function(){
		var id = $(document.activeElement).val();
		console.log(id);
		console.log($("#name"+id).val());
		if(validate(id)){
			var customer = new Object();
			customer.id=id;
			customer.name= $("#name"+id).val();
			customer.age= $("#age"+id).val();
			customer.address= $("#address"+id).val();
			customer.mobile= $("#mobile"+id).val();
			customer.amount= $("#amount"+id).val();
			customer.ssn= $("#ssn"+id).val();
			customer.gender= $("#gender"+id).val();
			customer.city= $("#city"+id).val();
			customer.company= $("#company"+id).val();
			customer.salaried= $("#salaried"+id).val();
			customer.salary= $("#salary"+id).val();
			customer.salaryAccount= $("#salaryacc"+id).val();
			customer.companyJoinDate= $("#joindate"+id).val();
			customer.amountOfExperience= $("#experience"+id).val();
			customer.timeInArea= $("#located"+id).val();
			customer.lastmove= $("#lastmoved"+id).val();
			customer.homedetails= $("#homedetails"+id).val();
			customer.dob= $("#dob"+id).val();
			customer.loanDetails = $("#loandetails"+id).val();
			customer.emiCount= $("#emis"+id).val();
			customer.email= $("#email"+id).val();
			if(customer.companyJoinDate == 0){
				customer.companyJoinDate = null;
			}
			if(customer.lastmove == 0){
				customer.lastmove = null;
			}
			if(customer.dob == 0){
				customer.dob = null;
			}
			if(customer.emiCount == ""){
				customer.emiCount = -1;
			}
			if(customer.salary == ""){
				customer.salary = -1;
			}
			if(customer.amountOfExperience == ""){
				customer.amountOfExperience = -1;
			}
			if(customer.timeInArea == ""){
				customer.timeInArea = -1;
			}
			console.log(JSON.stringify(customer));
			var purl = "http://localhost:8080/crm-bank/bank/updatecustomer";
			 $.ajax({
			        url: purl,
			        type: 'POST',
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
			$("#saved"+id).show();
		}
	});
});
function validate(id){
	console.log("validation start");
	if($("#name"+id).val() == ""){
		alert("A name must be given.");
	} else if ($("#age"+id).val() < 1 || isNaN($("#age"+id).val())){
		alert("Age can not be a negative number!");
	} else if (checkMobile($("#mobile"+id).val())){
		alert("Insert a valid mobile number. A valid phone number consists of 10 numbers and dashes.");
	} else if (checkSSN($("#ssn"+id).val())){
		alert("Insert a valid Social Security Number. A valid SSN number consists of 9 numbers and dashes.");
	} else {
		return true;
	}
	return false;	
}
		
	
	function checkSSN(ssn){
		if(ssn.length == 0){
			return false;
		}
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
		if(mobile.length == 0){
			return false;
		}
		var numbers = 0;
		for(var i = 0; i < mobile.length; i++){
			if(isNaN(parseInt(mobile.charAt(i)))){
				if(!(mobile.charAt(i) == '-')){
					return true;
				}
			} else {
				numbers = numbers + 1;
			}
		}
		if(numbers == 10){
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body>

<% ArrayList<CustomerForm> customers = (ArrayList<CustomerForm>) request.getAttribute("customers"); 
	int id;
	for(CustomerForm customer : customers){
		id = customer.getId();%>
		<div id="Header<%=id%>"><h2> Customer #<%=id%>  <a href="javascript:expand('<%=id%>');">+</a><a href="javascript:unexpand('<%=id%>');">-</a></h2>
		<div id="Cst<%=id%>" style="display:none"> 
		<form name="Cstform<%=id%>">
		Customer Name: <input type="text" id="name<%=id%>" <%=JSPHelper.pickString(customer.getName()) %>><br>
		Age: <input type="text" id="age<%=id%>" <%=JSPHelper.pickString(customer.getAge()) %>><br>
		Address: <input type="text" id="address<%=id%>" <%=JSPHelper.pickString(customer.getAddress()) %>><br>   
		Mobile: <input type="text" id="mobile<%=id%>" <%=JSPHelper.pickString(customer.getMobile()) %>><br> 
		Amount: <input type="text" id="amount<%=id%>" <%=JSPHelper.pickString(customer.getAmount()) %>><br> 
		Social Security Number: <input type="text" id="ssn<%=id%>" <%=JSPHelper.pickString(customer.getSsn()) %>><br>	
		Gender: <input type="text" id="gender<%=id%>" <%=JSPHelper.pickString(customer.getGender()) %>><br>	
		City: <input type="text" id="city<%=id%>" <%=JSPHelper.pickString(customer.getCity()) %>><br>
		Company: <input type="text" id="company<%=id%>" <%=JSPHelper.pickString(customer.getCompany()) %>><br>	
		Salaried? <input type="text" id="salaried<%=id%>" value="<%=customer.isSalaried() %>"><br>
		Salary: <input type="text" id="salary<%=id%>" <%=JSPHelper.pickString(customer.getSalary()) %>><br>	
		Salary Account: <input type="text" id="salaryacc<%=id%>" <%=JSPHelper.pickString(customer.getSalaryAccount()) %>><br>	
		Date Company Was Joined: <input type="date" id="joindate<%=id%>" <%=JSPHelper.pickString(customer.getCompanyJoinDate())%>><br>	
		Years of Working Experience: <input type="text" id="experience<%=id%>" <%=JSPHelper.pickString(customer.getAmountOfExperience()) %>><br>	
		How long they have been located in the area: <input type="text" id="located<%=id%>" <%=JSPHelper.pickString(customer.getTimeInArea()) %>><br>	
		When they last moved: <input type="date" id="lastmoved<%=id%>" <%=JSPHelper.pickString(customer.getLastmove()) %>><br>	
		Home Details: <input type="text" id="homedetails<%=id%>" <%=JSPHelper.pickString(customer.getHomedetails()) %>><br>	
		Date Of Birth: <input type="date" id="dob<%=id%>"  <%=JSPHelper.pickString(customer.getDob()) %>><br>	
		Loan Details: <input type="text" id="loandetails<%=id%>" <%=JSPHelper.pickString(customer.getLoanDetails()) %>><br>	
		Amount of EMIs: <input type="text" id="emis<%=id%>" <%=JSPHelper.pickString(customer.getEmiCount()) %>><br>	
		Email: <input type="text" id="email<%=id%>" <%=JSPHelper.pickString(customer.getEmail()) %>><br>	
		<button type="button" id="save<%=id%>" class="save" value="<%=id%>">Save</button>
		</form>	
		<br />
		</div>
		<br />
		</div>
		<div id="saved<%=id%>" style="display:none"><h4>Data saved!</h4></div>
<% 	id++;
	}
%>

</body>
</html>