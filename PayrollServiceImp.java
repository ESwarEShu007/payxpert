package dao;
import java.util.*;
import java.sql.*;
import entity.*;
import java.time.LocalDate;

public class PayrollServiceImp implements PayrollServiceInt {
	
	private Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

	@Override
    public Payroll getPayrollById(int payrollId) {
		Payroll payroll=null;
        String sql = "SELECT * FROM Payroll WHERE PayrollID = ?";
try {
        // Create a PreparedStatement with the SQL query
	PreparedStatement     preparedStatement = connection.prepareStatement(sql);

        // Set the parameter for the query
        preparedStatement.setInt(1, payrollId);

        // Execute the query and get the result set
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if a payroll record was found
        if (resultSet.next()) {
            // Create a Payroll object and populate its attributes from the result set
        	  payroll = new Payroll();
            payroll.setPayrollID(resultSet.getInt("PayrollID"));
            payroll.setEmployeeID(resultSet.getInt("EmployeeID"));
            payroll.setPayPeriodStartDate(resultSet.getDate("PayPeriodStartDate"));
            payroll.setPayPeriodEndDate(resultSet.getDate("PayPeriodEndDate"));
            payroll.setBasicSalary(resultSet.getDouble("BasicSalary"));
            payroll.setOvertimePay(resultSet.getDouble("OvertimePay"));
            payroll.setDeductions(resultSet.getDouble("Deductions"));
            payroll.setNetSalary(resultSet.getDouble("NetSalary"));
        }
    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();
    } 
    return payroll;

    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrolls = new ArrayList<>();
		Payroll payroll=null;


        try {
            String sql = "SELECT * FROM Payroll WHERE EmployeeID = ?";

            // Create a PreparedStatement with the SQL query
            PreparedStatement  preparedStatement = connection.prepareStatement(sql);

            // Set the parameter for the query
            preparedStatement.setInt(1, employeeId);

            // Execute the query and get the result set
            ResultSet    resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Payroll objects
            while (resultSet.next()) {
                 payroll = new Payroll();
                payroll.setPayrollID(resultSet.getInt("PayrollID"));
                payroll.setEmployeeID(resultSet.getInt("EmployeeID"));
                payroll.setPayPeriodStartDate(resultSet.getDate("PayPeriodStartDate"));
                payroll.setPayPeriodEndDate(resultSet.getDate("PayPeriodEndDate"));
                payroll.setBasicSalary(resultSet.getDouble("BasicSalary"));
                payroll.setOvertimePay(resultSet.getDouble("OvertimePay"));
                payroll.setDeductions(resultSet.getDouble("Deductions"));
                payroll.setNetSalary(resultSet.getDouble("NetSalary"));

                // Add the payroll record to the list
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            // Handle database-related exceptions
            e.printStackTrace();
        } 

        // Return an empty list instead of null if no records are found
        return payrolls.isEmpty() ? Collections.emptyList() : payrolls;
    }
    

    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";
try {
        // Create a PreparedStatement with the SQL query
	PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // Set the parameters for the query
        preparedStatement.setDate(1, java.sql.Date.valueOf(startDate));
        preparedStatement.setDate(2, java.sql.Date.valueOf(endDate));

        // Execute the query and get the result set
        ResultSet  resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create Payroll objects
        while (resultSet.next()) {
            Payroll payroll = new Payroll();
            payroll.setPayrollID(resultSet.getInt("PayrollID"));
            payroll.setEmployeeID(resultSet.getInt("EmployeeID"));
            payroll.setPayPeriodStartDate(resultSet.getDate("PayPeriodStartDate"));
            payroll.setPayPeriodEndDate(resultSet.getDate("PayPeriodEndDate"));
            payroll.setBasicSalary(resultSet.getDouble("BasicSalary"));
            payroll.setOvertimePay(resultSet.getDouble("OvertimePay"));
            payroll.setDeductions(resultSet.getDouble("Deductions"));
            payroll.setNetSalary(resultSet.getDouble("NetSalary"));

            // Add the payroll record to the list
            payrolls.add(payroll);
        }
    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();
    } 

    // Return an empty list instead of null if no records are found
    return payrolls.isEmpty() ? Collections.emptyList() : payrolls;
}
    

    
    public boolean generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) {
        Double basicSalary = Double.valueOf(5000.00);  // Replace with your calculation
        Double overtimePay = Double.valueOf(200.00);    // Replace with your calculation
        Double deductions = Double.valueOf(100.00);    // Replace with your calculation
        Double netSalary = Double.valueOf(25500);

        String sql = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES (?, ?, ?, ?, ?, ?, ?)";
try {
        // Create a PreparedStatement with the SQL query
	PreparedStatement  preparedStatement = connection.prepareStatement(sql);

        // Set parameters for the query based on your calculated values
    preparedStatement.setInt(1, employeeId);
    preparedStatement.setDate(2, java.sql.Date.valueOf(startDate));
    preparedStatement.setDate(3, java.sql.Date.valueOf(endDate));
    preparedStatement.setDouble(4, basicSalary);
    preparedStatement.setDouble(5, overtimePay);
    preparedStatement.setDouble(6, deductions);
    preparedStatement.setDouble(7, netSalary);


        // Execute the query to insert the payroll record
        int rowsAffected = preparedStatement.executeUpdate();

        // Return true if at least one row was affected (record inserted), false otherwise
        return rowsAffected > 0;
    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();
        return false; // Indicate failure
    } 

    }


}
