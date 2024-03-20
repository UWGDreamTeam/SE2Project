package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

/**
 * Provides functionality to manage orders, including creating, deleting,
 * updating, and retrieving orders from a server-side inventory system.
 * 
 * @author Jason Nunez
 * @version Spring 2024
 */
public class OrderInventory {

	/**
	 * Creates a new order with the specified products and completion status.
	 *
	 * @param products A map of products and their quantities to be included in the
	 *                 order.
	 * @param status   The completion status of the order.
	 * @return The unique identifier for the newly created order.
	 * @throws IllegalArgumentException If the status is null, products map is null
	 *                                  or empty, or if the server returns an error.
	 */
	public static String createOrder(Map<Product, Integer> products, CompletionStatus status) {
		checkValidStatus(status);
		checkValidProducts(products);
		List<Map<String, Object>> productList = prepareProductListForJSON(products);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("Products", productList, "CompletionStatus", status.toString()));

		Map<String, Object> orderData = Server.sendRequestAndGetResponse("createOrder", requestData);

		return (String) orderData.get("OrderID");
	}

	/**
	 * Deletes an existing order from the inventory by its ID.
	 *
	 * @param orderID The unique identifier of the order to be deleted.
	 * @throws IllegalArgumentException If the orderID is null, blank, or if the
	 *                                  server returns an error.
	 */
	public static void deleteOrder(String orderID) {
		checkValidOrderID(orderID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("OrderID", orderID));
		Server.sendRequestAndGetResponse("deleteOrder", requestData);
	}

	/**
	 * Updates the details of an existing order, including its products, quantities,
	 * and completion status.
	 *
	 * @param orderID  The unique identifier of the order to be updated.
	 * @param products A map of products and their quantities for the order.
	 * @param status   The new completion status of the order.
	 * @throws IllegalArgumentException If the orderID is null or blank, products
	 *                                  map is null, or if the server returns an
	 *                                  error.
	 */
	public static void updateOrder(String orderID, Map<Product, Integer> products, CompletionStatus status) {
		checkValidOrderID(orderID);
		checkValidProducts(products);
		checkValidStatus(status);

		List<Map<String, Object>> productList = prepareProductListForJSON(products);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data",
				Map.of("OrderID", orderID, "Products", productList, "CompletionStatus", status.toString()));

		Server.sendRequestAndGetResponse("updateOrder", requestData);
	}

	/**
	 * Retrieves an existing order from the inventory by its ID.
	 *
	 * @param orderID The unique identifier of the order to be retrieved.
	 * @return The Order object corresponding to the provided ID.
	 * @throws IllegalArgumentException If the orderID is null, blank, or if the
	 *                                  server returns an error.
	 */
	public static Order getOrder(String orderID) {
		checkValidOrderID(orderID);
		Map<String, Object> requestData = Map.of("data", Map.of("OrderID", orderID));
		Map<String, Object> orderData = Server.sendRequestAndGetResponse("getOrder", requestData);

		return extractOrder(orderData);
	}

	private static Order extractOrder(Map<String, Object> orderData) {
		Map<Product, Integer> products = extractProductListFromJson(orderData);
		Order order = new Order();
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			order.addItem(entry.getKey(), entry.getValue());
		}

		order.setID((String) orderData.get("OrderID"));
		order.setCompletionStatus(CompletionStatus.valueOf((String) orderData.get("CompletionStatus")));
		return order;
	}

	private static List<Map<String, Object>> prepareProductListForJSON(Map<Product, Integer> products) {
		List<Map<String, Object>> productList = new ArrayList<>();
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			Map<String, Object> productData = new HashMap<>();
			productData.put("ProductID", entry.getKey().getID());
			productData.put("Quantity", entry.getValue());
			productList.add(productData);
		}
		return productList;
	}

	@SuppressWarnings("unchecked")
	private static Map<Product, Integer> extractProductListFromJson(Map<String, Object> productData) {
		List<Map<String, Object>> productList = (List<Map<String, Object>>) productData.get("Products");
		Map<Product, Integer> products = new HashMap<>();
		for (Map<String, Object> product : productList) {
			Product productObject = ProductInventory.getProduct((String) product.get("ProductID"));
			products.put(productObject, ((Number) product.get("Quantity")).intValue());
		}
		return products;

	}

	private static void checkValidProducts(Map<Product, Integer> products) {
		if (products == null) {
			throw new IllegalArgumentException(Constants.ITEMS_CANNOT_BE_NULL);
		}
		if (products.isEmpty()) {
			throw new IllegalArgumentException(Constants.ITEMS_CANNOT_BE_EMPTY);
		}
	}

	private static void checkValidStatus(CompletionStatus status) {
		if (status == null) {
			throw new IllegalArgumentException(Constants.COMPLETION_STATUS_CANNOT_BE_NULL);
		}
	}

	private static void checkValidOrderID(String orderID) {
		if (orderID == null) {
			throw new IllegalArgumentException(Constants.ORDER_ID_CANNOT_BE_NULL);
		}
		if (orderID.isBlank()) {
			throw new IllegalArgumentException(Constants.ORDER_ID_CANNOT_BE_BLANK);
		}
	}

}
