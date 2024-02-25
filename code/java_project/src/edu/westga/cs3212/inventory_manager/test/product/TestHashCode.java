package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

class TestHashCode {

	@Test
	void testHashCode() {
		//Arrange
		int id = "ID321".hashCode();
		int name = "bow".hashCode();
		
		int hashCode = "product".hashCode() + id + name;
		
		Product product = new Product("ID321", "bow");
		
		//Act
		
		//Assert
		assertEquals(hashCode, product.hashCode());
	}

}
