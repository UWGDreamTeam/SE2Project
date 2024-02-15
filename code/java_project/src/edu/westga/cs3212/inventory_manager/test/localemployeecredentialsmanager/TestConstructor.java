package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestConstructor {

	private final Path credentialsFilePath = Paths.get("employeeCredentials.json");
	
	@BeforeEach
	public void setUp() throws Exception {
		Files.deleteIfExists(this.credentialsFilePath);
	}

	
	@Test
    public void testConstructor_initializesMap() {
        LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
    }

}
