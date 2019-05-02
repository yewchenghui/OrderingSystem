package com.fdmgroup.StockOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DELIVERY")
public class Order {

	@Id
	@GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
	private int orderId;
	private String storeName;
	private String customerName;
	private int customerPhoneNumber;
	private int customerVIPStatus;
	private int customerPaymentType;
	private int orderState;
	private String orderCreatedDate;
	private String orderCreatedTime;
	private String orderCompletedDate;
	private String orderCompletedTime;
	private String riderName;
	private String customerAddress;
	private double orderTotal;

	public Order(String storeName, String customerName, int customerPhoneNumber, int customerPaymentType,
			String customerAddress, double orderTotal) {
		this.storeName = storeName;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerVIPStatus = 0;
		this.customerPaymentType = customerPaymentType;
		this.orderState = 0;
		this.orderCreatedDate = LocalDate.now().toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		this.orderCreatedTime = LocalDateTime.now().format(formatter);
		this.customerAddress = customerAddress;
		this.orderTotal = orderTotal;
	}

	public Order() {
		this.customerVIPStatus = 0;
		this.orderState = 0;
		this.orderCreatedDate = LocalDate.now().toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		this.orderCreatedTime = LocalDateTime.now().format(formatter);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(int customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public int getCustomerVIPStatus() {
		return customerVIPStatus;
	}

	public void setCustomerVIPStatus(int customerVIPStatus) {
		this.customerVIPStatus = customerVIPStatus;
	}

	public int getCustomerPaymentType() {
		return customerPaymentType;
	}

	public void setCustomerPaymentType(int customerPaymentType) {
		this.customerPaymentType = customerPaymentType;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public String getOrderCreatedDate() {
		return orderCreatedDate;
	}

	public void setOrderCreatedDate(String orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}

	public String getOrderCreatedTime() {
		return orderCreatedTime;
	}

	public void setOrderCreatedTime(String orderCreatedTime) {
		this.orderCreatedTime = orderCreatedTime;
	}

	public String getOrderCompletedDate() {
		return orderCompletedDate;
	}

	public void setOrderCompletedDate(String orderCompletedDate) {
		this.orderCompletedDate = orderCompletedDate;
	}

	public String getOrderCompletedTime() {
		return orderCompletedTime;
	}

	public void setOrderCompletedTime(String orderCompletedTime) {
		this.orderCompletedTime = orderCompletedTime;
	}

	public String getRiderName() {
		return riderName;
	}

	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", storeName=" + storeName + ", customerName=" + customerName
				+ ", customerPhoneNumber=" + customerPhoneNumber + ", customerVIPStatus=" + customerVIPStatus
				+ ", customerPaymentType=" + customerPaymentType + ", orderState=" + orderState + ", orderCreatedDate="
				+ orderCreatedDate + ", orderCreatedTime=" + orderCreatedTime + ", orderCompletedDate="
				+ orderCompletedDate + ", orderCompletedTime=" + orderCompletedTime + ", riderName=" + riderName
				+ ", customerAddress=" + customerAddress + ", orderTotal=" + orderTotal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + customerPaymentType;
		result = prime * result + customerPhoneNumber;
		result = prime * result + customerVIPStatus;
		result = prime * result + ((orderCompletedDate == null) ? 0 : orderCompletedDate.hashCode());
		result = prime * result + ((orderCompletedTime == null) ? 0 : orderCompletedTime.hashCode());
		result = prime * result + ((orderCreatedDate == null) ? 0 : orderCreatedDate.hashCode());
		result = prime * result + ((orderCreatedTime == null) ? 0 : orderCreatedTime.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + orderState;
		long temp;
		temp = Double.doubleToLongBits(orderTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((riderName == null) ? 0 : riderName.hashCode());
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerPaymentType != other.customerPaymentType)
			return false;
		if (customerPhoneNumber != other.customerPhoneNumber)
			return false;
		if (customerVIPStatus != other.customerVIPStatus)
			return false;
		if (orderCompletedDate == null) {
			if (other.orderCompletedDate != null)
				return false;
		} else if (!orderCompletedDate.equals(other.orderCompletedDate))
			return false;
		if (orderCompletedTime == null) {
			if (other.orderCompletedTime != null)
				return false;
		} else if (!orderCompletedTime.equals(other.orderCompletedTime))
			return false;
		if (orderCreatedDate == null) {
			if (other.orderCreatedDate != null)
				return false;
		} else if (!orderCreatedDate.equals(other.orderCreatedDate))
			return false;
		if (orderCreatedTime == null) {
			if (other.orderCreatedTime != null)
				return false;
		} else if (!orderCreatedTime.equals(other.orderCreatedTime))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderState != other.orderState)
			return false;
		if (Double.doubleToLongBits(orderTotal) != Double.doubleToLongBits(other.orderTotal))
			return false;
		if (riderName == null) {
			if (other.riderName != null)
				return false;
		} else if (!riderName.equals(other.riderName))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		return true;
	}
}
