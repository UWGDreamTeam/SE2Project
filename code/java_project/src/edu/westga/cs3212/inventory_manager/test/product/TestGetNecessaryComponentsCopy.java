package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

class TestGetNecessaryComponentsCopy {

	@Test
	void testGetComponentsListCopy() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID00", "wood");
		Component component2 = new Component("ID01", "wool");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		//Act
		Product product = new Product("ID0005", "bed", components);
		int listOfComponentsSize = product.getNecessaryComponentsCopy().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponentsCopy().containsKey("ID00")),
				() -> assertTrue(product.getNecessaryComponentsCopy().containsKey("ID01")));
	}

}
