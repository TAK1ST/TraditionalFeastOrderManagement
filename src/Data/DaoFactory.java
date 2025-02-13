package Data;
 // Data access object factory
 // ** Factory Patte
import Data.EmployeeDao.IEmployeeDao;
import Data.EmployeeDao.EmployeeDao;
import Data.CustomerDao.ICustomerDao;
import Data.CustomerDao.CustomerDao;

public class DaoFactory implements IDaoFactory{
   
    IFileManager fileManager;

    public DaoFactory() {
    }    
    
    public DaoFactory(String inputDataFile) {       
        this.fileManager = new FileManager(inputDataFile);
    }
    
    @Override
    public ICustomerDao customerDao() throws Exception{
        return new CustomerDao(fileManager);
    }

    @Override
    public IEmployeeDao employeeDao() throws Exception{
        return new EmployeeDao(fileManager);
    }

  
}
