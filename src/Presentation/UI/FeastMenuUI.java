/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.UI;

import Data.DAO.FeastMenuDAO;

/**
 *
 * @author asus
 */
public class FeastMenuUI {
    public void displayFeastMenu() {
        FeastMenuDAO fes = new FeastMenuDAO();
        fes.display();
    }
}
