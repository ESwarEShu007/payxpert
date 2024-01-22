package dao;
import java.util.*;
import entity.*;
public interface FinancialRecordServiceInt {
    FinancialRecord getFinancialRecordById(int recordId);
    List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId);
    List<FinancialRecord> getFinancialRecordsForDate(String recordDate);
    void addFinancialRecord(int employeeId, String description, double amount, String recordType);
 
}
