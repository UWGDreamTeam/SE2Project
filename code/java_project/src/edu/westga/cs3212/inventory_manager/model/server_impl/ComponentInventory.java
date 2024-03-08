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

	public String addComponent(String componentName, double productionCost) {
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
            "ProductionCost", productionCost
        ));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(requestData);

        String response = Server.sendRequest(requestJson);
        Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> responseMap = gson.fromJson(response, responseType);

        return (String) responseMap.get("ComponentID");
	}
	
	public boolean removeComponent(String componentID) {
		if (componentID == null) {
			throw new IllegalArgumentException("Component ID cannot be null");
		}
		if (componentID.isBlank()) {
			throw new IllegalArgumentException("Component ID cannot be blank");
		}
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("type", "removeComponent");
		requestData.put("data", Map.of("ComponentID", componentID));

		Gson gson = new Gson();
		String requestJson = gson.toJson(requestData);

		String response = Server.sendRequest(requestJson);
		Type responseType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> responseMap = gson.fromJson(response, responseType);

		return (boolean) responseMap.get("Success");
	}
	
}
