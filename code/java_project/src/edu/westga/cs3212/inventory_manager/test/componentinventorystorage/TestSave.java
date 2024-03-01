package edu.westga.cs3212.inventory_manager.test.componentinventorystorage;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.ComponentInventoryStorage;

class TestSave {

	@TempDir
    Path tempDir;

    Path testFile;

    @BeforeEach
    void setUp() throws Exception {
        testFile = tempDir.resolve("testComponents.json");
    }

    @AfterEach
    void tearDown() throws Exception {
        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }
    }

    @Test
    public void testSaveAndLoadComponents() throws Exception {
        Map<Component, Integer> components = new HashMap<>();
        // Adjusted to match the new Component constructor with name and price
        components.put(new Component("Component1", 100.50), 10);
        components.put(new Component("Component2", 200.75), 20);

        ComponentInventoryStorage.save(components, testFile.toString());

        Map<Component, Integer> loadedComponents = ComponentInventoryStorage.load(testFile.toString());

        assertEquals(components.size(), loadedComponents.size(), "The number of components should match");
        
        // Create components again for comparison because they need to match the new constructor
        Component component1 = new Component("Component1", 100.50);
        Component component2 = new Component("Component2", 200.75);
        
        assertTrue(loadedComponents.containsKey(component1), "Loaded components should contain Component1");
        assertTrue(loadedComponents.containsKey(component2), "Loaded components should contain Component2");
        assertEquals(10, loadedComponents.get(component1), "Quantity of Component1 should match");
        assertEquals(20, loadedComponents.get(component2), "Quantity of Component2 should match");
    }

    @Test
    public void testLoadNonExistentFile() {
        Map<Component, Integer> loadedComponents = ComponentInventoryStorage.load("nonexistent.json");
        assertTrue(loadedComponents.isEmpty(), "Loading from a nonexistent file should return an empty map");
    }

}
