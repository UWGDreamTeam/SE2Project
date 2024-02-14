package edu.westga.cs3212.inventory_manager.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.Item;

class ComponentTest {

/*Hash Code*/
	
	@Test
	void testHashCode() {
		//Arrange
		int id = "ID321".hashCode();
		int name = "bow".hashCode();
		
		int hashCode = "component".hashCode() + id + name;
		
		Item product = new Component("ID321", "bow");
		
		//Act
		
		//Assert
		assertEquals(hashCode, product.hashCode());
	}
	
	/*Equals*/
	
	@Test
	void testEqualsSelf() {
		//Arrange
		Item product = new Component("ID528", "arrow");
		
		//Act
		
		//Assert
		assertTrue(product.equals(product));
	}
	
	@Test
	void testEqualsWithNull() {
		//Arrange
		Item product = new Component("ID527", "feather");
		
		//Act
		
		//Assert
		assertFalse(product.equals(null));
	}
	
	@Test
	void testEqualsWithDifferentClass() {
		//Arrange
		Item product = new Component("ID523", "furnace");
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
		
		Item product = new Component(id, name);
		Item product1 = new Component(id, name);
		//Act
		
		//Assert
		assertTrue(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassDifferentHashCode() {
		//Arrange
		Item product = new Component("ID526", "grass");
		Item product1 = new Component("ID991", "water");
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}

}
