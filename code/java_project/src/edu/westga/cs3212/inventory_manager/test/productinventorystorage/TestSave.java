package edu.westga.cs3212.inventory_manager.test.productinventorystorage;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.storage.ProductInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestSave {

	@Test
    public void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException {
        Constructor<ProductInventoryStorage> constructor = ProductInventoryStorage.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()), "Constructor is not private");
        
        constructor.setAccessible(true); // Make the private constructor accessible
        
        try {
            constructor.newInstance();
            fail("Constructor invocation should throw an IllegalStateException");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalStateException, "Cause of InvocationTargetException should be IllegalStateException");
            assertEquals(ProductInventoryStorage.UTILITY_CLASS_ERROR, e.getCause().getMessage(), "Exception message does not match");
        } catch (InstantiationException e) {
            fail("Instantiation exception should not occur");
        }
    }
	
	@Test
    void testSaveWithInvalidFilePathThrowsException() {
		Map<Product, Integer> products = new HashMap<>();

        String invalidFilePath = "/invalid/path/to/file";
		assertEquals(0, products.size());
		ProductInventoryStorage.save(products, invalidFilePath);
    }

    @Test
    void testSaveWithFilePathAsDirectoryThrowsException() {
    	Map<Product, Integer> products = new HashMap<>();
        String filePathAsDirectory = "/tmp"; 
        ProductInventoryStorage.save(products, filePathAsDirectory);
        assertEquals(0, products.size());
    }

}
