package main;
import java.util.*;
import entity.*;
import util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;

import dao.*;
public class Main_module {
	public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);     
		Employee employee1 = new Employee(4,"dravid", "mahesh", "2001-05-10", "M", "dravid@email.com", "7993047454", "eswarnagr", "manager", "2001-05-11", "2001-05-18");
        EmployeeService employeeService = new EmployeeServiceImp();
        PayrollServiceInt payrollService = new PayrollServiceImp();
        TaxServiceInt taxService = new TaxServiceImp();
        FinancialRecordServiceInt financialRecordService = new FinancialRecordServiceImp();
       try (Connection connection = DatabaseContext.getConnection()) {
            ((EmployeeServiceImp) employeeService).setConnection(connection);
            ((PayrollServiceImp) payrollService).setConnection(connection);
            ((TaxServiceImp) taxService).setConnection(connection);
            ((FinancialRecordServiceImp) financialRecordService).setConnection(connection);
//            taxService.getTaxById(1);
            employeeService.updateEmployee(employee1);
           int choice;
           do {
               System.out.println("1. Get Employee by ID");
               System.out.println("2. Get All Employees");
               System.out.println("3. Add Employee");
               System.out.println("4. Remove Employee");
               System.out.println("5. get FinancialRecord By recordId");
               System.out.println("6. get FinancialRecord for employee");
               System.out.println("7. get FinancialRecord By date");
               System.out.println("8. add FinancialRecord By recordId");
               System.out.println("9. get payroll By payrollid");
               System.out.println("10. get payroll for employee");
               System.out.println("11. get payroll  for period");
               System.out.println("12. to generate  payroll");
               System.out.println("13. get tax   by id");
               System.out.println("14. get tax  by employeeid");
               System.out.println("15. get tax  for year");
               System.out.println("0. Exit");
               System.out.print("Enter your choice: ");

               choice = scanner.nextInt();

               switch (choice) {
                   case 1:
                       System.out.print("Enter Employee ID: ");
                       int employeeId = scanner.nextInt();
                       if(employeeId!=0) {
                       employeeService.getEmployeeById(employeeId);
                       }
                       else                        System.out.print("Enter valid Employee ID: ");

                       break;

                   case 2:
                	   
                       employeeService.getAllEmployees();
                       break;

                   case 3:
                       System.out.println("Enter employee ID:");
                       int employeeID = scanner.nextInt();

                       System.out.println("Enter first name:");
                       String firstName = scanner.next();
                       System.out.println("Enter last name:");
                       String lastName = scanner.next();
                       System.out.println("Enter date of birth (yyyy-MM-dd):");
                       String dateOfBirth = scanner.next();
                       System.out.println("Enter gender (M/F):");
                       String gender = scanner.next();
                       System.out.println("Enter email:");
                       String email = scanner.next();
                       System.out.println("Enter phone number:");
                       String phoneNumber = scanner.next();
                       System.out.println("Enter address:");
                       String address = scanner.next();
                       System.out.println("Enter position:");
                       String position = scanner.next();
                       System.out.println("Enter joining date (yyyy-MM-dd):");
                       String joiningDate = scanner.next();
                       System.out.println("Enter termination date (yyyy-MM-dd):");
                       String terminationDate = scanner.next();
                       Employee employeee = new Employee(employeeID, firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate);
                       employeeService.addEmployee(employeee);
                       employeeService.getAllEmployees();
                       break;
                   case 4:
                       System.out.println("Enter employee ID TO REMOVE FROM database");
                       int id=scanner.nextInt() ;    
                    if (id != 0) {
                employeeService.removeEmployee(id);
                System.out.println("Employee removed successfully.");
                  } else {
                    System.out.println("Invalid EmployeeID provided.");
                       }
                       break;
                   case 5:
                       System.out.print("Enter recordID : ");
                       int idToRemove = scanner.nextInt();
                       FinancialRecord financialRecord= financialRecordService.getFinancialRecordById(idToRemove);
                       if (financialRecord != null) {
                           System.out.println("Financial Record Details:");
                           System.out.println(financialRecord);
                       } else {
                           System.out.println("Financial Record not found.");
                       }

                       break;
                   case 6:
                       System.out.print("Enter employeeid : ");
                     int ide =scanner.nextInt();
                     List<FinancialRecord> financialRecord2=financialRecordService.getFinancialRecordsForEmployee(ide);
                     if (!financialRecord2.isEmpty()) {
                         System.out.println("Financial Record Details:");
                         System.out.println(financialRecord2);
                     } else {
                         System.out.println("Financial Record not found.");
                     }
                       break;
                   case 7:
                       System.out.print("Enter date to get records");
                     String recorddate =scanner.next();

                     List<FinancialRecord> financialRecord3=financialRecordService.getFinancialRecordsForDate(recorddate);
                     if (!financialRecord3.isEmpty()) {
                         System.out.println("Financial Record Details:");
                         System.out.println(financialRecord3);
                     } else {
                         System.out.println("Financial Record not found.");
                     }

                       break;
                   case 8:
                       System.out.print("Enter employeeid");
                       int employeeid=scanner.nextInt();
                       System.out.print("Enter description");
                       String discription=scanner.next();
                       System.out.print("Enter amount");
                       double amount=scanner.nextDouble();
                       System.out.print("Enter employeeid");
                       String recordType=scanner.next();
                       financialRecordService.addFinancialRecord(employeeid,discription,amount,recordType);
                       break;
                   case 9:
                       System.out.print("Enter payrollid");
                       int payrollid=scanner.nextInt();
                       Payroll finalpayroll=payrollService.getPayrollById(payrollid);
                     if (finalpayroll != null) {
                     System.out.println("payroll Record Details:");
                     System.out.println(finalpayroll);
                 } else {
                     System.out.println("Financial Record not found.");
                 }
                       break;
                   case 10:
                       System.out.print("Enter employee id");
                       int employeeidd=scanner.nextInt();
                     List<Payroll> finalpayroll2=payrollService.getPayrollsForEmployee(employeeidd);
                   if (finalpayroll2 != null) {
                   System.out.println("payroll Record Details:");
                   System.out.println(finalpayroll2);
               } else {
                   System.out.println("Financial Record not found.");
               }
                       break;
                   case 11:
                       System.out.println("Enter start date year!");
                       int startdateyear= scanner.nextInt();
                       System.out.println("Enter start date month!");
                       int startdatemonth= scanner.nextInt();
                       System.out.println("Enter start date day!");
                       int startdateday= scanner.nextInt();
                       System.out.println("Enter end date year!");
                       int enddateyear= scanner.nextInt();
                       System.out.println("Enter end date month!");
                       int enddatemonth= scanner.nextInt();
                       System.out.println("Enter end date day!");
                       int enddateday= scanner.nextInt();
                     List<Payroll> finalpayroll3=payrollService.getPayrollsForPeriod(LocalDate.of(startdateyear,startdatemonth,startdateday),LocalDate.of(enddateyear, enddatemonth, enddateday));
                   if (finalpayroll3 != null) {
                   System.out.println("payroll Record Details:");
                   System.out.println(finalpayroll3);
               } else {
                   System.out.println("Financial Record not found.");
               }
                       break;
                   case 12:
                       System.out.println("Enter start date year!");
                       int startdateyear1= scanner.nextInt();
                       System.out.println("Enter start date month!");
                       int startdatemonth1= scanner.nextInt();
                       System.out.println("Enter start date day!");
                       int startdateday1= scanner.nextInt();
                       System.out.println("Enter end date year!");
                       int enddateyear1= scanner.nextInt();
                       System.out.println("Enter end date month!");
                       int enddatemonth1= scanner.nextInt();
                       System.out.println("Enter end date day!");
                       int enddateday1= scanner.nextInt();
                  payrollService.generatePayroll(1,LocalDate.of(startdateyear1,startdatemonth1,startdateday1),LocalDate.of(enddateyear1, enddatemonth1, enddateday1));
                       break;
                   case 13:
                       System.out.print("Enter tax id");
                       int taxid=scanner.nextInt();
                     Tax tat= taxService.getTaxById(taxid);
                       if (tat != null) {
                           System.out.println("tax Record Details:");
                           System.out.println(tat);
                       } else {
                           System.out.println("tax Record not found.");
                       }
                       break;
                   case 14:
                       System.out.print("Enter employee id");
                       int taxemployeeid=scanner.nextInt();

                     List<Tax> tat2=    taxService.getTaxesForEmployee(taxemployeeid);
                   if (tat2 != null) {
                   System.out.println("tax Record Details:");
                   System.out.println(tat2);
               } else {
                   System.out.println("tax Record not found.");
               }
                       break;
                   case 15:
                       System.out.print("Enter year");
                     int year =scanner.nextInt();
                     List<Tax> tat3=taxService.getTaxesForYear(year);
                     if (tat3 != null) {
                         System.out.println("tax Record Details:");
                         System.out.println(tat3);
                     } else {
                         System.out.println("tax Record not found.");
                     }
                       break;

                   default:
                       System.out.println("Invalid choice. Please try again.");
               }
           } while (choice != 0);{

           scanner.close();
       }

}
}

}
