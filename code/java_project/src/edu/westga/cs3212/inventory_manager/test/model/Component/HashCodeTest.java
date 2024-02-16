package edu.westga.cs3212.inventory_manager.test.model.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.Item;

class HashCodeTest {

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
