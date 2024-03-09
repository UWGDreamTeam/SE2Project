package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.westga.cs3212.inventory_manager.model.Component;

public class ProductInventory {

	public String addProduct(String productName, Map<Component, Integer> recipe, int quantity) {
		if (productName == null) {
			throw new IllegalArgumentException("Product name cannot be null");
		}
		if (productName.isBlank()) {
			throw new IllegalArgumentException("Product name cannot be blank");
		}
		if (recipe == null) {
			throw new IllegalArgumentException("Recipe cannot be null");
		}
		if (recipe.isEmpty()) {
			throw new IllegalArgumentException("Recipe cannot be empty");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "addProduct");
		requestData.put("data", Map.of("Name", productName, "Recipe", recipe, "Quantity", quantity));
		
		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);
		
		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		
		return (String) responseMap.get("ProductID");
	}
	
	public boolean produceProduct(String productID, int quantity) {
		if (productID == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException("Product ID cannot be blank");
		}
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "produceProduct");
        requestData.put("data", Map.of("ProductID", productID, "Quantity", quantity));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(requestData);
        
        String response = Server.sendRequest(requestJson);
        Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> responseMap = gson.fromJson(response, responseType);
        
        return (boolean) responseMap.get("Success");	
	}
	
	public boolean removeProduct(String productID) {
		if (productID == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException("Product ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "removeProduct");
		requestData.put("data", Map.of("ProductID", productID));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);

		return (boolean) responseMap.get("Success");
	}
	
	public boolean updateProduct(String productID, String productName, Map<Component, Integer> recipe, int quantity) {
		if (productID == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException("Product ID cannot be blank");
		}
		if (productName == null) {
			throw new IllegalArgumentException("Product name cannot be null");
		}
		if (productName.isBlank()) {
			throw new IllegalArgumentException("Product name cannot be blank");
		}
		if (recipe == null) {
			throw new IllegalArgumentException("Recipe cannot be null");
		}
		if (recipe.isEmpty()) {
			throw new IllegalArgumentException("Recipe cannot be empty");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "updateProduct");
		requestData.put("data",
				Map.of("ProductID", productID, "Name", productName, "Recipe", recipe, "Quantity", quantity));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);

		return (boolean) responseMap.get("Success");
	}
	
	
	
}
