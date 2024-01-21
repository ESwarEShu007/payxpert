package entity;

public class ValidationService {
    // Method for validating employee data
    public static boolean isValidEmployeeData(Employee employee) {
        if (employee == null) {
            return false; // Employee object is null, invalid
        }

        // Perform additional validation based on business rules
        // For example, check if required fields are not empty
        if (employee.getFirstName() == null || employee.getLastName() == null || employee.getDateOfBirth() == null
                || employee.getGender() == "\u0000" || employee.getEmail() == null || employee.getPhoneNumber() == null
                || employee.getAddress() == null || employee.getPosition() == null || employee.getJoiningDate() == null) {
            return false; // Required fields are not provided, invalid
        }

        // Additional validation logic can be added based on your specific business rules

        // If all validations pass, consider the employee data as valid
        return true;
    }

    // You can add other validation methods for different entities or business rules

}
