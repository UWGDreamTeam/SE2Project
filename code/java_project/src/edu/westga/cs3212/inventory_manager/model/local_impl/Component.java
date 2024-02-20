package edu.westga.cs3212.inventory_manager.model.local_impl;

import edu.westga.cs3212.inventory_manager.model.Item;

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
	 * @param id the id
	 * @param name the name
	 */
	public Component(String id, String name) {
		super(id, name);
	}
	
	@Override
	public int hashCode() {
		int result = "component".hashCode();
		
		result += this.getId().hashCode();
		result += this.getName().hashCode();
		
		return result;
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
	
}