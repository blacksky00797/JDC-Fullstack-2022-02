<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Edit</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<style type="text/css">
		td {
			vertical-align: middle;
		}
	</style>
</head>
<body>
	<div class="container mt-2">
		<h1 style="text-align: center;">Java Developer Class</h1>
		<h3 style="text-align: center;" class="mt-3 mb-5">Registration Process For "${openClass.course.name }" course</h3>
		
		<c:url var="registration" value="/registration">
			<c:param name="openClassId" value="${openClass.id }"></c:param>
		</c:url>
		<form action="${registration}" method="post">
			<div class="form-group">
				<label>Student Name</label>
				<input type="text" class="form-control mt-1 mb-3" name="name" required="required">
				<label>Student Phone</label>
				<input type="text" class="form-control mt-1 mb-3" name="phone" required="required">
				<label>Student Email</label>
				<input type="email" class="form-control mt-1 mb-3" name="email" required="required">
				<input type="submit" value="Register" class="btn btn-primary btn-md">
			</div>
		</form>
	</div>
</body>
</html>