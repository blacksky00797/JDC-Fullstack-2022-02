<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
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
		<h3 style="text-align: center;" class="mt-3 mb-5">Registered Students</h3>
		
		<c:url var="registrationEdit" value="/registration-edit">
			<c:param name="openClassId" value="${openClass.id}"></c:param>
		</c:url>
		<a href="${registrationEdit}" class="btn btn-primary mb-3">Register</a>
		
		<c:choose>
			<c:when test="${empty registrations }">
				<div class="alert alert-warning">
					There is no student in this class yet.Please click the <em>Register</em> button to add a new student.
				</div>
			</c:when>
			
			<c:otherwise>
				<table class="table">
						<thead>
							<tr>
								<th>Register Id</th>
								<th>Course Name</th>
								<th>Teacher</th>
								<th>Start Date</th>
								<th>Duration</th>
								<th>Student Name</th>
								<th>Student Phone</th>
								<th>Student Email</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="reg" items="${registrations}">
								<tr>
									<td>${reg.id}</td>
									<td>${reg.openClass.course.name}</td>
									<td>${reg.openClass.teacher}</td>
									<td>${reg.openClass.start_date}</td>
									<td>${reg.openClass.course.duration} Months</td>
									<td>${reg.studentName}</td>
									<td>${reg.studentPhone}</td>
									<td>${reg.studentEmail}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>