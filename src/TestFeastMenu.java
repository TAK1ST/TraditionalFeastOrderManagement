//import Data.DAO.FeastMenuDAO;
//import Data.Entity.FeastMenues;
//
//public class TestFeastMenu {
//    public static void main(String[] args) {
//        // Create instance of FeastMenuDAO
//        FeastMenuDAO feastMenuDAO = new FeastMenuDAO();
//        
//        // First display all menus to load data
//        System.out.println("Loading and displaying all menus:");
//        feastMenuDAO.display();
//        
//        System.out.println("\n---------- Testing getFeastMenuesBySetMenuCode ----------");
//        
//        // Test with code PW001
//        String testCode = "PW001";
//        System.out.println("\nTesting with code: " + testCode);
//        FeastMenues menu1 = feastMenuDAO.getFeastMenuesBySetMenuCode(testCode);
//        if (menu1 != null) {
//            System.out.println("Found menu:");
//            menu1.display();
//        } else {
//            System.out.println("No menu found with code: " + testCode);
//        }
//        
//        // Test with another code
//        testCode = "PW002";
//        System.out.println("\nTesting with code: " + testCode);
//        FeastMenues menu2 = feastMenuDAO.getFeastMenuesBySetMenuCode(testCode);
//        if (menu2 != null) {
//            System.out.println("Found menu:");
//            menu2.display();
//        } else {
//            System.out.println("No menu found with code: " + testCode);
//        }
//        
//        // Test with invalid code
//        testCode = "INVALID";
//        System.out.println("\nTesting with invalid code: " + testCode);
//        FeastMenues menu3 = feastMenuDAO.getFeastMenuesBySetMenuCode(testCode);
//        if (menu3 != null) {
//            System.out.println("Found menu:");
//            menu3.display();
//        } else {
//            System.out.println("No menu found with code: " + testCode);
//        }
//    }
//}