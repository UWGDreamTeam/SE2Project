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
public final class OrderInventory {

	private static final String ACTION_GET_ORDERS_BY_COMPLETION_STATUS = "getOrdersByCompletionStatus";
	private static final String ACTION_GET_ORDER = "getOrder";
	private static final String ACTION_UPDATE_ORDER = "updateOrder";
	private static final String ACTION_DELETE_ORDER = "deleteOrder";
	private static final String KEY_DATA_ORDER_ID = "OrderID";
	private static final String ACTION_CREATE_ORDER = "createOrder";
	private static final String KEY_DATA_COMPLETION_STATUS = "CompletionStatus";
	private static final String KEY_DATA_PRODUCTS = "Products";
	private static final String KEY_DATA = "data";

	private OrderInventory() {
		throw new IllegalStateException(
				Constants.ORDER_INVENTORY_CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Creates a new order with the specified products and completion status.
	 *
	 * @param products
	 *            A map of products and their quantities to be included in the
	 *            order.
	 * @param status
	 *            The completion status of the order.
	 * @precondition products != null && !products.isEmpty() && status != null
	 *               && !status.isBlank() && products contains valid products
	 *               and quantities and products are in the inventory &&
	 *               components in products are in the inventory and valid
	 * @postcondition A new order is created with the specified products and
	 *                status
	 * @return The unique identifier for the newly created order.
	 * @throws IllegalArgumentException
	 *             If the status is null, products map is null or empty, or if
	 *             the server returns an error.
	 */
	public static String createOrder(Map<Product, Integer> products,
			CompletionStatus status) {
		checkValidStatus(status);
		checkValidProducts(products);
		List<Map<String, Object>> productList = prepareProductListForJSON(
				products);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(KEY_DATA_PRODUCTS, productList,
				KEY_DATA_COMPLETION_STATUS, status.toString()));

		Map<String, Object> orderData = Server
				.sendRequestAndGetResponse(ACTION_CREATE_ORDER, requestData);

		return (String) orderData.get(KEY_DATA_ORDER_ID);
	}

	/**
	 * Deletes an existing order from the inventory by its ID.
	 *
	 * @param orderID
	 *            The unique identifier of the order to be deleted.
	 * @precondition orderID != null && !orderID.isBlank() && orderID is a valid
	 *               && orderID is in the inventory
	 * @postcondition The order with the specified ID is deleted from the
	 *                inventory
	 * @throws IllegalArgumentException
	 *             If the orderID is null, blank, or if the server returns an
	 *             error.
	 */
	public static void deleteOrder(String orderID) {
		checkValidOrderID(orderID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(KEY_DATA_ORDER_ID, orderID));
		Server.sendRequestAndGetResponse(ACTION_DELETE_ORDER, requestData);
	}

	/**
	 * Updates the details of an existing order, including its products,
	 * quantities, and completion status.
	 *
	 * @param orderID
	 *            The unique identifier of the order to be updated.
	 * @param products
	 *            A map of products and their quantities for the order.
	 * @param status
	 *            The new completion status of the order.
	 * @precondition orderID != null && !orderID.isBlank() && orderID is in the
	 *               inventory && products != null && !products.isEmpty() &&
	 *               status != null && !status.isBlank() && products contains
	 *               valid products and quantities and products are in the
	 *               inventory && components in products are in the inventory
	 *               and valid
	 * @postcondition The order with the specified ID is updated with the new
	 *                products and status
	 * @throws IllegalArgumentException
	 *             If the orderID is null or blank, products map is null, or if
	 *             the server returns an error.
	 */
	public static void updateOrder(String orderID,
			Map<Product, Integer> products, CompletionStatus status) {
		checkValidOrderID(orderID);
		checkValidProducts(products);
		checkValidStatus(status);

		List<Map<String, Object>> productList = prepareProductListForJSON(
				products);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA,
				Map.of(KEY_DATA_ORDER_ID, orderID, KEY_DATA_PRODUCTS,
						productList, KEY_DATA_COMPLETION_STATUS,
						status.toString()));

		Server.sendRequestAndGetResponse(ACTION_UPDATE_ORDER, requestData);
	}

	/**
	 * Retrieves an existing order from the inventory by its ID.
	 *
	 * @param orderID
	 *            The unique identifier of the order to be retrieved.
	 * @precondition orderID != null && !orderID.isBlank() && orderID is in the
	 *               inventory
	 * @postcondition The order with the specified ID is retrieved from the
	 *                inventory
	 * @return The Order object corresponding to the provided ID.
	 * @throws IllegalArgumentException
	 *             If the orderID is null, blank, or if the server returns an
	 *             error.
	 */
	public static Order getOrder(String orderID) {
		checkValidOrderID(orderID);
		Map<String, Object> requestData = Map.of(KEY_DATA,
				Map.of(KEY_DATA_ORDER_ID, orderID));
		Map<String, Object> orderData = Server
				.sendRequestAndGetResponse(ACTION_GET_ORDER, requestData);

		return extractOrder(orderData);
	}

	private static Order extractOrder(Map<String, Object> orderData) {
		Map<Product, Integer> products = extractProductListFromJson(orderData);
		Order order = new Order();
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			order.addItem(entry.getKey(), entry.getValue());
		}

		order.setID((String) orderData.get(KEY_DATA_ORDER_ID));
		order.setCompletionStatus(CompletionStatus
				.valueOf((String) orderData.get(KEY_DATA_COMPLETION_STATUS)));
		return order;
	}

	private static List<Map<String, Object>> prepareProductListForJSON(
			Map<Product, Integer> products) {
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
	private static Map<Product, Integer> extractProductListFromJson(
			Map<String, Object> productData) {
		List<Map<String, Object>> productList = (List<Map<String, Object>>) productData
				.get(KEY_DATA_PRODUCTS);
		Map<Product, Integer> products = new HashMap<>();
		for (Map<String, Object> product : productList) {
			Product productObject = ProductInventory
					.getProduct((String) product.get("ProductID"));
			products.put(productObject,
					((Number) product.get("Quantity")).intValue());
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
			throw new IllegalArgumentException(
					Constants.COMPLETION_STATUS_CANNOT_BE_NULL);
		}
	}

	private static void checkValidOrderID(String orderID) {
		if (orderID == null) {
			throw new IllegalArgumentException(
					Constants.ORDER_ID_CANNOT_BE_NULL);
		}
		if (orderID.isBlank()) {
			throw new IllegalArgumentException(
					Constants.ORDER_ID_CANNOT_BE_BLANK);
		}
	}

	public static void clearOrders() {
		Server.sendRequestAndGetResponse("clearOrders");
	}

	public static List<Order> getOrdersByCompletionStatus(
			CompletionStatus complete) {
		Map<String, Object> requestData = Map.of(KEY_DATA,
				Map.of(KEY_DATA_COMPLETION_STATUS, complete.toString()));
		Map<String, Object> orderData = Server.sendRequestAndGetResponse(
				ACTION_GET_ORDERS_BY_COMPLETION_STATUS, requestData);
		return parseOrders(orderData);
	}

    @SuppressWarnings("unchecked")
	private static List<Order> parseOrders(Map<String, Object> orderData) {
		List<Order> orders = new ArrayList<>();
		List<Map<String, Object>> orderList = (List<Map<String, Object>>) orderData
                .get(KEY_DATA);
        for (Map<String, Object> order : orderList) {
            orders.add(extractOrder(order));
        }
        return orders;
	}
	

}
