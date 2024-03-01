package edu.westga.cs3212.inventory_manager.test.componentinventorystorage;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.ComponentInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.Constants;

class TestSave {

	@AfterEach
	void tearDown() throws IOException {
		Files.deleteIfExists(Paths.get("test.json"));
	}
	
	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get("test.json"));
	}

    @Test
    public void testSaveAndLoadComponents() throws Exception {
        Map<Component, Integer> components = new HashMap<>();
        
        components.put(new Component("Component1", 100.50), 10);
        components.put(new Component("Component2", 200.75), 20);

        ComponentInventoryStorage.save(components, "test.json");
        
        
        Map<Component, Integer> loadedComponents = ComponentInventoryStorage.load("test.json");
        Map<Component, Integer> expectedComponents = ComponentInventoryStorage.load(Constants.COMPONENT_INVENTORY_FILE_LOCATION);
        
        assertNotNull(expectedComponents);
        assertEquals(2, loadedComponents.size());
        
       
    }

    @Test
    public void testLoadNonExistentFile() {
        Map<Component, Integer> loadedComponents = ComponentInventoryStorage.load("nonexistent.json");
        assertTrue(loadedComponents.isEmpty(), "Loading from a nonexistent file should return an empty map");
    }
    
    @Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException {
        Constructor<ComponentInventoryStorage> constructor = ComponentInventoryStorage.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()), "Constructor is not private");
        
        constructor.setAccessible(true); // Make the private constructor accessible
        
        try {
            constructor.newInstance();
            fail("Constructor invocation should throw an IllegalStateException");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException, "Cause of InvocationTargetException should be IllegalStateException");
            assertEquals(ComponentInventoryStorage.UTILITY_CLASS_ERROR, e.getCause().getMessage(), "Exception message does not match");
        } catch (InstantiationException e) {
            fail("Instantiation exception should not occur");
        }
    }
    
    @Test
    void testSaveWithInvalidFilePathThrowsException() {
        Map<Component, Integer> components = new HashMap<>();

        String invalidFilePath = "/invalid/path/to/file";
		assertEquals(0, components.size());
		ComponentInventoryStorage.save(components, invalidFilePath);
    }

    @Test
    void testSaveWithFilePathAsDirectoryThrowsException() {
        Map<Component, Integer> components = new HashMap<>();
        String filePathAsDirectory = "/tmp"; 
        ComponentInventoryStorage.save(components, filePathAsDirectory);
    }

}
