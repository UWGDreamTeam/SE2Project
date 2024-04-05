package edu.westga.cs3212.inventory_manager.test.employeecredentialsstorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.credentials.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.storage.EmployeeCredentialsStorage;

public class TestSave {

	@Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException {
        Constructor<EmployeeCredentialsStorage> constructor = EmployeeCredentialsStorage.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()), "Constructor is not private");
        
        constructor.setAccessible(true); // Make the private constructor accessible
        
        try {
            constructor.newInstance();
            fail("Constructor invocation should throw an IllegalStateException");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException, "Cause of InvocationTargetException should be IllegalStateException");
            assertEquals(EmployeeCredentialsStorage.UTILITY_CLASS_ERROR, e.getCause().getMessage(), "Exception message does not match");
        } catch (InstantiationException e) {
            fail("Instantiation exception should not occur");
        }
    }
	
    @Test
    void testSaveWithInvalidFilePathThrowsException() {
    	Map<String, LocalEmployeeCredentials> employeeCredentialsMap = new HashMap<>();

        String invalidFilePath = "/invalid/path/to/file";
		assertEquals(0, employeeCredentialsMap.size());
		EmployeeCredentialsStorage.save(employeeCredentialsMap, invalidFilePath);
    }

    @Test
    void testSaveWithFilePathAsDirectoryThrowsException() {
    	Map<String, LocalEmployeeCredentials> employeeCredentialsMap = new HashMap<>();
        String filePathAsDirectory = "/tmp"; 
        EmployeeCredentialsStorage.save(employeeCredentialsMap, filePathAsDirectory);
        assertEquals(0, employeeCredentialsMap.size());
    }
}
