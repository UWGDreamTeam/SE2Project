package edu.westga.cs3212.inventory_manager.model;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

	private static final String UTILITY_CLASS_ERROR = "Utility class";
	
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
	public static void save(List<Component> components, String filePath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(components, writer);
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
	public static List<Component> load(String filePath) {
		List<Component> components;
		try {
			String json = new String(Files.readAllBytes(Paths.get(filePath)));
			Gson gson = new Gson();
			Type type = new TypeToken<List<Component>>() {
			}.getType();
			components = gson.fromJson(json, type);
		} catch (IOException e) {
			components = new ArrayList<>();
		}
		return components;
	}
}
