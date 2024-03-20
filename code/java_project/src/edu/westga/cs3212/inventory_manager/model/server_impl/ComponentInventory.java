package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Constants;

/**
 * Handles the inventory of components, including CRUD operations and component
 * ordering via interactions with a server.
 * 
 * @author Jason Nunez
 * @version Spring 2024
 */
public class ComponentInventory {

	/**
	 * Retrieves a component by its ID.
	 *
	 * @param componentID The ID of the component to retrieve.
	 * @return The requested component.
	 * @throws IllegalArgumentException If the componentID is null or blank, or if
	 *                                  the server returns an error.
	 */
	public static Component getComponent(String componentID) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = Map.of("ComponentID", componentID);
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("getComponent", Map.of("data", requestData));
		Component component = parseComponent(dataMap, componentID);
		return component;
	}

	/**
	 * Adds a new component to the inventory.
	 *
	 * @param componentName  The name of the component to add.
	 * @param productionCost The production cost of the component.
	 * @param quantity       The quantity of the component.
	 * @return The ID of the newly added component.
	 * @throws IllegalArgumentException If any input parameters are invalid or if
	 *                                  the server returns an error.
	 */
	public static String addComponent(String componentName, double productionCost, int quantity) {
		checkValidComponentName(componentName);
		checkValidProductionCost(productionCost);
		Map<String, Object> requestData = Map.of("data",
				Map.of("Name", componentName, "ProductionCost", productionCost, "Quantity", quantity));

		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("addComponent", requestData);
		return (String) dataMap.get("ComponentID");
	}

	/**
	 * Deletes a component from the inventory by its ID.
	 *
	 * @param componentID The ID of the component to delete.
	 * @throws IllegalArgumentException If the componentID is null or blank, or if
	 *                                  the server returns an error.
	 */
	public static void deleteComponent(String componentID) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ComponentID", componentID));
		Server.sendRequestAndGetResponse("deleteComponent", requestData);
	}

	/**
	 * Updates the information of an existing component in the inventory.
	 *
	 * @param componentID    The ID of the component to update.
	 * @param componentName  The new name of the component.
	 * @param productionCost The new production cost of the component.
	 * @param quantity       The new quantity of the component.
	 * @throws IllegalArgumentException If any input parameters are invalid or if
	 *                                  the server returns an error.
	 */
	public static void updateComponent(String componentID, String componentName, double productionCost, int quantity) {
		checkValidComponentID(componentID);
		checkValidComponentName(componentName);
		checkValidProductionCost(productionCost);

		Map<String, Object> requestData = Map.of("data", Map.of("ComponentID", componentID, "Name", componentName,
				"ProductionCost", productionCost, "Quantity", quantity));

		Server.sendRequestAndGetResponse("updateComponent", requestData);
	}

	/**
	 * Orders a specified quantity of a component by its ID.
	 *
	 * @param componentID The ID of the component to order.
	 * @param quantity    The quantity to order.
	 * @return The updated quantity of the component.
	 * @throws IllegalArgumentException If the componentID is null or blank, the
	 *                                  quantity is invalid, or if the server
	 *                                  returns an error.
	 */
	public static int orderComponent(String componentID, int quantity) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ComponentID", componentID, "Quantity", quantity));
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("orderComponent", requestData);
		return ((Number) dataMap.get("Quantity")).intValue();

	}

	/**
	 * Retrieves the current quantity of a component by its ID.
	 *
	 * @param componentID The ID of the component.
	 * @return The current quantity of the component.
	 * @throws IllegalArgumentException If the componentID is null or blank, or if
	 *                                  the server returns an error.
	 */
	public static int getQuantity(String componentID) {
		checkValidComponentID(componentID);
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("data", Map.of("ComponentID", componentID));
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("getQuantityOfComponent", requestData);
		return ((Number) dataMap.get("Quantity")).intValue();
	}

	private static void checkValidComponentID(String componentID) {
		if (componentID == null) {
			throw new IllegalArgumentException(Constants.COMPONENT_ID_CANNOT_BE_NULL);
		}
		if (componentID.isBlank()) {
			throw new IllegalArgumentException(Constants.COMPONENT_ID_CANNOT_BE_BLANK);
		}
	}

	private static void checkValidComponentName(String componentName) {
		if (componentName == null) {
			throw new IllegalArgumentException(Constants.COMPONENT_NAME_CANNOT_BE_NULL);
		}
		if (componentName.isBlank()) {
			throw new IllegalArgumentException(Constants.COMPONENT_NAME_CANNOT_BE_BLANK);
		}
	}

	private static void checkValidProductionCost(double productionCost) {
		if (productionCost < Constants.MINIMUM_PRODUCTION_COST) {
			throw new IllegalArgumentException(Constants.MINIMUM_PRODUCTION_COST + " is the minimum production cost");
		}
	}

	private static Component parseComponent(Map<String, Object> componentData, String componentID) {
		String name = (String) componentData.get("Name");
		double productionCost = ((Number) componentData.get("ProductionCost")).doubleValue();

		Component component = new Component(name, productionCost);
		component.setID(componentID);
		return component;
	}
}
