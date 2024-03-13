package edu.westga.cs3212.inventory_manager.model;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Provides utility methods for saving and loading orders to and from a file using JSON serialization.
 * This class cannot be instantiated and is designed to be used statically.
 *
 * @author Group 1
 * @version Spring 2024
 */
public final class OrderInventoryStorage {

	private static final String UTILITY_CLASS_ERROR = "Utility class";

	private OrderInventoryStorage() {
		throw new IllegalStateException(OrderInventoryStorage.UTILITY_CLASS_ERROR);
	}

	/**
	 * Saves a list of orders to a specified file path in JSON format.
	 * 
	 * @param orders   The list of orders to save.
	 * @param filePath The file path where the orders should be saved.
	 * @precondition orders != null && filePath != null && !filePath.isEmpty()
	 * @postcondition The orders are saved to the specified file in JSON format.
	 * 
	 * @throws Exception if an I/O error occurs during saving the orders.
	 */
	public static void save(List<Order> orders, String filePath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(orders, writer);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	 /**
     * Loads a list of orders from a specified file path that contains JSON formatted data.
     * 
     * @param filePath The file path from which to load the orders.
     * @return A list of orders deserialized from the specified file. Returns an empty list if the file cannot be read or the data is invalid.
     * @precondition filePath != null && !filePath.isEmpty()
     * @postcondition none
     * 
     * @throws Exception if an I/O error occurs during loading the orders.
     */
	public static List<Order> load(String filePath) {
		ArrayList<Order> orders;
		try {
			String json = new String(Files.readAllBytes(Paths.get(filePath)));
			Gson gson = new Gson();
			Type type = new TypeToken<ArrayList<Order>>() {
			}.getType();
			orders = gson.fromJson(json, type);
		} catch (Exception exception) {
			orders = new ArrayList<>();
		}
		return orders;
	}
}
