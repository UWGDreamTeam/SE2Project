package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

public class HomePageViewModel {
	
	LocalComponentInventory componentManager;
	LocalProductInventory productManager;
	//TODO add property for orders
	

	public HomePageViewModel() {
		this.componentManager = new LocalComponentInventory();
		this.productManager = new LocalProductInventory();
	}
	
	public void update
}
