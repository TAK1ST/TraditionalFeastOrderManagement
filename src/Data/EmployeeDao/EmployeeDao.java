package Data.EmployeeDao;

import BussinessLayer.Entity.Employee;
import Data.IFileManager;
import Data.IFileManager;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class EmployeeDao implements IEmployeeDao {

    IFileManager<Employee> fileManager;
    //Declare an arraylist to store users Employees  
    List<Employee> employeeList = new ArrayList<>();

    public EmployeeDao() {
    }

    //--------------------------------------------------
    public EmployeeDao(IFileManager fileManager) throws Exception {
        this.fileManager = fileManager;
        loadDataFromFile();
    }
    //--------------------------------------------------

    @Override
    public void loadDataFromFile() throws Exception {
        String id, name, email;
        List<String> empData = fileManager.readDataFromFile();
        for (String e : empData) {
            List<String> emp = Arrays.asList(e.split(","));
            id = emp.get(0).trim();
            name = emp.get(1).trim();
            email = emp.get(2).trim();
            Employee newEmp = new Employee(id, name, email);
            addNew(newEmp);
        }
    }
    //--------------------------------------------------

    @Override
    public List<Employee> getList() throws Exception {
        if (employeeList.isEmpty()) {
            throw new Exception("Employee list is empty.");
        }
        return employeeList;
    }
    //--------------------------------------------------

    @Override
    public void addNew(Employee employee) throws Exception {
        employeeList.add(employee);
    }
    //--------------------------------------------------  
    //To do here..........
}
