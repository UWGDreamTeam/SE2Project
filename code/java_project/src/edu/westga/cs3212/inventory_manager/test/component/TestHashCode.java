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
		int id = "ID321".hashCode();
		int name = "bow".hashCode();
		
		int hashCode = "component".hashCode() + id + name;
		
		Item product = new Component("ID321", "bow");
		
		//Act
		
		//Assert
		assertEquals(hashCode, product.hashCode());
	}
}
