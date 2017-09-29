<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload information Page</title>
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
</head>
<body>
	<span style="color: red; font-size: 16px;">${message}</span>
	<div class="bs-example" style="margin-left: 30px;">
		<form action="uploadApplicant.do" method="post" class="form-horizontal">
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
						<button type="submit" class="btn btn-danger"
							style="display: inline; margin-right: 40px;">Create
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