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

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

/**
 * The Class ProductInventoryStorage is used to save and load products from local storage.
 * 
 * @author Jason Nunez
 * @version Spring 2024
 */
public final class ProductInventoryStorage {

	private static final String UTILITY_CLASS_ERROR = "Utility class";
	
	private ProductInventoryStorage() {
		throw new IllegalStateException(ProductInventoryStorage.UTILITY_CLASS_ERROR);
	}
	
	/**
	 * Saves products to local storage.
	 * 
	 * @param products  The list of products to save.
	 * @param filePath  The file path where products are saved.
	 */
	public static void save(List<Product> products, String filePath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(products, writer);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Loads products from local storage.
	 * 
	 * @param filePath The file path from where products are to be loaded.
	 * @return A list of products.
	 */
	public static List<Product> load(String filePath) {
		List<Product> products;
		try {
			String json = new String(Files.readAllBytes(Paths.get(filePath)));
			Gson gson = new Gson();
			Type type = new TypeToken<List<Product>>() {
			}.getType();
			products = gson.fromJson(json, type);
		} catch (IOException e) {
			products = new ArrayList<>();
		}
		return products;
	}
}
