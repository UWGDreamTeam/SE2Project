package edu.westga.cs3212.inventory_manager.test.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;

class TestHashCode {

/*Hash Code*/
	
	@Test
	void testHashCode() {
		//Arrange
		int name = "bow".hashCode();
		
		int hashCode = "component".hashCode() + name;
		
		Item product = new Component("bow");
		
		//Act
		
		//Assert
		assertEquals(hashCode, product.hashCode());
	}
}
