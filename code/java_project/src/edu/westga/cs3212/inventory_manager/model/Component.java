package edu.westga.cs3212.inventory_manager.model;

/**
 * The Class Component.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class Component extends Item {

	/**
	 * Instantiates a new raw material.
	 *
	 * @param name the name
	 * @param productionCost the production cost of this component
	 */
	public Component(String name, double productionCost) {
		super(name, productionCost);
	}

	@Override
	public int hashCode() {
		return "Component".hashCode() + this.getID().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Item other = (Item) obj;

		return this.hashCode() == other.hashCode();
	}

	@Override
	public String toString() {
		return "Component{" + "id=" + this.getID() + ", name='" + this.getName() + '\'' + ", productionCost="
				+ this.getProductionCost() + '}';
	}

}
