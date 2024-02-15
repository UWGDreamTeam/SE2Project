package edu.westga.cs3212.inventory_manager.test.localemployeecredentials;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
class TestConstructor {

	@Test
    void testConstructorShouldCreateObjectWithValidParameters() {
	    LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("123", "John", "Doe", "password123", EmployeeType.WORKER);
	    assertNotNull(credentials);
	    assertEquals("123", credentials.getEmployeeID());
	    assertEquals("John", credentials.getFirstName());
	    assertEquals("Doe", credentials.getLastName());
	    assertEquals("password123", credentials.getPassword());
	    assertEquals(EmployeeType.WORKER, credentials.getEmployeeType());
    }
	
	@Test
    void testSetFirstNameShouldUpdateFirstName() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("123", "John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setFirstName("Jane");
        assertEquals("Jane", credentials.getFirstName());
    }
	
	@Test
    void testSetLastNameShouldUpdateLastName() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("123", "John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setLastName("Smith");
        assertEquals("Smith", credentials.getLastName());
    }
	
	@Test
    void testSetPasswordShouldUpdatePassword() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("123", "John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setPassword("newpassword456");
        assertEquals("newpassword456", credentials.getPassword());
    }
	
	@Test
    void testSetEmployeeTypeShouldUpdateEmployeeType() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("123", "John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setEmployeeType(EmployeeType.MANAGER);
        assertEquals(EmployeeType.MANAGER, credentials.getEmployeeType());
    }

	@Test
	void testConstructorShouldThrowExceptionIfEmployeeIDIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials(null, "John", "Doe", "password123", EmployeeType.WORKER);
        });
	}
	
	@Test
	void testConstructorShouldThrowExceptionIfEmployeeIDIsEmpty() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials("", "John", "Doe", "password123", EmployeeType.WORKER);
		});
	}
	
    @Test
    void testConstructorShouldThrowExceptionIfFirstNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123", null, "Doe", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfLastNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123", "John", null, "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfPasswordIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123","John", "Doe", null, EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfEmployeeTypeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123","John", "Doe", "password123", null);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfFirstNameIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123", "", "Doe", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfLastNameIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123", "John", "", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfPasswordIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123","John", "Doe", "", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfFirstNameIsWhitespace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123", "   ", "Doe", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfLastNameIsWhitespace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123", "John", "   ", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfPasswordIsWhitespace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("123","John", "Doe", "   ", EmployeeType.WORKER);
        });
    }

}

