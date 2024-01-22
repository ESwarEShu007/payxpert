package dao;
import java.util.*;
import entity.*;
import java.sql.*;
import java.sql.Date;
public class EmployeeServiceImp implements EmployeeService {
    private Connection connection;
	private Employee employee;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        
        try {            
            String sqlQuery = "SELECT * FROM Employee WHERE employeeID = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, employeeId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int fetchedEmployeeId = resultSet.getInt("employeeID");
                        String employeeName = resultSet.getString("FirstName");
String employeelastname=resultSet.getString("LastName");
System.out.println(fetchedEmployeeId);
System.out.println(fetchedEmployeeId+ " "+employeeName+" "+employeelastname);

                    }
                    else {
                        System.out.println("Employee with ID " + employeeId + " does not exist.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return employee;
    }
    

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try {
            String sqlQuery = "SELECT * FROM Employee";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("employeeID");
                    String employeeName = resultSet.getString("FirstName");
                    String employeelastname=resultSet.getString("LastName");

                    System.out.println(employeeId +" "+ employeeName +" "+employeelastname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        return employees;
    }
    

    @Override
    public void addEmployee(Employee employee) {
    	try {
            // Assuming you have a method to check if the employee already exists

            String sqlQuery = "INSERT INTO Employee (EmployeeID, FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE EmployeeID=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, (int)employee.getEmployeeID());
                preparedStatement.setString(2, employee.getFirstName());
                preparedStatement.setString(3, employee.getLastName());
                preparedStatement.setString(4, employee.getDateOfBirth());
                preparedStatement.setString(5, employee.getGender());
                preparedStatement.setString(6, employee.getEmail());
                preparedStatement.setString(7, employee.getPhoneNumber());
                preparedStatement.setString(8, employee.getAddress());
                preparedStatement.setString(9, employee.getPosition());
                preparedStatement.setString(10, employee.getJoiningDate());
                preparedStatement.setString(11, employee.getTerminationDate());

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Employee added successfully.");
                } else {
                    System.out.println("Failed to add employee. Please check your inputs and try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
            }
    
    

    @Override
    public void updateEmployee(Employee employee) {
//    	try {
//    		String sqlQuery = "UPDATE Employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Email = ?, PhoneNumber = ?, Address = ?, Position = ?, JoiningDate = ?, TerminationDate = ? WHERE EmployeeID= ?";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
//                System.out.println("Updating employee with ID: " + employee.getEmployeeID());
//                
//                preparedStatement.setString(1, employee.getFirstName());
//                preparedStatement.setString(2, employee.getLastName());
//                preparedStatement.setString(3, employee.getDateOfBirth());
//                preparedStatement.setString(4, employee.getGender());
//                preparedStatement.setString(5, employee.getEmail());
//                preparedStatement.setString(6, employee.getPhoneNumber());
//                preparedStatement.setString(7, employee.getAddress());
//                preparedStatement.setString(8, employee.getPosition());
//                preparedStatement.setString(9, employee.getJoiningDate());
//                preparedStatement.setString(10, employee.getTerminationDate());
//                
//                // Set the EmployeeID in the WHERE clause
//               preparedStatement.setInt(11, employee.getEmployeeID());
//
//                System.out.println("SQL Query: " + sqlQuery); // Print the SQL query
//
//                // Execute the update query
//                int rowsAffected = preparedStatement.executeUpdate();
//
//                if (rowsAffected > 0) {
//                    System.out.println("Employee updated successfully.");
//                } else {
//                    System.out.println("No employee with the specified ID found.");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception as needed
        //}        
    	}
      

    @Override
    public void removeEmployee(int employeeId) {
        String sql = "DELETE FROM Employee WHERE EmployeeID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, employeeId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Employee removed successfully!");
            } else {
                System.out.println("No employee found with the given EmployeeID.");
            }
        }
    catch (SQLException e) {
        e.printStackTrace();
    }
    }
    }


