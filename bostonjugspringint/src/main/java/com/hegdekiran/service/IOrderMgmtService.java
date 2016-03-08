package com.hegdekiran.service;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;

import com.hegdekiran.beans.Order;

public interface IOrderMgmtService {

	// Create Synchronous Orders
	@Gateway(requestChannel = "createOrdersRequestChannel", requestTimeout = 500, replyTimeout = 500)
	Order createOrder(Order order);

	/**
	 * When the return type is changed to Future, Spring framework's
	 * GatewayProxyFactoryBean class takes care of switching to asynchronous
	 * mode by leveraging AsyncTaskExecutor.
	 * 
	 * @param order
	 * @return
	 */
	@Gateway(requestChannel = "createAyncOrdersRequestChannel", requestTimeout = 500, replyTimeout = 500)
	Future<Order> createFeed(Order order);

	List<Order> displayAllOrders();
}
