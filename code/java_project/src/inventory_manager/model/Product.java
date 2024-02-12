package inventory_manager.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class Product.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class Product extends Item {

	/** The components. */
	private Map<String, Integer> components;
	
	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param name the name
	 * 
	 */
	Product(String id, String name) {
		super(id, name);
		
		this.components = new HashMap<String, Integer>();
	}
	
	
	/**
	 * Gets the components components
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the list of components and quantities
	 */
	public Map<String, Integer> getComponentsList() {
		return new HashMap<String, Integer>(this.components);
	}
	
	@Override
	public int hashCode() {
		int result = "product".hashCode();
		
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
