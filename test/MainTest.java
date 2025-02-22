/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asus
 */


import Data.DAO.CustomerDAO;
import Data.DAO.FeastMenuDAO;
import Data.DAO.OrderDAO;
import Data.Entity.Customer;
import Data.Entity.FeastMenues;
import Data.Entity.Order;
import Validator.CustomerValidator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.List;

public class MainTest {
    private CustomerDAO customerDAO;
    private FeastMenuDAO feastMenuDAO;
    private OrderDAO orderDAO;
    private CustomerValidator customerValidator;

    @Before
    public void setUp() {
        customerDAO = new CustomerDAO();
        feastMenuDAO = new FeastMenuDAO();
        orderDAO = new OrderDAO();
        customerValidator = new CustomerValidator();
    }

    @Test
    public void testCustomerCodeValidation() {
        // Valid codes
        assertTrue(customerValidator.validCustomerCode("C001"));
        assertTrue(customerValidator.validCustomerCode("G999"));
        assertTrue(customerValidator.validCustomerCode("K123"));
        
        // Invalid codes
        assertFalse(customerValidator.validCustomerCode("A0001")); // Wrong prefix
        assertFalse(customerValidator.validCustomerCode("C001")); // Too short
        assertFalse(customerValidator.validCustomerCode("C00012")); // Too long
        assertFalse(customerValidator.validCustomerCode("C000A")); // Non-numeric suffix
    }

    @Test
    public void testCustomerNameValidation() {
        assertTrue(customerValidator.validCustomerName("Nguyen Van A"));
        assertTrue(customerValidator.validCustomerName("Le B"));
        
        assertFalse(customerValidator.validCustomerName("")); // Empty
        assertFalse(customerValidator.validCustomerName("A")); // Too short
        assertFalse(customerValidator.validCustomerName("This name is way too long to be valid")); // >25 chars
    }

    @Test
    public void testPhoneNumberValidation() {
        // Valid Vietnamese phone numbers
        assertTrue(customerValidator.validPhone("0987654321")); // Viettel
        assertTrue(customerValidator.validPhone("0901234567")); // Mobifone
        assertTrue(customerValidator.validPhone("0351232321")); // Viettel
        
        // Invalid phone numbers
        assertFalse(customerValidator.validPhone("098765432")); // Too short
        assertFalse(customerValidator.validPhone("09876543210")); // Too long
        assertFalse(customerValidator.validPhone("1987654321")); // Wrong prefix
        assertFalse(customerValidator.validPhone("abc1234567")); // Non-numeric
    }

    @Test
    public void testEmailValidation() {
        // Valid emails
        assertTrue(customerValidator.validEmail("test@gmail.com"));
        assertTrue(customerValidator.validEmail("user.name@company.com"));
        assertTrue(customerValidator.validEmail("user123@domain.vn"));
        
        // Invalid emails
        assertFalse(customerValidator.validEmail("invalid.email")); // No @
        assertFalse(customerValidator.validEmail("@domain.com")); // No local part
        assertFalse(customerValidator.validEmail("user@")); // No domain
    }

    @Test
    public void testOrderPlacement() {
        // Add test customer and menu first
        Customer customer = new Customer("C0123", "Test Customer", "0987654321", "test@email.com");
        customerDAO.addCustomer(customer);
        
        FeastMenues menu = new FeastMenues("PW001", "Test Menu", 2000000,
            "Test Appetizer");
        feastMenuDAO.addFeastMenues(menu);
        
        // Test valid order
        Order order = new Order("ORD123", "C0123", "PW001", 10, LocalDate.now().plusMonths(1));
        assertTrue(orderDAO.addNew(order));
        
        // Test duplicate order
        Order duplicateOrder = new Order("ORD124", "C0123", "PW001", 10, LocalDate.now().plusMonths(1));
        assertFalse(orderDAO.addNew(duplicateOrder));
        
        // Test order with past date
        Order pastOrder = new Order("ORD125", "C0123", "PW001", 10, LocalDate.now().minusDays(1));
        assertFalse(orderDAO.addNew(pastOrder));
    }

    @Test
    public void testOrderTotalCostCalculation() {
        FeastMenues menu = new FeastMenues("PW002", "Test Menu", 2000000,
            "Appetizer");
        feastMenuDAO.addFeastMenues(menu);
        
        Order order = new Order("ORD126", "C0123", "PW002", 5, LocalDate.now().plusMonths(1));
        orderDAO.addNew(order);
        orderDAO.saveOrder();
        // Total cost should be price per table * number of tables
        assertEquals(10000000.0, order.getTotalCost(), 0.01);
    }

    @Test
    public void testCustomerSearch() {
        customerDAO.addCustomer(new Customer("C0001", "An, Hoang Thi To", "0987654321", "an@email.com"));
        customerDAO.saveCustomers();
        customerDAO.addCustomer(new Customer("C0002", "Anh, Nguyen Van", "0987654322", "anh@email.com"));
        customerDAO.saveCustomers();
        // Test partial name search
        List<Customer> results = customerDAO.searchCustomersByName("An");
        assertEquals(2, results.size());
        
        // Test exact name search
        results = (List<Customer>) customerDAO.searchCustomersByName("An, Hoang Thi To");
        assertEquals(1, results.size());
        
        // Test no matches
        results = (List<Customer>) customerDAO.searchCustomersByName("XYZ");
        assertTrue(results.isEmpty());
    }
}