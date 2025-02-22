/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.DAO;

import static Constant.DateFormat.formatter;
import Data.Entity.Customer;
import Data.Entity.Order;
import Data.File.FileManagement;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author asus
 */
public class OrderDAO {

    private final String filename = "./src/Data/feast_order_service.dat";
    private final FileManagement<Order> orderManagement;
    private final FeastMenuDAO feastMenuDAO;
    private final CustomerDAO customerDAO;

    public OrderDAO() {
        this.orderManagement = new FileManagement<>(filename);
        this.feastMenuDAO = new FeastMenuDAO();
        this.customerDAO = new CustomerDAO();
    }

    public List<Order> getAllOrders() {
        return orderManagement.getObjects();
    }

    public boolean addNew(Order order) {
        if (order == null) {
            return false;
        } else {
            orderManagement.addObject(order);
            return true;
        }
    }

    public void saveOrder() {
        try {
            orderManagement.writeToFile();
        } catch (Exception e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    public boolean updateOrder(Order order) {
        try {
            List<Order> orders = getAllOrders();
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getOrderCode().equals(order.getOrderCode())) {
                    orders.set(i, order);
                    orderManagement.writeToFile();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage());
            return false;
        }
    }

    public Order getOrderById(String orderId) {
        return getAllOrders().stream()
                .filter(order -> order.getOrderCode().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public boolean isDuplicateOrder(String customerCode, String setMenuCode, LocalDate eventDate) {
        return getAllOrders().stream()
                .anyMatch(order -> order.getCustomerCode().equals(customerCode)
                && order.getSetMenuCode().equals(setMenuCode)
                && order.getEventDate().equals(eventDate));
    }
    
    public void displayOrderDetail() {
        List<Order> orders = getAllOrders();
//        for (Order o : orders)
//        {
//            System.out.println(o);
//        }
        if (orders.isEmpty()) {
            System.out.println("No orders to display");
            return;
        }
        Order order = orders.get(orders.size() - 1);
//        System.out.println(order.getCustomerCode());
        Customer customer = customerDAO.findCustomerByCode(order.getCustomerCode());
//        System.out.println(customer.getCustomerName());
        System.out.println("----------------------------------------------------------------");
        System.out.println("Customer order information [Order ID: " + order.getOrderCode() + "]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Code          : " + order.getCustomerCode());
        System.out.println("Customer name : " + customer.getCustomerName());
        System.out.println("Phone number  : " + customer.getPhoneNumber());
        System.out.println("Email         : " + customer.getEmail());
        System.out.println("----------------------------------------------------------------");
        System.out.println("Code of Set Menu: " + order.getSetMenuCode());

        feastMenuDAO.displayDetail(order.getSetMenuCode());
        System.out.println("Event date      : " + order.getEventDate().format(formatter));
        System.out.println("Number of tables: " + order.getNumberOfTables());
        System.out.println("Total cost      : $" + String.format("%.2f", order.getTotalCost()));
        System.out.println("----------------------------------------------------------------");
    }

    public void displayAllOrders() {
        List<Order> orders = getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found");
            return;
        }

        System.out.println("\n================ ALL ORDERS ================");
        for (Order order : orders) {
            Customer customer = customerDAO.findCustomerByCode(order.getCustomerCode());
            System.out.println("Order ID: " + order.getOrderCode());
            System.out.println("Customer: " + customer.getCustomerName());
            System.out.println("Set Menu: " + order.getSetMenuCode());
            System.out.println("Event Date: " + order.getEventDate().format(formatter));
            System.out.println("Tables: " + order.getNumberOfTables());
            System.out.println("Total Cost: $" + String.format("%.2f", order.getTotalCost()));
            System.out.println("-------------------------------------------");
        }
    }
}
