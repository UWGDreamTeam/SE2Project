package edu.westga.cs3212.inventory_manager.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class EmployeeCredentialsStorage is used to save and load employee credentials from local storage.
 * @version Spring 2024
 * @Author Jason Nunez
 */
public class EmployeeCredentialsStorage {

	private static final String UTILITY_CLASS_ERROR = "Utility class";
	
	private EmployeeCredentialsStorage() {
		throw new IllegalStateException(EmployeeCredentialsStorage.UTILITY_CLASS_ERROR);
	}
	
	/**
	 * Saves employee credentials to local storage.
	 * 
	 * @param employeeCredentialsMap The map of employee credentials to save.
	 * @param filePath               The file path where credentials are saved.
	 */
	public static void save(Map<String, LocalEmployeeCredentials> employeeCredentialsMap, String filePath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(employeeCredentialsMap, writer);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Loads employee credentials from local storage.
	 * 
	 * @param filePath The file path from where credentials are to be loaded.
	 * @return A map of employee credentials.
	 */
	public static Map<String, LocalEmployeeCredentials> load(String filePath) {
		HashMap<String, LocalEmployeeCredentials> employeeCredentialsMap;
		try {
			String json = new String(Files.readAllBytes(Paths.get(filePath)));
			Gson gson = new Gson();
			Type type = new TypeToken<HashMap<String, LocalEmployeeCredentials>>() {
			}.getType();
			employeeCredentialsMap = gson.fromJson(json, type);
		} catch (IOException e) {
			employeeCredentialsMap = new HashMap<>();
		}
		return employeeCredentialsMap;
	}
}
