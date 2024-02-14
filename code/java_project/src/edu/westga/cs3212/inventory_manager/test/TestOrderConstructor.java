package edu.westga.cs3212.inventory_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

public class TestOrderConstructor {

	@Test
	public void testOrderConstructor() {
		// Arrange
		Order order = new Order();
		
		// Act and Assert 
		assertNotNull(order.getId());
        assertNotNull(order.getDateCreated());
        assertNotNull(order.getItems());
        assertEquals(LocalDateTime.now().getDayOfYear(), order.getDateCreated().getDayOfYear());
        assertEquals(0, order.getItems().size());
	}
	
	@Test
	public void testOrderEqualsIdenticalObject() {
		// Arrange
        Order order1 = new Order();
        Order order2 = order1;
        
        // Act and Assert 
        assertTrue(order1.equals(order2));
        assertEquals(order1.getId(), order2.getId());
        assertEquals(order1.getDateCreated(), order2.getDateCreated());
        assertEquals(order1.getItems(), order2.getItems());
        }
	
	@Test
	public void testOrderEqualsDifferentObjectSameType() {
		// Arrange
        Order order1 = new Order();
        Order order2 = new Order();
        
        // Act and Assert 
        assertFalse(order1.equals(order2));
	}
	
	@Test
	public void testOrderEqualsDifferentObjectDifferentType() {
		// Arrange
        Order order1 = new Order();
        Product product1 = new Product();
      
        // Act and Assert 
        assertFalse(order1.equals(product1));
	}
	
	@Test
	public void testOrderEqualsNull() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertFalse(order.equals(null));
	}
	
	@Test
	public void testOrderHashCode() {
		// Arrange
		Order order = new Order();
		int idHashCode = order.getId().hashCode();
		int dateCreatedHashCode = order.getDateCreated().hashCode();

		// Act and Assert
		assertTrue(order.hashCode() == idHashCode + dateCreatedHashCode);
	}
}
