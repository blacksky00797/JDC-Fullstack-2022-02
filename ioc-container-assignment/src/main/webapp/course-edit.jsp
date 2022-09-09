<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container mt-2">
		<h1 style="text-align: center;">Java Developer Class</h1>
		<h3 style="text-align: center;" class="mt-3 mb-5">Add a course</h3>
	
		
		<c:url var="course" value="/course"></c:url>
		<form action="${course}" method="post">
			<div class="form-group">
				<label>Name</label>
				<input type="text" class="form-control mt-1 mb-3" name="name" required="required">
				<label>Fees</label>
				<input type="number" class="form-control mt-1 mb-3" name="fees" required="required">
				<label>Duration</label>
				<input type="number" class="form-control mt-1 mb-3" name="duration" required="required">
				<label>Description</label>
				<textarea rows="4" class="form-control mt-1 mb-3" name="description" required="required"></textarea>
				<input type="submit" value="Add Course" class="btn btn-primary btn-md">
			</div>
		</form>
	</div>
</body>
</html>