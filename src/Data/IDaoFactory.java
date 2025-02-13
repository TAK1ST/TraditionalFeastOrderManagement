package Data;

// abstract factory interface. Creates data access objects.

import Data.EmployeeDao.IEmployeeDao;
import Data.CustomerDao.ICustomerDao;

// ** GoF Design Pattern: Factory.    
public interface IDaoFactory {
    ICustomerDao customerDao() throws Exception;
    IEmployeeDao employeeDao() throws Exception;  
    //.....
}
