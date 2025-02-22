/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Data.DAO.FeastMenuDAO;
import Data.DAO.OrderDAO;
import Data.Entity.FeastMenues;
import Data.Entity.Order;
import Validator.CustomerValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 *
 * @author asus
 */
public class OrderService {

    private final OrderDAO orderDAO;
    private final FeastMenuDAO feastMenuDAO;
    private final CustomerValidator customerValidator;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.feastMenuDAO = new FeastMenuDAO();
        this.customerValidator = new CustomerValidator();
    }

    // Validate and place new feast order
    public boolean placeFeastOrder(String customerCode, String setMenuCode,
            int numTables, LocalDate eventDate) throws Exception {
        // Validate customer code
        if (!customerValidator.validCustomerCode(customerCode)) {
            throw new Exception("Invalid customer code");
        }

        // Validate set menu code
        if (!isValidSetMenuCode(setMenuCode)) {
            throw new Exception("Invalid set menu code");
        }

        // Validate number of tables
        if (numTables <= 0) {
            throw new Exception("Number of tables must be greater than zero");
        }

        // Validate event date
        if (eventDate.isBefore(LocalDate.now())) {
            throw new Exception("Event date must be in the future");
        }

        // Check for duplicate order
        if (orderDAO.isDuplicateOrder(customerCode, setMenuCode, eventDate)) {
            throw new Exception("Duplicate data!");
        }

        // Create and save new order
        Order order = new Order();
        order.setCustomerCode(customerCode);
        order.setSetMenuCode(setMenuCode);
        order.setNumberOfTables(numTables);
        order.setEventDate(eventDate);

        // Generate unique order code
        String orderCode = generateOrderCode();
        order.setOrderCode(orderCode);

        // Calculate total cost
        double totalCost = calculateTotalCost(setMenuCode, numTables);
        order.setTotalCost(totalCost);

        return orderDAO.addNew(order);
    }
    
    // Update existing order information
    public boolean updateOrder(String orderId, String setMenuCode,
            int numTables, LocalDate eventDate) throws Exception {
        // Get existing order
        Order order = orderDAO.getOrderById(orderId);

        if (order == null) {
            return false;
        }

        // Check if event date has passed
        if (order.getEventDate().isBefore(LocalDate.now())) {
            throw new Exception("Cannot update order for past events");
        }

        // Update fields if provided
        if (!setMenuCode.isEmpty() && isValidSetMenuCode(setMenuCode)) {
            order.setSetMenuCode(setMenuCode);
        }

        if (numTables > 0) {
            order.setNumberOfTables(numTables);
        }

        if (eventDate != null) {
            if (eventDate.isBefore(LocalDate.now())) {
                throw new Exception("Event date must be in the future");
            }
            order.setEventDate(eventDate);
        }

        // Recalculate total cost
        double totalCost = calculateTotalCost(order.getSetMenuCode(), order.getNumberOfTables());
        order.setTotalCost(totalCost);

        return orderDAO.updateOrder(order);
    }

    // Check if order exists
    public boolean orderExists(String orderId) {
        return orderDAO.getOrderById(orderId) != null;
    }

    // Display order details
    public void displayOrderDetails() {
        orderDAO.displayOrderDetail();
    }

    private boolean isValidSetMenuCode(String code) {
        // Add validation logic based on feastMenu.csv
        return code != null && !code.isEmpty() && code.length() == 5;
    }

    public String generateOrderCode() {
        // Implementation for generating unique order code
        Random random = new Random();
        String code = "ORD" + random.nextInt(1000);
        if (checkExistCode(code)) {
            code = "ORD" + random.nextInt(1000);
        }
        return code;
    }

    public boolean checkExistCode(String code) {
        return feastMenuDAO.getFeastMenuesBySetMenuCode(code) != null;
    }

    public double calculateTotalCost(String setMenuCode, int numTables) {
        FeastMenues feastMenues = feastMenuDAO.getFeastMenuesBySetMenuCode(setMenuCode);
        if (feastMenues == null) {
            throw new IllegalArgumentException("Set menu code " + setMenuCode + " not found");
        }
        double sum = feastMenues.getPrice() * numTables;
        return numTables != 0 ? sum : 0.0;
    }

    public void displayAllOrders() {
        orderDAO.displayAllOrders();
    }

    // Add method to get all orders
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
    
    public void saveData()
    {
        orderDAO.saveOrder();
    }
}
