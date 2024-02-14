package edu.westga.cs3212.inventory_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Order;

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
}
