package edu.westga.cs3212.inventory_manager.model;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * The Class ProductInventoryStorage is used to save and load products from
 * local storage.
 * 
 * @author Jason Nunez
 * @version Spring 2024
 */
public final class ProductInventoryStorage {

    public static final String UTILITY_CLASS_ERROR = "Utility class";

    private ProductInventoryStorage() {
	throw new IllegalStateException(ProductInventoryStorage.UTILITY_CLASS_ERROR);
    }

    /**
     * Saves products to local storage.
     * 
     * @precondition none
     * @postcondition products are saved to local storage.
     * 
     * @param products The list of products to save.
     * @param filePath The file path where products are saved.
     */
    public static void save(Map<Product, Integer> products, String filePath) {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	try (FileWriter writer = new FileWriter(filePath)) {
	    gson.toJson(products, writer);
	} catch (Exception exception) {

	}
    }

    /**
     * Loads products from local storage.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @param filePath The file path from where products are to be loaded.
     * @return A list of products.
     */
    public static Map<Product, Integer> load(String filePath) {
	Map<Product, Integer> products;
	try {
	    String json = new String(Files.readAllBytes(Paths.get(filePath)));
	    Gson gson = new Gson();
	    Type type = new TypeToken<Map<Product, Integer>>() {
	    }.getType();
	    products = gson.fromJson(json, type);
	} catch (Exception e) {
	    products = new HashMap<>();
	}
	return products;
    }
}
