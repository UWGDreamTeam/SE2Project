package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
     * @throws IllegalArgumentException If the componentID is null or blank, or if the server returns an error.
     */
    public static Component getComponent(String componentID) {
	checkValidComponentID(componentID);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "getComponent");
	requestData.put("data", Map.of("ComponentID", componentID));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	Map<String, Object> dataMap = safelyCastToMap(responseMap.get("data"));
	if (responseMap.get("status").equals("error")) {
	    throw new IllegalArgumentException((String) responseMap.get("message"));
	}
	Component component = new Component((String) dataMap.get("Name"),
		((Number) dataMap.get("ProductionCost")).doubleValue());
	component.setID(componentID);
	return component;
    }

    /**
     * Adds a new component to the inventory.
     *
     * @param componentName The name of the component to add.
     * @param productionCost The production cost of the component.
     * @param quantity The quantity of the component.
     * @return The ID of the newly added component.
     * @throws IllegalArgumentException If any input parameters are invalid or if the server returns an error.
     */
    public static String addComponent(String componentName, double productionCost, int quantity) {
	checkValidComponentName(componentName);
	checkValidProductionCost(productionCost);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "addComponent");
	requestData.put("data", Map.of("Name", componentName, "ProductionCost", productionCost, "Quantity", quantity));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	Map<String, Object> dataMap = safelyCastToMap(responseMap.get("data"));
	String componentID = (String) dataMap.get("ComponentID");
	return componentID;
    }

    /**
     * Deletes a component from the inventory by its ID.
     *
     * @param componentID The ID of the component to delete.
     * @return true if the operation was successful.
     * @throws IllegalArgumentException If the componentID is null or blank, or if the server returns an error.
     */
    public static boolean deleteComponent(String componentID) {
	checkValidComponentID(componentID);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "deleteComponent");
	requestData.put("data", Map.of("ComponentID", componentID));

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
     * Updates the information of an existing component in the inventory.
     *
     * @param componentID The ID of the component to update.
     * @param componentName The new name of the component.
     * @param productionCost The new production cost of the component.
     * @param quantity The new quantity of the component.
     * @return true if the operation was successful.
     * @throws IllegalArgumentException If any input parameters are invalid or if the server returns an error.
     */
    public static boolean updateComponent(String componentID, String componentName, double productionCost,
	    int quantity) {
	checkValidComponentID(componentID);
	checkValidComponentName(componentName);
	checkValidProductionCost(productionCost);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "updateComponent");
	requestData.put("data", Map.of("ComponentID", componentID, "Name", componentName, "ProductionCost",
		productionCost, "Quantity", quantity));

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
     * Orders a specified quantity of a component by its ID.
     *
     * @param componentID The ID of the component to order.
     * @param quantity The quantity to order.
     * @return The updated quantity of the component.
     * @throws IllegalArgumentException If the componentID is null or blank, the quantity is invalid, or if the server returns an error.
     */
    public static int orderComponent(String componentID, int quantity) {
	checkValidComponentID(componentID);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "orderComponent");
	requestData.put("data", Map.of("ComponentID", componentID, "Quantity", quantity));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	Map<String, Object> dataMap = safelyCastToMap(responseMap.get("data"));
	if (responseMap.get("status").equals("error")) {
	    throw new IllegalArgumentException((String) responseMap.get("message"));
	}
	return ((Number) dataMap.get("Quantity")).intValue();

    }

    /**
     * Retrieves the current quantity of a component by its ID.
     *
     * @param componentID The ID of the component.
     * @return The current quantity of the component.
     * @throws IllegalArgumentException If the componentID is null or blank, or if the server returns an error.
     */
    public static int getQuantity(String componentID) {
	checkValidComponentID(componentID);
	Map<String, Object> requestData = new HashMap<>();
	requestData.put("type", "getQuantityOfComponent");
	requestData.put("data", Map.of("ComponentID", componentID));

	Gson gson = new Gson();
	String requestJson = gson.toJson(requestData);

	String response = Server.sendRequest(requestJson);
	Type responseType = new TypeToken<Map<String, Object>>() {
	}.getType();
	Map<String, Object> responseMap = gson.fromJson(response, responseType);
	Map<String, Object> dataMap = safelyCastToMap(responseMap.get("data"));
	if (responseMap.get("status").equals("error")) {
	    throw new IllegalArgumentException((String) responseMap.get("message"));
	}
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
    
    @SuppressWarnings("unchecked")
    private static Map<String, Object> safelyCastToMap(Object object) {
	if (object instanceof Map<?, ?>) {
	    return (Map<String, Object>) object;
	} else {
	    throw new IllegalArgumentException(Constants.INVALID_RESPONSE_FROM_SERVER);
	}
    }
}
