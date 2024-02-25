package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

class TestEquals {

	@Test
	void testEqualsSelf() {
		//Arrange
		Product product = new Product("ID528", "arrow");
		
		//Act
		
		//Assert
		assertTrue(product.equals(product));
	}
	
	@Test
	void testEqualsWithNull() {
		//Arrange
		Product product = new Product("ID527", "feather");
		
		//Act
		
		//Assert
		assertFalse(product.equals(null));
	}
	
	@Test
	void testEqualsWithDifferentClass() {
		//Arrange
		Product product = new Product("ID523", "furnace");
		Object product1 = new Object();
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassAndHashCode() {
		//Arrange
		String id = "ID529";
		String name = "table";
		
		Product product = new Product(id, name);
		Product product1 = new Product(id, name);
		//Act
		
		//Assert
		assertTrue(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassDifferentHashCode() {
		//Arrange
		Product product = new Product("ID526", "grass");
		Product product1 = new Product("ID991", "water");
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}

}
