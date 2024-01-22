package dao;
import java.util.*;
import entity.*;
import exception.*;
public interface EmployeeService {
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void removeEmployee(int employeeId);

}
