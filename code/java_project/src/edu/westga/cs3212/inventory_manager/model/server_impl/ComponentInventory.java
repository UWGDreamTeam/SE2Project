package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import edu.westga.cs3212.inventory_manager.model.Component;

public class ComponentInventory {

	
	public static Component getComponent(String componentID) {
		if (componentID == null) {
			throw new IllegalArgumentException("Component ID cannot be null");
		}
		if (componentID.isBlank()) {
			throw new IllegalArgumentException("Component ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "getComponent");
		requestData.put("data", Map.of("ComponentID", componentID));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		Map<String, Object> dataMap = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		Component component = new Component((String) dataMap.get("Name"), ((Number) dataMap.get("ProductionCost")).doubleValue());
		component.setID(componentID);
		return component;
	}
	
	
	public static String addComponent(String componentName, double productionCost, int quantity) {
		if (componentName == null) {
			throw new IllegalArgumentException("Component name cannot be null");
		}
		if (componentName.isBlank()) {
			throw new IllegalArgumentException("Component name cannot be blank");
		}
		if (productionCost < 0) {
			throw new IllegalArgumentException("Production cost cannot be negative");
		}
		Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "addComponent");
        requestData.put("data", Map.of(
            "Name", componentName,
            "ProductionCost", productionCost,
            "Quantity", quantity
        ));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(requestData);

        String response = Server.sendRequest(requestJson);
        Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> responseMap = gson.fromJson(response, responseType);
        Map<String, Object> dataMap = (Map<String, Object>) responseMap.get("data");
        String componentID = (String) dataMap.get("ComponentID");
        return componentID;
	}
	
	public static boolean deleteComponent(String componentID) {
		if (componentID == null) {
			throw new IllegalArgumentException("Component ID cannot be null");
		}
		if (componentID.isBlank()) {
			throw new IllegalArgumentException("Component ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "deleteComponent");
		requestData.put("data", Map.of("ComponentID", componentID));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return true;
	}
	
	public static boolean updateComponent(String componentID, String componentName, double productionCost, int quantity) {
		if (componentID == null) {
			throw new IllegalArgumentException("Component ID cannot be null");
		}
		if (componentID.isBlank()) {
			throw new IllegalArgumentException("Component ID cannot be blank");
		}
		if (componentName == null) {
			throw new IllegalArgumentException("Component name cannot be null");
		}
		if (componentName.isBlank()) {
			throw new IllegalArgumentException("Component name cannot be blank");
		}
		if (productionCost < 0) {
			throw new IllegalArgumentException("Production cost cannot be negative");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "updateComponent");
		requestData.put("data", Map.of("ComponentID", componentID, "Name", componentName, "ProductionCost", productionCost, "Quantity", quantity));
		
		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);
		
		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return true;
    }
	
	public static int orderComponent(String componentID, int quantity) {
		if (componentID == null) {
            throw new IllegalArgumentException("Component ID cannot be null");
        }
        if (componentID.isBlank()) {
            throw new IllegalArgumentException("Component ID cannot be blank");
        }
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "orderComponent");
        requestData.put("data", Map.of("ComponentID", componentID, "Quantity", quantity));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(requestData);
        
        String response = Server.sendRequest(requestJson);
        Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> responseMap = gson.fromJson(response, responseType);
        Map<String, Object> dataMap = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return ((Number) dataMap.get("Quantity")).intValue();

	}	
	
	public static int getQuantity(String componentID) {
		if (componentID == null) {
			throw new IllegalArgumentException("Component ID cannot be null");
		}
		if (componentID.isBlank()) {
			throw new IllegalArgumentException("Component ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "getQuantityOfComponent");
		requestData.put("data", Map.of("ComponentID", componentID));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);
		Map<String, Object> dataMap = (Map<String, Object>) responseMap.get("data");
		if (responseMap.get("status").equals("error")) {
			throw new IllegalArgumentException((String) responseMap.get("message"));
		}
		return ((Number) dataMap.get("Quantity")).intValue();
	}
}
