<%@ page import="com.axon.bank.form.CustomerForm" %>
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
	});
});
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
		Customer Name: <input type="text" id="name<%=id%>" value="<%=customer.getName() %>"><br>
		Age: <input type="text" id="age<%=id%>" value="<%=customer.getAge() %>"><br>
		Address: <input type="text" id="address<%=id%>" value="<%=customer.getAddress() %>"><br>   
		Mobile: <input type="text" id="mobile<%=id%>" value="<%=customer.getMobile() %>"><br> 
		Amount: <input type="text" id="amount<%=id%>" value="<%=customer.getAmount() %>"><br> 
		Social Security Number: <input type="text" id="ssn<%=id%>" value="<%=customer.getSsn() %>"><br>	
		Gender: <input type="text" id="gender<%=id%>" value="<%=customer.getGender() %>"><br>	
		City: <input type="text" id="city<%=id%>" value="<%=customer.getCity() %>"><br>
		Company: <input type="text" id="company<%=id%>" value="<%=customer.getCompany() %>"><br>	
		Salaried? <input type="text" id="salaried<%=id%>" value="<%=customer.isSalaried() %>"><br>
		Salary: <input type="text" id="salary<%=id%>" value="<%=customer.getSalary() %>"><br>	
		Salary Account: <input type="text" id="salaryacc<%=id%>" value="<%=customer.getSalaryAccount() %>"><br>	
		<% if (customer.getCompanyJoinDate() != null){ %>
		Date Company Was Joined: <input type="date" id="joindate<%=id%>" value="<%=customer.getCompanyJoinDate().toString().substring(0,10) %>"><br>	
		<% } else { %>
		Date Company Was Joined: <input type="date" id="joindate<%=id%>" ><br><% }%>
		Years of Working Experience: <input type="text" id="experience<%=id%>" value="<%=customer.getAmountOfExperience() %>"><br>	
		How long they have been located in the area: <input type="text" id="located<%=id%>" value="<%=customer.getTimeInArea() %>"><br>	
		<% if (customer.getCompanyJoinDate() != null){ %>
		When they last moved: <input type="date" id="lastmoved<%=id%>" value="<%=customer.getLastmove().toString().substring(0,10)  %>"><br>	
		<% } else { %>
		When they last moved: <input type="date" id="lastmoved<%=id%>" ><br>	<% }%>
		Home Details: <input type="text" id="homedetails<%=id%>" value="<%=customer.getHomedetails() %>"><br>	
		<% if (customer.getCompanyJoinDate() != null){ %>
		Date Of Birth: <input type="date" id="dob<%=id%>" value="<%=customer.getDob().toString().substring(0,10)  %>"><br>	
		<% } else { %>
		Date Of Birth: <input type="date" id="dob<%=id%>" ><br><%}%>
		Loan Details: <input type="text" id="loandetails<%=id%>" value="<%=customer.getLoanDetails() %>"><br>	
		Amount of EMIs: <input type="text" id="emis<%=id%>" value="<%=customer.getEmiCount() %>"><br>	
		Email: <input type="text" id="email<%=id%>" value="<%=customer.getEmail() %>"><br>	
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