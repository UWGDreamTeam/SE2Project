package edu.westga.cs3212.inventory_manager.model;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public final class OrderInventoryStorage {

	private static final String UTILITY_CLASS_ERROR = "Utility class";
	
	private OrderInventoryStorage() {
		throw new IllegalStateException(OrderInventoryStorage.UTILITY_CLASS_ERROR);
	}
	
	
	public static void save(List<Order> orders, String filePath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(orders, writer);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public static List<Order> load(String filePath) {
		List<Order> orders;
		try {
			String json = new String(Files.readAllBytes(Paths.get(filePath)));
			Gson gson = new Gson();
			Type type = new TypeToken<List<Order>>() {
			}.getType();
			orders = gson.fromJson(json, type);
		} catch (IOException exception) {
			orders = new ArrayList<>();
		}
		return orders;
	}
}
