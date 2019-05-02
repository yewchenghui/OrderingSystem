<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delivery Management System: Index Landing</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form action="DoLogin" method="POST">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">Sign in</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body mx-6">
						<div class="md-form mb-6">
							<input type="email" name="defaultForm-email" placeholder="Username" class="form-control validate"> <input type="password" name="defaultForm-pass" placeholder="Password"
								class="form-control validate">
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-center">
						<button class="btn btn-default btn-primary">Login</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #014c85;">
		<a class="navbar-brand" href="./">R&B DMS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link active" href="./">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="AddOrder">Add Order</a></li>
				<li class="nav-item"><a class="nav-link" href="GetOrder">Retrieve Single Order</a></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">View Orders</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="GetAllOrders">All</a> <a class="dropdown-item" href="GetActiveOrders">Active</a> <a class="dropdown-item" href="GetCompletedOrders">Completed</a>
					</div></li>
				<c:if test="${not empty loggedin}">
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin Page</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="ModifyOrder">Modify Order</a> <a class="dropdown-item" href="RemoveOrder">Remove Order</a>
						</div></li>
				</c:if>
			</ul>
			<c:if test="${empty loggedin}">
				<span class="navbar-text"><a class="btn btn-primary btn-rounded" href="" data-toggle="modal" data-target="#modalLoginForm" onclick="" title="Login for Admin Controls.">Login</a></span>
			</c:if>
			<c:if test="${not empty loggedin}">
				<form action="DoLogout" method="POST">
					<span class="navbar-text"><input class="btn btn-danger btn-rounded" type="submit" value="Logout" name="logOutButton" title="Click to logout."></span>
				</form>
			</c:if>
		</div>
	</nav>
	<c:if test="${empty loggedin}">
		<div class="alert alert-info" role="alert">
			<h4 class="alert-heading">Welcome!</h4>
			<hr>
			Website is now functional and online. Report any bugs to the <a href="mailto:ChengHui.Yew@fdmgroup.com" class="alert-link">webmaster</a>, if you find any.
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>
	<c:if test="${not empty loggedin}">
		<div class="alert alert-success" role="alert">Welcome, ${loggedin}!</div>
	</c:if>
	<h1 class="text-center">Delivery Management System</h1>
	<div id="index_picture">
		<img class="rounded mx-auto d-block" style="max-width: 30%;" alt="Responsive image" src="img/delivery-man-flat-vector.jpg"></img>
	</div>
	<div class="container" id="index_navigation">
		<div class="d-flex justify-content-center">
			<a class="btn btn-outline-primary col" href="AddOrder" title="Click here to add an order manually.">Add Order</a> <a class="btn btn-outline-primary col" href="GetOrder"
				title="Click here to retrieve a specific order.">Retrieve Order</a> <a class="btn btn-outline-primary col" href="GetActiveOrders" title="Click here to view all active orders.">View Active Orders</a>
		</div>
		<div class="d-flex justify-content-center">
			<a class="btn btn-outline-primary col" href="RemoveOrder" title="Click here to remove an order. (Requires Admin Password)">Remove Order</a> <a class="btn btn-outline-primary col"
				href="ModifyOrder" title="Click here to modify a specific order.">Modify Order</a> <a class="btn btn-outline-primary col" href="GetAllOrders" title="Click here to view all existing orders.">View
				All Orders</a>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>