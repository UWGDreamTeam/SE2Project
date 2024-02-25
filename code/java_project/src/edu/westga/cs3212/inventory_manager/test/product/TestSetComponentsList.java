package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

class TestSetComponentsList {

	@Test
	void testSetComponentsListNullComponents() {
		//Arrange
		Product product = new Product("ID0006", "shovel");
				
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.setNecessaryComponentsList(null),
			"Set a components with null map should throw IAE");
	}
	
	@Test
	void testSetComponentsListEmptyComponents() {
		//Arrange
		Product product = new Product("ID0007", "line");
				
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.setNecessaryComponentsList(new HashMap<String, Integer>()),
			"Set a components with null map should throw IAE");
	}
	
	@Test
	void testSetComponentsListValidComponentsWithEmptyListInsideProduct() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID99", "glass");
		Component component2 = new Component("ID98", "sand");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		//Act
		Product product = new Product("ID009", "door");
		product.setNecessaryComponentsList(components);
		
		int listOfComponentsSize = product.getNecessaryComponents().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponents().containsKey("ID99")),
				() -> assertTrue(product.getNecessaryComponents().containsKey("ID98")));
	}
	
	
	@Test
	void testSetComponentsListValidComponentsWithNonEmptyListInsideProduct() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID91", "fence");
		Component component2 = new Component("ID85", "dirt");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		Product product = new Product("ID0008", "dirt door");
		product.addComponent(component1, 2);
		
		//Act
		product.setNecessaryComponentsList(components);
		
		int listOfComponentsSize = product.getNecessaryComponents().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponents().containsKey("ID91")),
				() -> assertTrue(product.getNecessaryComponents().containsKey("ID85")));
	}

}
