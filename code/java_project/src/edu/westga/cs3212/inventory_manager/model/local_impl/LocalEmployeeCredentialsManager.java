package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import edu.wetsga.cs3212.inventory_manager.model.SystemCredentialsManager;

public class LocalEmployeeCredentialsManager extends SystemCredentialsManager {
	private Map<String, LocalEmployeeCredentials> employeeCredentialsMap;

	public LocalEmployeeCredentialsManager() {
		this.employeeCredentialsMap = new HashMap<String, LocalEmployeeCredentials>();
		this.loadEmployeeCredentials();
	}

    public LocalEmployeeCredentials createEmployee(String firstName, String lastName, String password, LocalEmployeeCredentials.EmployeeType employeeType) {
        LocalEmployeeCredentials newCredentials = new LocalEmployeeCredentials(firstName, lastName, password, employeeType);
        this.employeeCredentialsMap.put(newCredentials.getEmployeeID(), newCredentials);
        this.saveChanges();
        return newCredentials;
    }

    public LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {
        return this.employeeCredentialsMap.get(employeeID);
    }

	@Override
	public String getEmployeePassword(String employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeEmployee(String employeeID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEmployee(String employeeID, String password, String employeeType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployeePassword(String employeeID, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateEmployeeID() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean attemptLogin(String employeeID, String password) {
		if (employeeID == null || employeeID.isEmpty() || password == null || password.isEmpty()) {
			return false;
		}
		if (this.employeeCredentialsMap.containsKey(employeeID)) {
			LocalEmployeeCredentials employee = this.employeeCredentialsMap.get(employeeID);
			if (employee.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	private void saveChanges() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("employeeCredentials.json")) {
            gson.toJson(this.employeeCredentialsMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	 private void loadEmployeeCredentials() {
	        try {
	            String json = new String(Files.readAllBytes(Paths.get("employeeCredentials.json")));
	            Gson gson = new Gson();
	            Type type = new TypeToken<HashMap<String, LocalEmployeeCredentials>>(){}.getType();
	            this.employeeCredentialsMap = gson.fromJson(json, type);
	        } catch (IOException e) {
	            this.employeeCredentialsMap = new HashMap<>();
	        }
	    }
}
