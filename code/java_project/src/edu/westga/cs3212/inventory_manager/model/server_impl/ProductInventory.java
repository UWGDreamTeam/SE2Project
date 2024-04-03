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
public final class ProductInventory {

	private static final String ACTION_CLEAR_INVENTORY = "clearProductInventory";
	private static final String KEY_DATA_COMPONENT_ID = "ComponentID";
	private static final String ACTION_GET_QUANTITY_OF_PRODUCT = "getQuantityOfProduct";
	private static final String ACTION_UPDATE_PRODUCT = "updateProduct";
	private static final String ACTION_DELETE_PRODUCT = "deleteProduct";
	private static final String ACTION_PRODUCE_PRODUCT = "produceProduct";
	private static final String ACTION_ADD_PRODUCT = "addProduct";
	private static final String KEY_DATA_QUANTITY = "Quantity";
	private static final String KEY_DATA_RECIPE = "Recipe";
	private static final String KEY_DATA_SALE_PRICE = "SalePrice";
	private static final String KEY_DATA_NAME = "Name";
	private static final String KEY_DATA = "data";
	private static final String ACTION_GET_PRODUCT = "getProduct";
	private static final String PRODUCT_ID = "ProductID";
	private static final String ACTION_GET_PRODUCTS = "getProducts";

	private ProductInventory() {
		throw new IllegalStateException(
				Constants.PRODUCT_INVENTORY_CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Retrieves a Product object by its ID from the server.
	 *
	 * @param productID
	 *            The unique identifier for the product.
	 * @precondition productID != null && productID is not blank
	 * @postcondition none
	 * @return The Product object corresponding to the provided ID.
	 * @throws IllegalArgumentException
	 *             If the productID is null, blank, or if the server returns an
	 *             error.
	 */
	public static Product getProduct(String productID) {
		productIDValid(productID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(PRODUCT_ID, productID));

		Map<String, Object> productData = Server
				.sendRequestAndGetResponse(ACTION_GET_PRODUCT, requestData);
		Map<Component, Integer> recipe = extractRecipeFromJson(productData);

		return extractProduct(productID, productData, recipe);
	}

	/**
	 * Adds a new product to the inventory with the given details.
	 *
	 * @param productName
	 *            The name of the product.
	 * @param salePrice
	 *            The sale price of the product.
	 * @param recipe
	 *            The recipe components and their quantities for the product.
	 * @param quantity
	 *            The initial quantity of the product.
	 * @precondition productName != null && productName is not blank &&
	 *               salePrice >= 0 && recipe != null && recipe is not empty &&
	 *               quantity >= 0 && recipe contains only valid components &&
	 *               recipe contains only positive quantities
	 * @postcondition A new product is added to the inventory with the given
	 *                details.
	 * @return The unique identifier for the newly added product.
	 * @throws IllegalArgumentException
	 *             If any input validation fails or if the server returns an
	 *             error.
	 */
	public static String addProduct(String productName, double salePrice,
			Map<Component, Integer> recipe, int quantity) {
		checkValidProductName(productName);
		checkValidRecipe(recipe);
		checkValidQuantity(quantity);
		checkValidSalesPrice(salePrice);
		List<Map<String, Object>> recipeList = prepareRecipeListForJSON(recipe);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA,
				Map.of(KEY_DATA_NAME, productName, KEY_DATA_SALE_PRICE,
						salePrice, KEY_DATA_RECIPE, recipeList,
						KEY_DATA_QUANTITY, quantity));

		Map<String, Object> productData = Server
				.sendRequestAndGetResponse(ACTION_ADD_PRODUCT, requestData);

		return (String) productData.get(PRODUCT_ID);
	}

	/**
	 * Initiates the production of a specified quantity of the product
	 * identified by the productID.
	 *
	 * @param productID
	 *            The unique identifier for the product.
	 * @param quantity
	 *            The quantity of the product to be produced.
	 * @precondition productID != null && productID is not blank && quantity >=
	 *               0
	 * @postcondition The quantity of the product is updated after production.
	 * @return The new quantity of the product after production.
	 * @throws IllegalArgumentException
	 *             If the productID is invalid, quantity is negative, or if the
	 *             server returns an error.
	 */
	public static int produceProduct(String productID, int quantity) {
		productIDValid(productID);

		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA,
				Map.of(PRODUCT_ID, productID, KEY_DATA_QUANTITY, quantity));

		Map<String, Object> productData = Server
				.sendRequestAndGetResponse(ACTION_PRODUCE_PRODUCT, requestData);

		return ((Number) productData.get(KEY_DATA_QUANTITY)).intValue();

	}

	/**
	 * Deletes a product from the inventory by its ID.
	 *
	 * @param productID
	 *            The unique identifier for the product to be deleted.
	 * @precondition productID != null && productID is not blank && productID
	 *               exists
	 * @postcondition The product is deleted from the inventory.
	 * @throws IllegalArgumentException
	 *             If the productID is invalid or if the server returns an
	 *             error.
	 */
	public static void deleteProduct(String productID) {
		productIDValid(productID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(PRODUCT_ID, productID));

		Server.sendRequestAndGetResponse(ACTION_DELETE_PRODUCT, requestData);
	}

	/**
	 * Updates the details of an existing product in the inventory.
	 *
	 * @param productID
	 *            The unique identifier for the product.
	 * @param productName
	 *            The new name for the product.
	 * @param salePrice
	 *            The new sale price for the product.
	 * @param recipe
	 *            The new recipe components and their quantities for the
	 *            product.
	 * @param quantity
	 *            The new quantity of the product.
	 * @precondition productID != null && productID is not blank && productName
	 *               != null && productName is not blank && salePrice >= 0 &&
	 *               recipe != null && recipe is not empty && quantity >= 0 &&
	 *               recipe contains only valid components
	 * @postcondition The product details are updated in the inventory.
	 * @throws IllegalArgumentException
	 *             If any input validation fails or if the server returns an
	 *             error.
	 */
	public static void updateProduct(String productID, String productName,
			double salePrice, Map<Component, Integer> recipe, int quantity) {
		productIDValid(productID);
		checkValidProductName(productName);
		checkValidRecipe(recipe);
		checkValidQuantity(quantity);
		checkValidSalesPrice(salePrice);

		List<Map<String, Object>> recipeList = prepareRecipeListForJSON(recipe);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA,
				Map.of(PRODUCT_ID, productID, KEY_DATA_NAME, productName,
						KEY_DATA_SALE_PRICE, salePrice, KEY_DATA_RECIPE,
						recipeList, KEY_DATA_QUANTITY, quantity));

		Server.sendRequestAndGetResponse(ACTION_UPDATE_PRODUCT, requestData);
	}
	
	/**
	 * Searches for products by name using a brute force search.
	 * 
	 * @param searchString The string to search for.
	 * @precondition searchString != null && !searchString.isBlank()
	 * @postcondition none
	 * @return A list of products that contain the search string.
	 */
	public static List<Product> searchProducts(String searchString) {
		ArrayList<Product> results = new ArrayList<>();
		
		for (Product product : getProducts()) {
			if (product.getName().toLowerCase().contains(searchString.toLowerCase())) {
				results.add(product);
			}
		}
		return results;
	}

	/**
	 * Retrieves the current quantity of a specified product.
	 *
	 * @param productID
	 *            The unique identifier for the product.
	 * @precondition productID != null && productID is not blank
	 * @postcondition none
	 * @return The current quantity of the product.
	 * @throws IllegalArgumentException
	 *             If the productID is invalid or if the server returns an
	 *             error.
	 */
	public static int getQuantity(String productID) {
		productIDValid(productID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(PRODUCT_ID, productID));

		Map<String, Object> productData = Server.sendRequestAndGetResponse(
				ACTION_GET_QUANTITY_OF_PRODUCT, requestData);

		return ((Number) productData.get(KEY_DATA_QUANTITY)).intValue();
	}

	private static void productIDValid(String productID) {
		if (productID == null) {
			throw new IllegalArgumentException(
					Constants.PRODUCT_ID_CANNOT_BE_NULL);
		}
		if (productID.isBlank()) {
			throw new IllegalArgumentException(
					Constants.PRODUCT_ID_CANNOT_BE_BLANK);
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<Component, Integer> extractRecipeFromJson(
			Map<String, Object> productData) {
		List<Map<String, Object>> recipeData = (List<Map<String, Object>>) productData
				.get(KEY_DATA_RECIPE);
		Map<Component, Integer> recipe = new HashMap<>();
		for (Map<String, Object> componentData : recipeData) {
			Component component = ComponentInventory.getComponent(
					(String) componentData.get(KEY_DATA_COMPONENT_ID));
			recipe.put(component,
					((Number) componentData.get(KEY_DATA_QUANTITY)).intValue());
		}
		return recipe;
	}

	private static List<Map<String, Object>> prepareRecipeListForJSON(
			Map<Component, Integer> recipe) {
		List<Map<String, Object>> recipeList = new ArrayList<>();
		for (Entry<Component, Integer> entry : recipe.entrySet()) {
			Map<String, Object> componentMap = new HashMap<>();
			componentMap.put(KEY_DATA_COMPONENT_ID, entry.getKey().getID());
			componentMap.put(KEY_DATA_QUANTITY, entry.getValue());
			recipeList.add(componentMap);
		}
		return recipeList;
	}

	private static void checkValidProductName(String productName) {
		if (productName == null) {
			throw new IllegalArgumentException(
					Constants.PRODUCT_NAME_CANNOT_BE_NULL);
		}
		if (productName.isBlank()) {
			throw new IllegalArgumentException(
					Constants.PRODUCT_NAME_CANNOT_BE_BLANK);
		}
	}

	private static Product extractProduct(String productID,
			Map<String, Object> productData, Map<Component, Integer> recipe) {
		Product product = new Product((String) productData.get(KEY_DATA_NAME),
				((Number) productData.get("ProductionCost")).doubleValue(),
				((Number) productData.get(KEY_DATA_SALE_PRICE)).doubleValue(),
				recipe);
		product.setID(productID);
		return product;
	}

	private static void checkValidSalesPrice(double salePrice) {
		if (salePrice < Constants.MINIMUM_SALES_PRICE) {
			throw new IllegalArgumentException(
					Constants.PRODUCT_SALE_PRICE_CANNOT_BE_NEGATIVE);
		}
	}

	private static void checkValidQuantity(int quantity) {
		if (quantity < Constants.MINIMUM_QUANTITY_SPINNER_VALUE) {
			throw new IllegalArgumentException(
					Constants.PRODUCT_QUANTITY_CANNOT_BE_NEGATIVE);
		}
	}

	private static void checkValidRecipe(Map<Component, Integer> recipe) {
		if (recipe == null) {
			throw new IllegalArgumentException(Constants.RECIPE_CANNOT_BE_NULL);
		}
		if (recipe.isEmpty()) {
			throw new IllegalArgumentException(
					Constants.RECIPE_CANNOT_BE_EMPTY);
		}
	}

	/**
	 * Retrieves all products from the server.
	 * @precondition none
	 * @postcondition none
	 * @return An array of all products in the inventory.
	 */
	public static Product[] getProducts() {
		Map<String, Object> dataMap = Server
				.sendRequestAndGetResponse(ACTION_GET_PRODUCTS);
		return parseProducts(dataMap);
	}

	private static Product[] parseProducts(Map<String, Object> response) {
		Product[] products = new Product[response.size()];
        int index = 0;
        for (String key : response.keySet()) {
            Map<String, Object> productData = (Map<String, Object>) response
                    .get(key);
            String productID = key;
            Map<Component, Integer> recipe = extractRecipeFromJson(productData);
            products[index] = extractProduct(productID, productData, recipe);
            index++;
        }
        return products;
	}
	
	/**
	 * Clears the product inventory.
	 * 
	 * @precondition none
	 * @postcondition The product inventory is empty.
	 */
	public static void clearInventory() {
		Server.sendRequestAndGetResponse(ACTION_CLEAR_INVENTORY);
	}

}
