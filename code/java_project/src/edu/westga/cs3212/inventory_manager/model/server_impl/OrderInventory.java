package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

/**
 * Provides functionality to manage orders, including creating, deleting, updating,
 * and retrieving orders from a server-side inventory system.
 * @author Jason Nunez
 * @version Spring 2024
 */
public class OrderInventory {

    /**
     * Creates a new order with the specified products and completion status.
     *
     * @param products A map of products and their quantities to be included in the order.
     * @param status The completion status of the order.
     * @return The unique identifier for the newly created order.
     * @throws IllegalArgumentException If the status is null, products map is null or empty,
     *                                  or if the server returns an error.
     */
    public static String createOrder(Map<Product, Integer> products, CompletionStatus status) {
	if (status == null) {
	    throw new IllegalArgumentException(Constants.COMPLETION_STATUS_CANNOT_BE_NULL);
	}
	if (products == null) {
	    throw new IllegalArgumentException(Constants.ITEMS_CANNOT_BE_NULL);
	}
	if (products.isEmpty()) {
	    throw new IllegalArgumentException(Constants.ITEMS_CANNOT_BE_EMPTY);
	}
	List<Map<String, Object>> productList = prepareProductListForJSON(products);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "createOrder");
	requestData.put("data", Map.of("Products", productList, "CompletionStatus", status.toString()));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	Map<String, Object> ordertData = safelyCastToMap(responseMap.get("data"));
	if (responseMap.get("status").equals("error")) {
	    throw new IllegalArgumentException((String) responseMap.get("message"));
	}
	return (String) ordertData.get("OrderID");
    }

    /**
     * Deletes an existing order from the inventory by its ID.
     *
     * @param orderID The unique identifier of the order to be deleted.
     * @return true if the deletion was successful, false otherwise.
     * @throws IllegalArgumentException If the orderID is null, blank,
     *                                  or if the server returns an error.
     */
    public static boolean deleteOrder(String orderID) {
	if (orderID == null) {
	    throw new IllegalArgumentException(Constants.ORDER_ID_CANNOT_BE_NULL);
	}
	if (orderID.isBlank()) {
	    throw new IllegalArgumentException(Constants.ORDER_ID_CANNOT_BE_BLANK);
	}
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "deleteOrder");
	requestData.put("data", Map.of("OrderID", orderID));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	if (responseMap.get("status").equals("error")) {
	    throw new IllegalArgumentException((String) responseMap.get("message"));
	}
	return true;
    }

    /**
     * Updates the details of an existing order, including its products, quantities,
     * and completion status.
     *
     * @param orderID The unique identifier of the order to be updated.
     * @param products A map of products and their quantities for the order.
     * @param status The new completion status of the order.
     * @return true if the update was successful, false otherwise.
     * @throws IllegalArgumentException If the orderID is null or blank, products map is null,
     *                                  or if the server returns an error.
     */
    public static boolean updateOrder(String orderID, Map<Product, Integer> products, CompletionStatus status) {
	if (orderID == null) {
	    throw new IllegalArgumentException(Constants.ORDER_ID_CANNOT_BE_NULL);
	}
	if (orderID.isBlank()) {
	    throw new IllegalArgumentException(Constants.ORDER_ID_CANNOT_BE_BLANK);
	}
	if (products == null) {
	    throw new IllegalArgumentException(Constants.ITEMS_CANNOT_BE_NULL);
	}
	List<Map<String, Object>> productList = prepareProductListForJSON(products);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "updateOrder");
	requestData.put("data",
		Map.of("OrderID", orderID, "Products", productList, "CompletionStatus", status.toString()));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	if (responseMap.get("status").equals("error")) {
	    throw new IllegalArgumentException((String) responseMap.get("message"));
	}
	return true;
    }

    /**
     * Retrieves an existing order from the inventory by its ID.
     *
     * @param orderID The unique identifier of the order to be retrieved.
     * @return The Order object corresponding to the provided ID.
     * @throws IllegalArgumentException If the orderID is null, blank,
     *                                  or if the server returns an error.
     */
    public static Order getOrder(String orderID) {
	if (orderID == null) {
	    throw new IllegalArgumentException("Order ID cannot be null");
	}
	if (orderID.isBlank()) {
	    throw new IllegalArgumentException("Order ID cannot be blank");
	}
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "getOrder");
	requestData.put("data", Map.of("OrderID", orderID));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	Map<String, Object> orderData = safelyCastToMap(responseMap.get("data"));
	if (responseMap.get("status").equals("error")) {
	    throw new IllegalArgumentException((String) responseMap.get("message"));
	}
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
    
    @SuppressWarnings("unchecked")
    private static Map<String, Object> safelyCastToMap(Object object) {
	if (object instanceof Map<?, ?>) {
	    return (Map<String, Object>) object;
	} else {
	    throw new IllegalArgumentException(Constants.INVALID_RESPONSE_FROM_SERVER);
	}
    }

}
