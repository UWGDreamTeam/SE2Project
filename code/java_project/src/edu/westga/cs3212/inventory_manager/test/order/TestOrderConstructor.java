package edu.westga.cs3212.inventory_manager.test.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;

public class TestOrderConstructor {

	@Test
	public void testOrderConstructor() {
		// Arrange
		Order order = new Order();
		
		// Act and Assert 
		assertNotNull(order.getID());
        assertNotNull(order.getItems());
        assertEquals(CompletionStatus.INCOMPLETE, order.getCompletionStatus());
        assertEquals(0, order.getItems().size());
	}
	
	@Test
	public void testOrderHashCode() {
		// Arrange
		Order order = new Order();
		int idHashCode = order.getID().hashCode();

		// Act and Assert
		assertTrue(order.hashCode() == "Order".hashCode() + idHashCode);
	}
	
	@Test
	void testOrderSetCompleteInvalidStatus() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			order.setCompletionStatus(null);
		});
	}
	
	@Test
	void testOrderSetNullID() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			order.setID(null);
		});
	}
	
	@Test
	void testOrderSetEmptyID() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			order.setID("");
		});
	}
	
	@Test
	public void testOrderSetComplete() {
		// Arrange
        Order order = new Order();
        
        // Act and Assert
        assertEquals(CompletionStatus.INCOMPLETE, order.getCompletionStatus());
        order.setCompletionStatus(CompletionStatus.COMPLETE);
        assertEquals(CompletionStatus.COMPLETE, order.getCompletionStatus());
	}
	
	@Test
	public void testOrderSetIncomplete() {
		// Arrange
        Order order = new Order();
        order.setCompletionStatus(CompletionStatus.COMPLETE);
        
        // Act and Assert
        assertEquals(CompletionStatus.COMPLETE, order.getCompletionStatus());
        order.setCompletionStatus(CompletionStatus.INCOMPLETE);
        assertEquals(CompletionStatus.INCOMPLETE, order.getCompletionStatus());
	}
	
}
