/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Data.CustomerDao.CustomerDAO;
import Data.Entity.Customer;
import Presentation.UI.CustomerUI;
import static Util.StartUtils.start;

/**
 *
 * @author asus
 */

public class Program {

    public static void main(String[] args) throws Exception 
    {
        start();
        CustomerDAO customerDAO = new CustomerDAO();
        System.out.println(customerDAO.getAllCustomers());
    }
}
