/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author asus
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private String orderCode;
    private String customerCode;
    private String setMenuCode;
    private int numberOfTables;
    private LocalDate eventDate;
    private double totalCost;

    public Order() {
    }

    public Order(String orderCode, String customerCode, String setMenuCode, int numberOfTables, LocalDate eventDate) {
        this.orderCode = orderCode;
        this.customerCode = customerCode;
        this.setMenuCode = setMenuCode;
        this.numberOfTables = numberOfTables;
        this.eventDate = eventDate;
    }

    public Order(String orderCode, String customerCode, String setMenuCode, int numberOfTables, LocalDate eventDate, int totalCost) {
        this.orderCode = orderCode;
        this.customerCode = customerCode;
        this.setMenuCode = setMenuCode;
        this.numberOfTables = numberOfTables;
        this.eventDate = eventDate;
        this.totalCost = totalCost;
    }

    // Getter và Setter
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getSetMenuCode() {
        return setMenuCode;
    }

    public void setSetMenuCode(String setMenuCode) {
        this.setMenuCode = setMenuCode;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}

//Some way to do with LocalDate
// soft with Comparator (dates, events: List()) - far to close
//        dates.sort(Comparator.naturalOrder()); or
//        events.sort(Comparator.comparing(Event::getDate));
//      Comparator.reverseOrder() (close to far)
//            List<LocalDate> sortedDates = dates.stream()
//                                            .sorted()
//                                            .collect(Collectors.toList());
// result
//        dates.forEach(System.out::println);