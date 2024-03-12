package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

public class OrderInventory {

	public static String createOrder(Map<Product, Integer> products, CompletionStatus status) {
		if (products == null) {
			throw new IllegalArgumentException("Items cannot be null");
		}
		List<Map<String,Object>> productList = prepareProductListForJSON(products);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "createOrder");
		requestData.put("data", Map.of("Products", productList, "CompletionStatus", status.toString()));
		
		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);
		
		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		Map<String, Object> ordertData = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return (String) ordertData.get("OrderID");
	}
	
	public static boolean deleteOrder(String orderID) {
		if (orderID == null) {
			throw new IllegalArgumentException("Order ID cannot be null");
		}
		if (orderID.isBlank()) {
			throw new IllegalArgumentException("Order ID cannot be blank");
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
	
	public static boolean updateOrder(String orderID, Map<Product, Integer> products, CompletionStatus status) {
		if (orderID == null) {
			throw new IllegalArgumentException("Order ID cannot be null");
		}
		if (orderID.isBlank()) {
			throw new IllegalArgumentException("Order ID cannot be blank");
		}
		if (products == null) {
			throw new IllegalArgumentException("Items cannot be null");
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
		Map<String, Object> orderData = (Map<String, Object>) responseMap.get("data");
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
	
	
	private static Map<Product, Integer> extractProductListFromJson(Map<String, Object> productData) {
		List<Map<String, Object>> productList = (List<Map<String, Object>>) productData.get("Products");
		Map<Product, Integer> products = new HashMap<>();
		for (Map<String, Object> product : productList) {
			Product productObject = ProductInventory.getProduct((String) product.get("ProductID"));
			products.put(productObject, ((Number) product.get("Quantity")).intValue());
		}
		return products;
		
	}
	
}
