package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.westga.cs3212.inventory_manager.model.Component;

public class ComponentInventory {

	public boolean addComponent(String componentName, String productionCost) {
		if (componentName == null) {
			throw new IllegalArgumentException("Component name cannot be null");
		}
		if (componentName.isBlank()) {
			throw new IllegalArgumentException("Component name cannot be blank");
		}
		if (productionCost == null) {
			throw new IllegalArgumentException("Production cost cannot be null");
		}
		if (productionCost.isBlank()) {
			throw new IllegalArgumentException("Production cost cannot be blank");
		}
		if (Double.parseDouble(productionCost) < 0) {
			throw new IllegalArgumentException("Production cost cannot be negative");
		}
		
	}
	
	
}
