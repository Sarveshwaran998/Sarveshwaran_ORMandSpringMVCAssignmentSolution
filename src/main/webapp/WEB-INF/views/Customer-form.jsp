<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
	<div class="container">
	<h3>Save Customer</h3>
		<hr>
			<form action="/customermanagement/customers/save" method="POST">
			<input type="hidden" name="id" value="${Customer.id}" />
			<div class="form-inline">
				<input type="text" name="f_name" placeholder="First Name" value="${Customer.f_name}" class="form-control mb-4 col-4" />
			</div>
			<div class="form-inline">
				<input type="text" name="l_name" placeholder="Last Name" value="${Customer.l_name}" class="form-control mb-4 col-4" />
			</div>
			<div class="form-inline">
				<input type="text" name="email" placeholder="Email" value="${Customer.email}" class="form-control mb-4 col-4" />
			</div>
				<button type="submit" class="btn btn-info col-2">Save Customer</button>
			</form>
			<a href="/customermanagement/customers/list">Back to customers list</a>
	</div>
</body>
</html>