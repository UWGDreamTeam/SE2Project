package edu.westga.cs3212.inventory_manager.test.model.Product;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

class TestProductConstrutor {

	/* SUPER CONSTRUCTOR */
	@Test
	void testNewProductNullId() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product(null, "product"),
				"Create product with null ID should throw IAE");
	}
	
	@Test
	void testNewProductEmptyId() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("", "product"),
				"Create product with empty ID should throw IAE");
	}
	
	@Test
	void testNewProductBlankId() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product(" ", "product"),
				"Create product with blank ID should throw IAE");
	}
	
	@Test
	void testNewProductNullName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", null),
				"Create product with null ID should throw IAE");
	}
	
	@Test
	void testNewProductEmptyName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", ""),
				"Create product with empty ID should throw IAE");
	}
	
	@Test
	void testNewProductBlankName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", " "),
				"Create product with blank ID should throw IAE");
	}
	
	@Test
	void testNewProductValidParamethers() {
		//Arrange
		
		//Act
		Product product = new Product("ID0000", "Sword");
		
		//Assert
		assertAll(
				() -> assertEquals("ID0000", product.getId()),
				() -> assertEquals("Sword", product.getName()),
				() -> assertEquals(0, product.getNecessaryComponents().size())
		);
	
	}
	
	/* CHILD CONSTRUCTOR*/
	
	@Test
	void testNewProductNullComponents() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", "product1", null),
				"Create product with blank ID should throw IAE");
	}
	
	@Test
	void testNewProductEmptyListOfComponents() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", "product1", new HashMap<String, Integer>()),
				"Create product with empty list of components should throw IAE");
	}
	
	@Test
	void testNewProductValidParamethersWithMap() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID001", "iron");
		Component component2 = new Component("ID002", "stick");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		//Act
		Product product = new Product("ID0001", "Pickaxe", components);
		int listOfComponentsSize = product.getNecessaryComponents().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponents().containsKey("ID001")),
				() -> assertTrue(product.getNecessaryComponents().containsKey("ID002")));
	}
}
