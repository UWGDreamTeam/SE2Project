package edu.westga.cs3212.inventory_manager.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.Item;

class ProductTest {

	/* SUPER CONSTRUCTOR */
	@Test
	void testNewProductNullId() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product(null, "product"),
				"Create product with null ID should throw IAE");
	}
	
	@Test
	void testNewProductEmptyId() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("", "product"),
				"Create product with empty ID should throw IAE");
	}
	
	@Test
	void testNewProductBlankId() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product(" ", "product"),
				"Create product with blank ID should throw IAE");
	}
	
	@Test
	void testNewProductNullName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", null),
				"Create product with null ID should throw IAE");
	}
	
	@Test
	void testNewProductEmptyName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", ""),
				"Create product with empty ID should throw IAE");
	}
	
	@Test
	void testNewProductBlankName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", " "),
				"Create product with blank ID should throw IAE");
	}
	
	@Test
	void testNewProductValidParamethers() {
		//Arrange
		
		//Act
		Product product = new Product("ID0000", "Sword");
		
		System.out.println(product.getNecessaryComponentsListCopy());
		
		//Assert
		assertAll(
				() -> assertEquals("ID0000", product.getId()),
				() -> assertEquals("Sword", product.getName()),
				() -> assertEquals(0, product.getNecessaryComponentsListCopy().size())
		);
	
	}
	
	/* CHILD CONSTRUCTOR*/
	
	@Test
	void testNewProductNullComponents() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", "product1", null),
				"Create product with blank ID should throw IAE");
	}
	
	@Test
	void testNewProductEmptyListOfComponents() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Product("ID001", "product1", new HashMap<String, Integer>()),
				"Create product with empty list of components should throw IAE");
	}
	
	@Test
	void testNewProductValidParamethersWithMap() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID001", "iron");
		Component component2 = new Component("ID002", "stick");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		//Act
		Product product = new Product("ID0001", "Pickaxe", components);
		int listOfComponentsSize = product.getNecessaryComponentsListCopy().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID001")),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID002")));
	}
	
	/*GET COMPONENTS LIST COPY*/
	
	@Test
	void testGetComponentsListCopy() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID00", "wood");
		Component component2 = new Component("ID01", "wool");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		//Act
		Product product = new Product("ID0005", "bed", components);
		int listOfComponentsSize = product.getNecessaryComponentsListCopy().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID00")),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID01")));
	}
	
	/*SET COMPONENTS LIST*/
	
	@Test
	void testSetComponentsListNullComponents() {
		//Arrange
		Product product = new Product("ID0006", "shovel");
				
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.setNecessaryComponentsList(null),
			"Set a components with null map should throw IAE");
	}
	
	@Test
	void testSetComponentsListEmptyComponents() {
		//Arrange
		Product product = new Product("ID0007", "line");
				
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.setNecessaryComponentsList(new HashMap<String, Integer>()),
			"Set a components with null map should throw IAE");
	}
	
	@Test
	void testSetComponentsListValidComponentsWithEmptyListInsideProduct() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID99", "glass");
		Component component2 = new Component("ID98", "sand");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		//Act
		Product product = new Product("ID009", "door");
		product.setNecessaryComponentsList(components);
		
		int listOfComponentsSize = product.getNecessaryComponentsListCopy().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID99")),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID98")));
	}
	
	
	@Test
	void testSetComponentsListValidComponentsWithNonEmptyListInsideProduct() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID91", "fence");
		Component component2 = new Component("ID85", "dirt");
		
		components.put(component1.getId(), 3);
		components.put(component2.getId(), 2);
		
		Product product = new Product("ID0008", "dirt door");
		product.addComponent(component1, 2);
		
		//Act
		product.setNecessaryComponentsList(components);
		
		int listOfComponentsSize = product.getNecessaryComponentsListCopy().size();
		
		//Assert
		assertAll(
				() -> assertEquals(2, listOfComponentsSize),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID91")),
				() -> assertTrue(product.getNecessaryComponentsListCopy().containsKey("ID85")));
	}
	
	/*ADD COMPONENT*/
	
	@Test
	void testAddNullComponent() {
		//Arrange
		Product product = new Product("ID056", "wood door");
		
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.addComponent(null, 1),
			"Add component with null compoenent should throw IAE");
	}
	
	@Test
	void testAddQuantityEqual0() {
		//Arrange
		Product product = new Product("ID015", "glass door");
		
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.addComponent(new Component("ID123", "redstone"), 0),
			"Add component with null compoenent should throw IAE");
	}
	
	@Test
	void testAddQuantityLessThan0() {
		//Arrange
		Product product = new Product("ID123", "stone");
		
		//Act
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> 
			product.addComponent(new Component("ID23", "wings"), -1),
			"Add component with null compoenent should throw IAE");
	}
	
	@Test
	void testAddComponentWithMatching() {
		//Arrange
		HashMap<String, Integer> components = new HashMap<String, Integer>();
		
		Component component1 = new Component("ID52", "wood window");
		
		components.put(component1.getId(), 3);
		
		Product product = new Product("ID008", "metal door", components);
		
		//Act
		
		//Assert
		assertFalse(product.addComponent(component1, 2));
	}
	
	@Test
	void testAddComponentWithNoMatching() {
		//Arrange
		Product product = new Product("ID008", "metal door");
		Component component1 = new Component("ID97", "window");
		
		//Act
		
		//Assert
		assertTrue(product.addComponent(component1, 2));
	}
	
	/*Hash Code*/
	
	@Test
	void testHashCode() {
		//Arrange
		int id = "ID321".hashCode();
		int name = "bow".hashCode();
		
		int hashCode = "product".hashCode() + id + name;
		
		Product product = new Product("ID321", "bow");
		
		//Act
		
		//Assert
		assertEquals(hashCode, product.hashCode());
	}
	
	/*Equals*/
	
	@Test
	void testEqualsSelf() {
		//Arrange
		Product product = new Product("ID528", "arrow");
		
		//Act
		
		//Assert
		assertTrue(product.equals(product));
	}
	
	@Test
	void testEqualsWithNull() {
		//Arrange
		Product product = new Product("ID527", "feather");
		
		//Act
		
		//Assert
		assertFalse(product.equals(null));
	}
	
	@Test
	void testEqualsWithDifferentClass() {
		//Arrange
		Product product = new Product("ID523", "furnace");
		Object product1 = new Object();
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassAndHashCode() {
		//Arrange
		String id = "ID529";
		String name = "table";
		
		Product product = new Product(id, name);
		Product product1 = new Product(id, name);
		//Act
		
		//Assert
		assertTrue(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassDifferentHashCode() {
		//Arrange
		Product product = new Product("ID526", "grass");
		Product product1 = new Product("ID991", "water");
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}
	
	
}
