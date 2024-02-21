package edu.westga.cs3212.inventory_manager.test.orderpageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.viewmodel.OrderPageViewModel;

class TestOrderPageViewModel {

	private OrderPageViewModel viewModel;

    @BeforeEach
    public void setUp() {
        this.viewModel = new OrderPageViewModel();
    }

    @Test
    public void testGetOpenOrders() {
        List<Order> openOrders = this.viewModel.getOpenOrders();
        assertEquals(2, openOrders.size());
    }

    @Test
    public void testGetClosedOrders() {
        List<Order> closedOrders = this.viewModel.getClosedOrders();
        assertEquals(1, closedOrders.size());
    }
}
