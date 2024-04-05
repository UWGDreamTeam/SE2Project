package edu.westga.cs3212.inventory_manager.model.server.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.server.Server;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

/**
 * Handles the inventory of components, including CRUD operations and component
 * ordering via interactions with a server.
 * 
 * @author Jason Nunez
 * @version Spring 2024
 */
public final class ComponentInventory {

	private static final String ACTION_CLEAR_INVENTORY = "clearComponentInventory";
	private static final String ACTION_GET_COMPONENTS = "getComponents";
	private static final String ACTION_GET_QUANTITY_OF_COMPONENT = "getQuantityOfComponent";
	private static final String ACTION_ORDER_COMPONENT = "orderComponent";
	private static final String ACTION_UPDATE_COMPONENT = "updateComponent";
	private static final String ACTION_DELETE_COMPONENT = "deleteComponent";
	private static final String KEY_DATA_QUANTITY = "Quantity";
	private static final String KEY_DATA_PRODUCTION_COST = "ProductionCost";
	private static final String KEY_DATA_NAME = "Name";
	private static final String KEY_DATA = "data";
	private static final String ACTION_GET_COMPONENT = "getComponent";
	private static final String KEY_DATA_COMPONENT_ID = "ComponentID";

	private ComponentInventory() {
		throw new IllegalStateException(
				Constants.COMPONENT_INVENTORY_CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Retrieves a component by its ID.
	 *
	 * @param componentID
	 *            The ID of the component to retrieve.
	 * @precondition componentID != null && !componentID.isBlank() && component
	 *               is in the inventory
	 * @postcondition none
	 * @return The requested component.
	 * @throws IllegalArgumentException
	 *             If the componentID is null or blank, or if the server returns
	 *             an error.
	 */
	public static Component getComponent(String componentID) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = Map.of(KEY_DATA_COMPONENT_ID,
				componentID);
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse(
				ACTION_GET_COMPONENT, Map.of(KEY_DATA, requestData));
		return parseComponent(dataMap, componentID);
	}

	/**
	 * Adds a new component to the inventory.
	 *
	 * @param componentName
	 *            The name of the component to add.
	 * @param productionCost
	 *            The production cost of the component.
	 * @param quantity
	 *            The quantity of the component.
	 * @precondition componentName != null && !componentName.isBlank() &&
	 *               productionCost >= Constants.MINIMUM_PRODUCTION_COST &&
	 *               quantity >= Constants.MINIMUM_QUANTITY_SPINNER_VALUE
	 * @postcondition a new component is added to the inventory
	 * @return The ID of the newly added component.
	 * @throws IllegalArgumentException
	 *             If any input parameters are invalid or if the server returns
	 *             an error.
	 */
	public static String addComponent(String componentName,
			double productionCost, int quantity) {
		checkValidComponentName(componentName);
		checkValidProductionCost(productionCost);
		Map<String, Object> requestData = Map.of(KEY_DATA,
				Map.of(KEY_DATA_NAME, componentName, KEY_DATA_PRODUCTION_COST,
						productionCost, KEY_DATA_QUANTITY, quantity));

		Map<String, Object> dataMap = Server
				.sendRequestAndGetResponse("addComponent", requestData);
		return (String) dataMap.get(KEY_DATA_COMPONENT_ID);
	}

	/**
	 * Deletes a component from the inventory by its ID.
	 *
	 * @param componentID
	 *            The ID of the component to delete.
	 * @precondition componentID != null && !componentID.isBlank() && component
	 *               is in the inventory
	 * @postcondition the component is removed from the inventory
	 * @throws IllegalArgumentException
	 *             If the componentID is null or blank, or if the server returns
	 *             an error.
	 */
	public static void deleteComponent(String componentID) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(KEY_DATA_COMPONENT_ID, componentID));
		Server.sendRequestAndGetResponse(ACTION_DELETE_COMPONENT, requestData);
	}

	/**
	 * Updates the information of an existing component in the inventory.
	 *
	 * @param componentID
	 *            The ID of the component to update.
	 * @param componentName
	 *            The new name of the component.
	 * @param productionCost
	 *            The new production cost of the component.
	 * @param quantity
	 *            The new quantity of the component.
	 * @precondition componentID != null && !componentID.isBlank() &&
	 *               componentName != null && !componentName.isBlank() &&
	 *               productionCost >= Constants.MINIMUM_PRODUCTION_COST &&
	 *               quantity >= Constants.MINIMUM_QUANTITY_SPINNER_VALUE &&
	 *               component is in the inventory
	 * @postcondition the component's information is updated
	 * @throws IllegalArgumentException
	 *             If any input parameters are invalid or if the server returns
	 *             an error.
	 */
	public static void updateComponent(String componentID, String componentName,
			double productionCost, int quantity) {
		checkValidComponentID(componentID);
		checkValidComponentName(componentName);
		checkValidProductionCost(productionCost);

		Map<String, Object> requestData = Map.of(KEY_DATA,
				Map.of(KEY_DATA_COMPONENT_ID, componentID, KEY_DATA_NAME,
						componentName, KEY_DATA_PRODUCTION_COST, productionCost,
						KEY_DATA_QUANTITY, quantity));

		Server.sendRequestAndGetResponse(ACTION_UPDATE_COMPONENT, requestData);
	}

	/**
	 * Orders a specified quantity of a component by its ID.
	 *
	 * @param componentID
	 *            The ID of the component to order.
	 * @param quantity
	 *            The quantity to order.
	 * @precondition componentID != null && !componentID.isBlank() && quantity
	 *               >= Constants.MINIMUM_QUANTITY_SPINNER_VALUE && component is
	 *               in the inventory
	 * @postcondition the component's quantity is updated
	 * @return The updated quantity of the component.
	 * @throws IllegalArgumentException
	 *             If the componentID is null or blank, the quantity is invalid,
	 *             or if the server returns an error.
	 */
	public static int orderComponent(String componentID, int quantity) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(KEY_DATA_COMPONENT_ID, componentID,
				KEY_DATA_QUANTITY, quantity));
		Map<String, Object> dataMap = Server
				.sendRequestAndGetResponse(ACTION_ORDER_COMPONENT, requestData);
		return ((Number) dataMap.get(KEY_DATA_QUANTITY)).intValue();

	}
	
	/**
	 * Searches for components by name using a brute force search.
	 * 
	 * @param searchString The string to search for.
	 * @precondition searchString != null && !searchString.isBlank()
	 * @postcondition none
	 * @return A list of components that contain the search string.
	 */
	public static List<Component> searchComponents(String searchString) {
		ArrayList<Component> results = new ArrayList<>();
		
		for (Component component : getComponents()) {
			if (component.getName().toLowerCase().contains(searchString.toLowerCase())) {
				results.add(component);
			}
		}
		return results;
	}

	/**
	 * Retrieves the current quantity of a component by its ID.
	 *
	 * @param componentID
	 *            The ID of the component.
	 * @precondition componentID != null && !componentID.isBlank() && component
	 *               is in the inventory
	 * @postcondition none
	 * @return The current quantity of the component.
	 * @throws IllegalArgumentException
	 *             If the componentID is null or blank, or if the server returns
	 *             an error.
	 */
	public static int getQuantity(String componentID) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put(KEY_DATA, Map.of(KEY_DATA_COMPONENT_ID, componentID));
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse(
				ACTION_GET_QUANTITY_OF_COMPONENT, requestData);
		return ((Number) dataMap.get(KEY_DATA_QUANTITY)).intValue();
	}

	private static void checkValidComponentID(String componentID) {
		if (componentID == null) {
			throw new IllegalArgumentException(
					Constants.COMPONENT_ID_CANNOT_BE_NULL);
		}
		if (componentID.isBlank()) {
			throw new IllegalArgumentException(
					Constants.COMPONENT_ID_CANNOT_BE_BLANK);
		}
	}

	private static void checkValidComponentName(String componentName) {
		if (componentName == null) {
			throw new IllegalArgumentException(
					Constants.COMPONENT_NAME_CANNOT_BE_NULL);
		}
		if (componentName.isBlank()) {
			throw new IllegalArgumentException(
					Constants.COMPONENT_NAME_CANNOT_BE_BLANK);
		}
	}

	private static void checkValidProductionCost(double productionCost) {
		if (productionCost < Constants.MINIMUM_PRODUCTION_COST) {
			throw new IllegalArgumentException(Constants.MINIMUM_PRODUCTION_COST
					+ " is the minimum production cost");
		}
	}

	private static Component parseComponent(Map<String, Object> componentData,
			String componentID) {
		String name = (String) componentData.get(KEY_DATA_NAME);
		double productionCost = ((Number) componentData
				.get(KEY_DATA_PRODUCTION_COST)).doubleValue();

		Component component = new Component(name, productionCost);
		component.setID(componentID);
		return component;
	}

	/**
	 * Retrieves all components in the inventory.
	 * @precondition none
	 * @postcondition none
	 * @return An array of all components in the inventory.
	 */
	public static Component[] getComponents() {
		Map<String, Object> dataMap = Server
				.sendRequestAndGetResponse(ACTION_GET_COMPONENTS);
		return parseComponents(dataMap);
	}

	private static Component[] parseComponents(Map<String, Object> dataMap) {
		Component[] components = new Component[dataMap.size()];
		int index = 0;
		for (String key : dataMap.keySet()) {
			Map<String, Object> componentMap = (Map<String, Object>) dataMap
					.get(key);
			Component component = extractComponent(key, componentMap);
			components[index] = component;
			index++;
		}
		return components;
	}

	private static Component extractComponent(String key,
			Map<String, Object> componentMap) {
		Component component = new Component(
				(String) componentMap.get(KEY_DATA_NAME),
				(double) componentMap.get(KEY_DATA_PRODUCTION_COST));
		component.setID(key);
		return component;
	}

	/**
	 * Clears the inventory of all components.
	 * 
	 * @precondition none
	 * @postcondition the inventory is empty
	 */
	public static void clearInventory() {
		Server.sendRequestAndGetResponse(ACTION_CLEAR_INVENTORY);
	}
}
