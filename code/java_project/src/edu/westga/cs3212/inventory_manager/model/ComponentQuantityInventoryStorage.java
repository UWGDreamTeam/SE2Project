package edu.westga.cs3212.inventory_manager.model;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public final class ComponentQuantityInventoryStorage {

	private static final String UTILITY_CLASS_ERROR = "Utility class";
	
	private ComponentQuantityInventoryStorage() {
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
	public static void save(Map<String, Integer> componentQuantity, String filePath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(componentQuantity, writer);
		} catch (IOException exception) {
			exception.printStackTrace();
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
	public static Map<String, Integer> load(String filePath) {
		Map<String, Integer> componentQuantity;
		try {
			String json = new String(Files.readAllBytes(Paths.get(filePath)));
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, Integer>>() {
			}.getType();
			componentQuantity = gson.fromJson(json, type);
		} catch (IOException e) {
			componentQuantity = new HashMap<>();
		}
		return componentQuantity;
	}
}
