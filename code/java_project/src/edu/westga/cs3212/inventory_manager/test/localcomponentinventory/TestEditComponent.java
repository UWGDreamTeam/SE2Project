package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestEditComponent {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
		LocalComponentInventory inventory = new LocalComponentInventory();
		inventory.clear();
	}
	
	@Test
	void testWhenTheItemIsNull() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem(null));
	}
	
	@Test
	void testWhenItemIDAndComponentIDDoNotMatch() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component item = new ComponentTest("test");
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem(item));
	} 
	
	@Test
	void testWhenItemDoesNotExist() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item =  new ComponentTest("test");
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem(item));
	}
	
	@Test
	void testWhenItemExists() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item =  new ComponentTest("test");
		inventory.addNewItem(item);
		
		inventory.editItem(new ComponentTest("test"));
		
		assertEquals("test", inventory.getListOfItems().get(0).getName());
	}
	
	@Test
	void testWhenThereAreMultipleItems() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item =  new ComponentTest("test");
		Item item2 =  new ComponentTest("test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		
		inventory.editItem(new ComponentTest("test"));
		
		assertEquals("test", inventory.getItemByID("test").getName());
	}
	
	@Test
	void testWhenItemIsFirstItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item =  new ComponentTest("test");
		Item item2 =  new ComponentTest("test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		
		inventory.editItem(new ComponentTest("test"));
		
		assertEquals("test", inventory.getItemByID("test").getName());
	}
	
	@Test
	void testWhenItemIsLastItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item =  new ComponentTest("test");
		Item item2 =  new ComponentTest("test2");
		
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		
		Item newComponent = new ComponentTest("test");
		
		inventory.editItem(newComponent);
		
		assertEquals("test", inventory.getItemByID("test").getName());
	}
	
	private final class ComponentTest extends Component {
		protected ComponentTest(String name) {
			super(name);
		}

		@Override
		public String generateID() {
			return this.getName();
		}
	}

}
