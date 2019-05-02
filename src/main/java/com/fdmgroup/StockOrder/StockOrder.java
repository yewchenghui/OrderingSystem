package com.fdmgroup.StockOrder;

import java.util.List;

public interface StockOrder {
	void addOrder(Order order);
	Order getOrder(Order order);
	void removeOrder(Order order);
	void updateOrder(Order order);
	List<Order> listAllOrder();
}
