package edu.westga.cs3212.inventory_manager.model.server_impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;

class TESTSEVRVER {

	@Test
	void test() {
		ComponentInventory test = new ComponentInventory();
		String result = test.addComponent("componentName", 5, 10);
	}
}
