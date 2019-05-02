<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fdmgroup.StockOrder.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delivery Management System: Order Updated Success</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #014c85;">
		<a class="navbar-brand" href="./">R&B DMS</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="./">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="AddOrder">Add Order</a></li>
				<li class="nav-item"><a class="nav-link" href="GetOrder">Retrieve Single Order</a></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">View Orders</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="GetAllOrders">All</a> <a class="dropdown-item" href="GetActiveOrders">Active</a> <a class="dropdown-item" href="GetCompletedOrders">Completed</a>
					</div></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin Page</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="ModifyOrder">Modify Order</a> <a class="dropdown-item" href="RemoveOrder">Remove Order</a>
					</div></li>
			</ul>
		</div>
	</nav>
	
	<% java.util.ArrayList paymentList = new java.util.ArrayList(); paymentList.add("Cash"); paymentList.add("MasterCard"); paymentList.add("Visa"); paymentList.add("Amex"); paymentList.add("UnionPay"); paymentList.add("eNets"); request.setAttribute("paymentList", paymentList);%>
	<% java.util.ArrayList storeList = new java.util.ArrayList(); storeList.add("North"); storeList.add("South"); storeList.add("East"); storeList.add("West"); storeList.add("Central"); request.setAttribute("storeList", storeList);%>
	<% java.util.ArrayList stateList = new java.util.ArrayList(); stateList.add("Not Delivered"); stateList.add("In Progress"); stateList.add("Completed"); stateList.add("Cancelled"); stateList.add("Halted"); request.setAttribute("stateList", stateList);%>
	<div class="alert alert-success" role="alert">Order has been updated.</div>
	<h1>Updated Order Details</h1>
	<table class="table table-bordered table-responsive table-striped">
		<tr class="text-center">
			<th>Order ID</th>
			<th>Payment</th>
			<th>Price Total</th>
			<th>Store</th>
			<th>Customer Name</th>
			<th>Contact Number</th>
			<th>Address</th>
			<th>VIP Status</th>
			<th>Order Status</th>
			<th>Rider Name</th>
			<th>Order Created Date</th>
			<th>Order Created Time</th>
			<th>Order Completed Date</th>
			<th>Order Completed Time</th>
		</tr>
		<tr class="text-center">
			<td><c:out value="${order.orderId}" /></td>
			<td><c:out value="${paymentList.get(order.customerPaymentType)}" /></td>
			<td><c:out value="\$${order.orderTotal}" /></td>
			<td><c:out value="${order.storeName}" /></td>
			<td><c:out value="${order.customerName}" /></td>
			<td><c:out value="${order.customerPhoneNumber}" /></td>
			<td><c:out value="${order.customerAddress}" /></td>
			<td><c:out value="${order.customerVIPStatus}" /></td>
			<td><c:out value="${stateList.get(order.orderState)}" /></td>
			<td><c:out value="${order.riderName}" />
			<td><c:out value="${order.orderCreatedDate}" /></td>
			<td><c:out value="${order.orderCreatedTime}" /></td>
			<td><c:out value="${order.orderCompletedDate}" /></td>
			<td><c:out value="${order.orderCompletedTime}" /></td>
		</tr>
	</table>
	<div class="col-md-12 text-center">
		<a class="btn btn-secondary" href="./">Back to Main</a>	
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>