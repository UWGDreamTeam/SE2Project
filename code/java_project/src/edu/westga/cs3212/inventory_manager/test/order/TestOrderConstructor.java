package edu.westga.cs3212.inventory_manager.test.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
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
        assertEquals(CompletionStatus.INCOMPLETE, order.getCompletionStatus());
        assertEquals(LocalDateTime.now().getDayOfYear(), order.getDateCreated().getDayOfYear());
        assertEquals(0, order.getItems().size());
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
