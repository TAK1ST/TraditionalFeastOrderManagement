package Data.EmployeeDao;

import BussinessLayer.Entity.Employee;
import Data.IUserDao;
import Data.IUserDao;

/**
 *
 * @author SwordLake
 */
public interface IEmployeeDao extends IUserDao<Employee>{   
    void loadDataFromFile() throws Exception ;   
    //--------------------------------------------------  
    //To do here..........
}
