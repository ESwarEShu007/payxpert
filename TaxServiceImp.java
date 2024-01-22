package dao;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import entity.*;
public class TaxServiceImp implements TaxServiceInt {
	private Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

	@Override
    public Tax getTaxById(int taxId) {
		Tax tax= null;
String sql = "SELECT * FROM Tax WHERE TaxID = ?";
        try {
        	PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, taxId);

        // Execute the query
        ResultSet   resultSet = preparedStatement.executeQuery();

        // Check if the tax record exists
        if (resultSet.next()) {

            // Retrieve values from the result set
            int retrievedTaxId = resultSet.getInt("TaxID");
            int employeeId = resultSet.getInt("EmployeeID");
            int taxYear = resultSet.getInt("TaxYear");
            double taxableIncome = resultSet.getDouble("TaxableIncome");
            double taxAmount = resultSet.getDouble("TaxAmount");

            // Create a Tax object with the retrieved values
             tax = new Tax(retrievedTaxId, employeeId, taxYear, taxableIncome, taxAmount);

            // Return the Tax object
            return tax;
        } else {
            // Tax record not found
            System.out.println("Tax record with ID " + taxId + " not found.");
            return null;
        }
    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();
        return null; // Indicate failure
    } 
}

    

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxes = new ArrayList<>();
        try{
        	String sql = "SELECT * FROM Tax WHERE EmployeeID = ?";
        PreparedStatement    preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);

        // Execute the query
        ResultSet   resultSet = preparedStatement.executeQuery();

        // Process the result set
        while (resultSet.next()) {
            // Retrieve values from the result set
            int taxId = resultSet.getInt("TaxID");
            int retrievedEmployeeId = resultSet.getInt("EmployeeID");
            int taxYear = resultSet.getInt("TaxYear");
            double taxableIncome = resultSet.getDouble("TaxableIncome");
            double taxAmount = resultSet.getDouble("TaxAmount");

            // Create a Tax object with the retrieved values
            Tax tax = new Tax(taxId, retrievedEmployeeId, taxYear, taxableIncome, taxAmount);

            // Add the Tax object to the list
            taxes.add(tax);
        }

    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();
    } 

    // Return the list of taxes for the employee
    return taxes;
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> taxes = new ArrayList<>();
        try {
        String sql = "SELECT * FROM Tax WHERE TaxYear = ?";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, taxYear);

        // Execute the query
        ResultSet resultSet = preparedStatement.executeQuery();

        // Process the result set
        while (resultSet.next()) {
            // Retrieve values from the result set
            int taxId = resultSet.getInt("TaxID");
            int employeeId = resultSet.getInt("EmployeeID");
            double taxableIncome = resultSet.getDouble("TaxableIncome");
            double taxAmount = resultSet.getDouble("TaxAmount");

            // Create a Tax object with the retrieved values
            Tax tax = new Tax(taxId, employeeId, taxYear, taxableIncome, taxAmount);

            // Add the Tax object to the list
            taxes.add(tax);
        }

    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();
    } 

    // Return the list of taxes for the specified tax year
    return taxes;
}
    


}
