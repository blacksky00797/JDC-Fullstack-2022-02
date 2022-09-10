<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java Developer Class</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>
	
	<style type="text/css">
		td {
			vertical-align: middle;
		}
	</style>
</head>
<body>
	<div class="container mt-2">
		<h1 style="text-align: center;">Java Developer Class</h1>
	</div>

	<div class="container mt-3">
		<h3 style="text-align: center;">Available Courses</h3>
	</div>


	<div class="container mt-5">
		
		<c:url var="courseEdit" value="/course-edit"></c:url>
		<a href="${courseEdit}" class="btn btn-primary mb-3">Add New Course</a>
		<c:choose>
			<c:when test="${empty courses }">
				<div class="alert alert-warning">There is no courses
					yet.Please add a new course to display on table.</div>
			</c:when>

			<c:otherwise>
				<table class="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Fees</th>
							<th>Duration</th>
							<th>Description</th>
							<th align="right"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${courses}">
							<tr>
								<td>${c.id}</td>
								<td>${c.name}</td>
								<td>${c.fees}</td>
								<td>${c.duration} Months</td>
								<td>${c.description}</td>
								<td align="center">
									<c:url var="classes" value="/classes">
										<c:param name="courseId" value="${c.id }"/>
									</c:url>
									<a href="${classes}" class="btn btn-danger btn-sm">Open Classes</a>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>

	</div>
</body>
</html>