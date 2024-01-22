package dao;
import java.util.*;
import entity.*;
public interface TaxServiceInt {
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);

}
