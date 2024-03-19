package edu.westga.cs3212.inventory_manager.test.serverorderinventory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server_impl.OrderInventory;

class TestGetOrder {

    @Test
    void testWhenOrderIDIsNull() {
	assertThrows(IllegalArgumentException.class, () -> {
	    OrderInventory.getOrder(null);
	});
    }

    @Test
    void testWhenOrderIDIsBlank() {
	assertThrows(IllegalArgumentException.class, () -> {
	    OrderInventory.getOrder("");
	});
    }

}
