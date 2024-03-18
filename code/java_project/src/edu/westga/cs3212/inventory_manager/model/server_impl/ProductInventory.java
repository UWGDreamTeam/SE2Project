package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;

public class ProductInventory {

	public static Product getProduct(String productID) {
		if (productID == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException("Product ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "getProduct");
		requestData.put("data", Map.of("ProductID", productID));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		Map<String, Object> productData = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		Map<Component, Integer> recipe = extractRecipeFromJson(productData);
		Product product = new Product((String) productData.get("Name"),
				((Number) productData.get("ProductionCost")).doubleValue(),
				((Number) productData.get("SalePrice")).doubleValue(), recipe);
		product.setID(productID);
		return product;
	}

	public static String addProduct(String productName, double salePrice, Map<Component, Integer> recipe,
			int quantity) {
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
		if (salePrice < 0) {
			throw new IllegalArgumentException("Sale price cannot be negative");
		}
		List<Map<String, Object>> recipeList = prepareRecipeListForJSON(recipe);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "addProduct");
		requestData.put("data",
				Map.of("Name", productName, "SalePrice", salePrice, "Recipe", recipeList, "Quantity", quantity));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		Map<String, Object> productData = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return (String) productData.get("ProductID");
	}

	public static int produceProduct(String productID, int quantity) {
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
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		Map<String, Object> productData = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return ((Number) productData.get("Quantity")).intValue();

	}

	public static boolean deleteProduct(String productID) {
		if (productID == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException("Product ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "deleteProduct");
		requestData.put("data", Map.of("ProductID", productID));

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

	public static boolean updateProduct(String productID, String productName, double salePrice,
			Map<Component, Integer> recipe, int quantity) {
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
		if (salePrice < 0) {
			throw new IllegalArgumentException("Sale price cannot be negative");
		}
		Map<String, Object> requestData = new HashMap<>();
		List<Map<String, Object>> recipeList = prepareRecipeListForJSON(recipe);
		requestData.put("type", "updateProduct");
		requestData.put("data",
				Map.of("ProductID", productID, "Name", productName, "SalePrice", salePrice, "Recipe", recipeList, "Quantity", quantity));

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

	public static int getQuantity(String productID) {
		if (productID == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException("Product ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "getQuantityOfProduct");
		requestData.put("data", Map.of("ProductID", productID));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		Map<String, Object> productData = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return ((Number) productData.get("Quantity")).intValue();
	}

	private static Map<Component, Integer> extractRecipeFromJson(Map<String, Object> productData) {
		List<Map<String, Object>> recipeData = (List<Map<String, Object>>) productData.get("Recipe");
		Map<Component, Integer> recipe = new HashMap<>();
		for (Map<String, Object> componentData : recipeData) {
			Component component = ComponentInventory.getComponent((String) componentData.get("ComponentID"));
			recipe.put(component, ((Number) componentData.get("Quantity")).intValue());
		}
		return recipe;
	}

	private static List<Map<String, Object>> prepareRecipeListForJSON(Map<Component, Integer> recipe) {
		List<Map<String, Object>> recipeList = new ArrayList<>();
		for (Entry<Component, Integer> entry : recipe.entrySet()) {
			Map<String, Object> componentMap = new HashMap<>();
			componentMap.put("ComponentID", entry.getKey().getID());
			componentMap.put("Quantity", entry.getValue());
			recipeList.add(componentMap);
		}
		return recipeList;
	}

}
