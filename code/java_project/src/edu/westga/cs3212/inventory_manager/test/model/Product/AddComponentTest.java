package edu.westga.cs3212.inventory_manager.test.model.Product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

class AddComponentTest {

	@Test
	void testAddNullComponent() {
		//Arrange
		Product product = new Product("ID056", "wood door");
		
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.addComponent(null, 1),
			"Add component with null compoenent should throw IAE");
	}
	
	@Test
	void testAddQuantityEqual0() {
		//Arrange
		Product product = new Product("ID015", "glass door");
		
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.addComponent(new Component("ID123", "redstone"), 0),
			"Add component with null compoenent should throw IAE");
	}
	
	@Test
	void testAddQuantityLessThan0() {
		//Arrange
		Product product = new Product("ID123", "stone");
		
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.addComponent(new Component("ID23", "wings"), -1),
			"Add component with null compoenent should throw IAE");
	}
	
	@Test
	void testAddComponentWithMatching() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID52", "wood window");
		
		components.put(component1.getId(), 3);
		
		Product product = new Product("ID054", "product54", components);
		
		//Act
		
		//Assert
		assertFalse(product.addComponent(component1, 2));
	}
	
	@Test
	void testAddComponentWithNoMatching() {
		//Arrange
		Product product = new Product("ID008", "metal door");
		Component component1 = new Component("ID97", "window");
		
		//Act
		
		//Assert
		assertTrue(product.addComponent(component1, 2));
	}

}
