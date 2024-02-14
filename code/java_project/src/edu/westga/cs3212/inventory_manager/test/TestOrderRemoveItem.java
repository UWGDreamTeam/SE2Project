package edu.westga.cs3212.inventory_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

class TestOrderRemoveItem {

    @Test
    void testRemoveItemValidInput() {
        // Arrange 
        Order order = new Order();
        Product product = new Product();
        int quantity = 2;
        order.addItem(product, quantity);

        // Act
        order.removeItem(product, 1);

        // Assert
        assertEquals(1, order.getItems().size());
        assertEquals(1, order.getItems().get(product));
    }

    @Test
    void testRemoveItemNullProduct() {
        // Arrange
        Order order = new Order();
        Product product = null;
        int quantity = 2;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            order.removeItem(product, quantity);
        });
    }

    @Test
    void testRemoveItemQuantityIsZero() {
        // Arrange
        Order order = new Order();
        Product product = new Product();
        int quantity = 0;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            order.removeItem(product, quantity);
        });
    }

    @Test
    void testRemoveItemQuantityIsLessThanZero() {
        // Arrange
        Order order = new Order();
        Product product = new Product();
        int quantity = -1;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            order.removeItem(product, quantity);
        });
    }

    @Test
    void testRemoveItemProductNotFound() {
        // Arrange
        Order order = new Order();
        Product product = new Product();
        int quantity = 1;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            order.removeItem(product, quantity);
        });
    }

    @Test
    void testRemoveItemQuantityGreaterThanOrderQuantity() {
        // Arrange
        Order order = new Order();
        Product product = new Product();
        int quantity = 2;
        order.addItem(product, 1);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            order.removeItem(product, quantity);
        });
    }
    
    @Test
    void testRemoveItemQuantityEqualsCurrentQuantity() {
        // Arrange 
        Order order = new Order();
        Product product = new Product();
        int quantity = 2;
        order.addItem(product, quantity);

        // Act
        order.removeItem(product, quantity);

        // Assert
        assertEquals(0, order.getItems().size());
        assertFalse(order.getItems().containsKey(product));
    }

}

