package dao;
import util.*;
import java.util.*;
import entity.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
public class FinancialRecordServiceImp implements FinancialRecordServiceInt {
    private static final Date RecordDate = null;
	private Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
	@Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        FinancialRecord financialRecord = null;

        String sql = "SELECT * FROM FinancialRecord WHERE RecordID = ?";
        try {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, recordId);

        // Execute the query and get the result set
        resultSet = preparedStatement.executeQuery();

        // Check if the result set has a record
        if (resultSet.next()) {
            // Retrieve data from the result set and create a FinancialRecord object
            financialRecord = new FinancialRecord();
            financialRecord.setRecordID(resultSet.getInt("RecordID"));
            financialRecord.setEmployeeID(resultSet.getInt("EmployeeID"));
            financialRecord.setRecordDate(resultSet.getString("RecordDate"));
            financialRecord.setDescription(resultSet.getString("Description"));
            financialRecord.setAmount(resultSet.getDouble("Amount"));
            financialRecord.setRecordType(resultSet.getString("RecordType"));
        }
    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();  // You may want to log the exception or throw a custom exception
    } 
        return financialRecord;
}

        
    
    
    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        String sql = "SELECT * FROM FinancialRecord WHERE EmployeeID = ?";
        List<FinancialRecord> financialRecords = new ArrayList<>();

try {
        // Create a PreparedStatement with the SQL query
	PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);

        // Execute the query and get the result set
        ResultSet  resultSet = preparedStatement.executeQuery();

        // Iterate through the result set and create FinancialRecord objects
        while (resultSet.next()) {
            FinancialRecord financialRecord = new FinancialRecord();
            financialRecord.setRecordID(resultSet.getInt("RecordID"));
            financialRecord.setEmployeeID(resultSet.getInt("EmployeeID"));
            financialRecord.setRecordDate(resultSet.getString("RecordDate"));
            financialRecord.setDescription(resultSet.getString("Description"));
            financialRecord.setAmount(resultSet.getDouble("Amount"));
            financialRecord.setRecordType(resultSet.getString("RecordType"));

            // Add the financial record to the list
            financialRecords.add(financialRecord);

        }
    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();  // You may want to log the exception or throw a custom exception
    } 


        return financialRecords; // Replace with actual implementation
    }

    public List<FinancialRecord> getFinancialRecordsForDate(String recordDate) {
        List<FinancialRecord> financialRecords = new ArrayList<>();

        try {
            // Get a database connection

            // Define the SQL query
            String sql = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";

            // Create a PreparedStatement with the SQL query
            PreparedStatement     preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recordDate);

            // Execute the query and get the result set
           ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create FinancialRecord objects
            while (resultSet.next()) {
                FinancialRecord financialRecord = new FinancialRecord();
                financialRecord.setRecordID(resultSet.getInt("RecordID"));
                financialRecord.setEmployeeID(resultSet.getInt("EmployeeID"));
                financialRecord.setRecordDate(resultSet.getString("RecordDate"));
                financialRecord.setDescription(resultSet.getString("Description"));
                financialRecord.setAmount(resultSet.getDouble("Amount"));
                financialRecord.setRecordType(resultSet.getString("RecordType"));

                // Add the financial record to the list
                financialRecords.add(financialRecord);
            }
        } catch (SQLException e) {
            // Handle database-related exceptions
            e.printStackTrace();  // You may want to log the exception or throw a custom exception
        } 

        return financialRecords;
    }
    

    @Override
    public void addFinancialRecord(int employeeId, String description, double amount, String recordType) {
        String sql = "INSERT INTO FinancialRecord (EmployeeID, RecordDate, Description, Amount, RecordType) VALUES (?, ?, ?, ?, ?)";
try {
        // Create a PreparedStatement with the SQL query
	PreparedStatement  preparedStatement = connection.prepareStatement(sql);

        // Set parameters for the query
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setString(2, String.valueOf(LocalDate.now())); // Assuming current date
        preparedStatement.setString(3, description);
        preparedStatement.setDouble(4, amount);
        preparedStatement.setString(5, recordType);

        // Execute the query to insert the new record
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        // Handle database-related exceptions
        e.printStackTrace();
    } 
}
    }


