package inventory_manager.model;

/**
 * The Class RawMaterial.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class RawMaterial extends Item {

	
	/**
	 * Instantiates a new raw material.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public RawMaterial(String id, String name) {
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
