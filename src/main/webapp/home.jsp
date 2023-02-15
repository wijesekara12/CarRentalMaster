<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='css/style.css' />">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <title></title>
</head>

<body>
    <!-- As a link -->
    <nav class="navbar navbar-light bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Logo Here</a>
            <a type="button" class="btn btn-light btn-block" href="${pageContext.request.contextPath}/login">Login</a>
        </div>
    </nav>
    <div class="container">
        <h1>Book Vehicles to Rent</h1>
        <div class="row">
            <c:forEach var="vehicle" items="${vehicles}">
                <div class="col-lg-4 col-md-6">
                    <div class="card" style="width: 18rem;">
                        <img src="<c:out value="${vehicle.image}"/>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text"><a href="#"><c:out value="${vehicle.name}"/></a></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="row">
            <a type="button" class="btn btn-primary btn-block" style="width: 150px; margin: 0 auto;" href="${pageContext.request.contextPath}/login">Book Now!</a>
        </div>
    </div>
</body>

</html>