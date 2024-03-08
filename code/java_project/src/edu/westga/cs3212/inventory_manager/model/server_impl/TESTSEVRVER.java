package edu.westga.cs3212.inventory_manager.model.server_impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;

class TESTSEVRVER {

	@Test
	void test() {
		ComponentInventory test = new ComponentInventory();
		String componentID = test.addComponent("test", 1.0);
		test.removeComponent(componentID);
		
	}

}
