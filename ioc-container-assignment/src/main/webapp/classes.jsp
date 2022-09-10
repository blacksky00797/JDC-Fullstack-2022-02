<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Classes</title>
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
		<h3 style="text-align: center;" class="mt-3 mb-5">Open Classes</h3>
		
		<c:url var="classEdit" value="/classes-edit">
			<c:param name="courseId" value="${course.id}"></c:param>
		</c:url>
		<a href="${classEdit}" class="btn btn-primary mb-3">Add New Class</a>
		
		<c:choose>
			<c:when test="${empty openClasses }">
				<div class="alert alert-warning">There is no classes
					yet.Please add a new class to display on table.</div>
			</c:when>

			<c:otherwise>
				<table class="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>Course Name</th>
							<th>Teacher</th>
							<th>Start Date</th>
							<th>Duration</th>
							<th>Description</th>
							<th align="right"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="oc" items="${openClasses}">
							<tr>
								<td>${oc.id}</td>
								<td>${oc.course.name}</td>
								<td>${oc.teacher}</td>
								<td>${oc.start_date}</td>
								<td>${oc.course.duration} Months</td>
								<td>${oc.course.description}</td>
								<td align="center">
									<c:url var="registration" value="/registration">
										<c:param name="openClassId" value="${oc.id }"/>
									</c:url>
									<a href="${registration}" class="btn btn-success btn-sm">Registration</a>
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