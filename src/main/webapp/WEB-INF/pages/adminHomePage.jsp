<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Admin Home Page</h1>
<br>
<center><label><a href="${pageContext.request.contextPath}/uploadApplicant.jsp">Customers: For inserting applicants</a></label></center><br/>
<center><label><a href="${pageContext.request.contextPath}/bank/viewApplicants">Manager: Approving Loan Applicants</a></label></center><br/>
<center><label><a href="${pageContext.request.contextPath}/leaderHomePage.jsp">Team Leaders: For assigning applicants to agents</a></label></center><br/>
<center><label><a href="${pageContext.request.contextPath}/bank/agentcustomers">Agents: For working with customer's data</a></label></center><br/>
<center><label><a href="${pageContext.request.contextPath}/mybank/logout">logout</a></label></center><br/>
</body>
</html>