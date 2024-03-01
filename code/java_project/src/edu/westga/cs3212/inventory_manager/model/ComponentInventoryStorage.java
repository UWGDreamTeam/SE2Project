package edu.westga.cs3212.inventory_manager.model;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * The Class ComponentInventoryStorage is used to save and load components from local storage.
 * 
 * It uses the Gson library to serialize and deserialize components.
 * @author Jason Nunez
 * @version Spring 2024
 */
public final class ComponentInventoryStorage {

	public static final String UTILITY_CLASS_ERROR = "Utility class";
	
	private ComponentInventoryStorage() {
		throw new IllegalStateException(UTILITY_CLASS_ERROR);
	}	
	
	/**
	 * Saves components to local storage.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param components  The list of components to save.
	 * @param filePath  The file path where products are saved.
	 */
	public static void save(Map<Component, Integer> components, String filePath) {
	    Map<String, Component> componentMap = new HashMap<>();
	    Map<String, Integer> quantityMap = new HashMap<>();

	    for (Map.Entry<Component, Integer> entry : components.entrySet()) {
	        String componentId = entry.getKey().getID();
	        componentMap.put(componentId, entry.getKey());
	        quantityMap.put(componentId, entry.getValue());
	    }

	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    String componentJson = gson.toJson(componentMap);
	    String quantityJson = gson.toJson(quantityMap);

	    try (FileWriter componentWriter = new FileWriter(filePath)) {
	        componentWriter.write(componentJson);
	    } catch (Exception e) {
	        
	    }

	    try (FileWriter quantityWriter = new FileWriter(filePath + "1")) {
	        quantityWriter.write(quantityJson);
	    } catch (Exception e) {
	        
	    }
	}

	/**
	 * Loads components from local storage.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param filePath The file path from where products are to be loaded.
	 * @return A list of products.
	 */
	public static Map<Component, Integer> load(String filePath) {
	    Gson gson = new Gson();
	    Map<Component, Integer> components = new HashMap<>();

	    try {
	        String componentJson = new String(Files.readAllBytes(Paths.get(filePath)));
	        Type componentType = new TypeToken<Map<String, Component>>(){}.getType();
	        Map<String, Component> componentMap = gson.fromJson(componentJson, componentType);

	        String quantityJson = new String(Files.readAllBytes(Paths.get(filePath + "1")));
	        Type quantityType = new TypeToken<Map<String, Integer>>(){}.getType();
	        Map<String, Integer> quantityMap = gson.fromJson(quantityJson, quantityType);

	        for (Map.Entry<String, Component> entry : componentMap.entrySet()) {
	            String componentId = entry.getKey();
	            Component component = entry.getValue();
	            Integer quantity = quantityMap.get(componentId);
	            components.put(component, quantity);
	        }
	    } catch (Exception e) {
	       
	    }

	    return components;
	}
}
