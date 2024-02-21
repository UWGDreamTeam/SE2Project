package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

public class OrderPageViewModel {

	private LocalOrderManager orderManager;
	
	public OrderPageViewModel() {
		this.orderManager = new LocalOrderManager();
		
	}
}
