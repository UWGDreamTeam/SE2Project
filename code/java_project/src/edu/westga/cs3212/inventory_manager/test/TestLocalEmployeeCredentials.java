package edu.westga.cs3212.inventory_manager.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials.EmployeeType;

class TestLocalEmployeeCredentials {

	@Test
    void testConstructorShouldCreateObjectWithValidParameters() {
        LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("John", "Doe", "password123", EmployeeType.WORKER);
        assertNotNull(credentials);
        assertEquals("John", credentials.getFirstName());
        assertEquals("Doe", credentials.getLastName());
        assertEquals("password123", credentials.getPassword());
        assertEquals(EmployeeType.WORKER, credentials.getEmployeeType());
        assertNotNull(credentials.getEmployeeID());
    }
	
	@Test
    void testSetFirstNameShouldUpdateFirstName() {
        LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setFirstName("Jane");
        assertEquals("Jane", credentials.getFirstName());
    }
	
	@Test
    void testSetLastNameShouldUpdateLastName() {
        LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setLastName("Smith");
        assertEquals("Smith", credentials.getLastName());
    }
	
	@Test
    void testSetPasswordShouldUpdatePassword() {
        LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setPassword("newpassword456");
        assertEquals("newpassword456", credentials.getPassword());
    }
	
	@Test
    void testSetEmployeeTypeShouldUpdateEmployeeType() {
        LocalEmployeeCredentials credentials = new LocalEmployeeCredentials("John", "Doe", "password123", EmployeeType.WORKER);
        credentials.setEmployeeType(EmployeeType.MANAGER);
        assertEquals(EmployeeType.MANAGER, credentials.getEmployeeType());
    }

    @Test
    void testConstructorShouldThrowExceptionIfFirstNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials(null, "Doe", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfLastNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("John", null, "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfPasswordIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("John", "Doe", null, EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfEmployeeTypeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("John", "Doe", "password123", null);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfFirstNameIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("", "Doe", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfLastNameIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("John", "", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfPasswordIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("John", "Doe", "", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfFirstNameIsWhitespace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("   ", "Doe", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfLastNameIsWhitespace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("John", "   ", "password123", EmployeeType.WORKER);
        });
    }

    @Test
    void testConstructorShouldThrowExceptionIfPasswordIsWhitespace() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LocalEmployeeCredentials("John", "Doe", "   ", EmployeeType.WORKER);
        });
    }

}

