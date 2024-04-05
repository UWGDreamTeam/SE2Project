package edu.westga.cs3212.inventory_manager.test.component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;

class TestHashCode {

/*Hash Code*/
	
	@Test
	void testHashCode() {
		//Arrange
		String id = "1";
		
		int hashCode = "Component".hashCode() + id.hashCode();
		
		Item product = new Component("bow", 20);
		product.setID(id);
		
		//Act
		
		//Assert
		assertEquals(hashCode, product.hashCode());
	}
}
