package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Product;

/**
 * The ProductInventory class provides methods to interact with the product
 * inventory, including operations to get, add, delete, update, and retrieve
 * product quantities.
 * 
 * @author Jason Nunez
 * @version Spring 2024
 */
public class ProductInventory {

	/**
	 * Retrieves a Product object by its ID from the server.
	 *
	 * @param productID The unique identifier for the product.
	 * @return The Product object corresponding to the provided ID.
	 * @throws IllegalArgumentException If the productID is null, blank, or if the
	 *                                  server returns an error.
	 */
	public static Product getProduct(String productID) {
		productIDValid(productID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ProductID", productID));

		Map<String, Object> productData = Server.sendRequestAndGetResponse("getProduct", requestData);
		Map<Component, Integer> recipe = extractRecipeFromJson(productData);

		return extractProduct(productID, productData, recipe);
	}

	/**
	 * Adds a new product to the inventory with the given details.
	 *
	 * @param productName The name of the product.
	 * @param salePrice   The sale price of the product.
	 * @param recipe      The recipe components and their quantities for the
	 *                    product.
	 * @param quantity    The initial quantity of the product.
	 * @return The unique identifier for the newly added product.
	 * @throws IllegalArgumentException If any input validation fails or if the
	 *                                  server returns an error.
	 */
	public static String addProduct(String productName, double salePrice, Map<Component, Integer> recipe,
			int quantity) {
		productNameValid(productName);
		checkValidRecipe(recipe);
		checkValidQuantity(quantity);
		checkValidSalesPrice(salePrice);
		List<Map<String, Object>> recipeList = prepareRecipeListForJSON(recipe);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data",
				Map.of("Name", productName, "SalePrice", salePrice, "Recipe", recipeList, "Quantity", quantity));

		Map<String, Object> productData = Server.sendRequestAndGetResponse("addProduct", requestData);

		return (String) productData.get("ProductID");
	}

	/**
	 * Initiates the production of a specified quantity of the product identified by
	 * the productID.
	 *
	 * @param productID The unique identifier for the product.
	 * @param quantity  The quantity of the product to be produced.
	 * @return The new quantity of the product after production.
	 * @throws IllegalArgumentException If the productID is invalid, quantity is
	 *                                  negative, or if the server returns an error.
	 */
	public static int produceProduct(String productID, int quantity) {
		productIDValid(productID);

		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ProductID", productID, "Quantity", quantity));

		Map<String, Object> productData = Server.sendRequestAndGetResponse("produceProduct", requestData);

		return ((Number) productData.get("Quantity")).intValue();

	}

	/**
	 * Deletes a product from the inventory by its ID.
	 *
	 * @param productID The unique identifier for the product to be deleted.
	 * @throws IllegalArgumentException If the productID is invalid or if the server
	 *                                  returns an error.
	 */
	public static void deleteProduct(String productID) {
		productIDValid(productID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ProductID", productID));

		Server.sendRequestAndGetResponse("deleteProduct", requestData);
	}

	/**
	 * Updates the details of an existing product in the inventory.
	 *
	 * @param productID   The unique identifier for the product.
	 * @param productName The new name for the product.
	 * @param salePrice   The new sale price for the product.
	 * @param recipe      The new recipe components and their quantities for the
	 *                    product.
	 * @param quantity    The new quantity of the product.
	 * @throws IllegalArgumentException If any input validation fails or if the
	 *                                  server returns an error.
	 */
	public static void updateProduct(String productID, String productName, double salePrice,
			Map<Component, Integer> recipe, int quantity) {
		productIDValid(productID);
		productNameValid(productName);
		checkValidRecipe(recipe);
		checkValidQuantity(quantity);
		checkValidSalesPrice(salePrice);

		List<Map<String, Object>> recipeList = prepareRecipeListForJSON(recipe);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ProductID", productID, "Name", productName, "SalePrice", salePrice, "Recipe",
				recipeList, "Quantity", quantity));

		Server.sendRequestAndGetResponse("updateProduct", requestData);
	}

	/**
	 * Retrieves the current quantity of a specified product.
	 *
	 * @param productID The unique identifier for the product.
	 * @return The current quantity of the product.
	 * @throws IllegalArgumentException If the productID is invalid or if the server
	 *                                  returns an error.
	 */
	public static int getQuantity(String productID) {
		productIDValid(productID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ProductID", productID));

		Map<String, Object> productData = Server.sendRequestAndGetResponse("getQuantityOfProduct", requestData);

		return ((Number) productData.get("Quantity")).intValue();
	}

	private static void productIDValid(String productID) {
		if (productID == null) {
			throw new IllegalArgumentException(Constants.PRODUCT_ID_CANNOT_BE_NULL);
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException(Constants.PRODUCT_ID_CANNOT_BE_BLANK);
		}
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	private static Map<String, Object> safelyCastToMap(Object object) {
		return (Map<String, Object>) object;
	}

	private static void productNameValid(String productName) {
		if (productName == null) {
			throw new IllegalArgumentException(Constants.PRODUCT_NAME_CANNOT_BE_NULL);
		}
		if (productName.isBlank()) {
			throw new IllegalArgumentException(Constants.PRODUCT_NAME_CANNOT_BE_BLANK);
		}
	}

	private static Product extractProduct(String productID, Map<String, Object> productData,
			Map<Component, Integer> recipe) {
		Product product = new Product((String) productData.get("Name"),
				((Number) productData.get("ProductionCost")).doubleValue(),
				((Number) productData.get("SalePrice")).doubleValue(), recipe);
		product.setID(productID);
		return product;
	}

	private static void checkValidSalesPrice(double salePrice) {
		if (salePrice < 0) {
			throw new IllegalArgumentException(Constants.PRODUCT_SALE_PRICE_CANNOT_BE_NEGATIVE);
		}
	}

	private static void checkValidQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException(Constants.PRODUCT_QUANTITY_CANNOT_BE_NEGATIVE);
		}
	}

	private static void checkValidRecipe(Map<Component, Integer> recipe) {
		if (recipe == null) {
			throw new IllegalArgumentException(Constants.RECIPE_CANNOT_BE_NULL);
		}
		if (recipe.isEmpty()) {
			throw new IllegalArgumentException(Constants.RECIPE_CANNOT_BE_EMPTY);
		}
	}

}
