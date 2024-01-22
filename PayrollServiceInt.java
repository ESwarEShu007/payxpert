package dao;
import java.util.*;
import java.time.LocalDate;
import entity.*;
public interface PayrollServiceInt {
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
    boolean generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate);

}
