package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.chart.XYChart;

public class HomePageViewModel {
	
	LocalComponentInventory componentManager;
	LocalProductInventory productManager;
	//TODO add property for orders
	
	private SimpleListProperty<XYChart.Series<String, Number>> commonComponentsBarChart;
	private SimpleListProperty<XYChart.Series<String, Number>> commonProductBarChart;
	private SimpleListProperty<XYChart.Series<String, Number>> ordersBarChart;
	
	public HomePageViewModel() {
		this.componentManager = new LocalComponentInventory();
		this.productManager = new LocalProductInventory();
		this.commonComponentsBarChart = new SimpleListProperty<XYChart.Series<String, Number>>();
		this.commonProductBarChart = new SimpleListProperty<XYChart.Series<String, Number>>();
		this.ordersBarChart = new SimpleListProperty<XYChart.Series<String, Number>>();
	}

	public SimpleListProperty<XYChart.Series<String, Number>> getCommonComponentsBarChart() {
		return commonComponentsBarChart;
	}

	public SimpleListProperty<XYChart.Series<String, Number>> getCommonProductBarChart() {
		return commonProductBarChart;
	}

	public SimpleListProperty<XYChart.Series<String, Number>> getOrdersBarChart() {
		return ordersBarChart;
	}
	
	private void test() {
		Iterable<Product> products = this.productManager.getProducts();
	}
	
	
}
