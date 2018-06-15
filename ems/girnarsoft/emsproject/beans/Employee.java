
package ems.girnarsoft.emsproject.beans;

import ems.giranarsoft.emsproject.database.EmployeeDatabase;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    
    private static final AtomicInteger COUNT = new AtomicInteger(0); 
    private int empId;
    private String firstName;
    private String lastName;
    private String designationOfEmployee;
    private int pin;
    
    /**
    * A default Constructor. 
    */
    public Employee() {
        
    }
    
    /**
    * This Constructor is used when there is a new Employee need to create.
    * mapSize is used because of creating a new employeeId require number of existing employee into the database.
    * Once the number of existing(mapSize) employee into the database found the new Employee's employeeID will be number of existing employee + 1. 
    */
    public Employee(String firstName, String lastName, String designationOfEmployee, int pin, int mapSize) {
        
        this.empId = mapSize + 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designationOfEmployee = designationOfEmployee;
        this.pin = pin;
        
    }
    
    /**
    * This Constructor is used when you have already an employee and you want to create a new object of that employee.
    * 
    */
    public Employee(int employeeID, String firstName, String lastName, String designationOfEmployee, int pin) {
        this.empId = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designationOfEmployee = designationOfEmployee;
        this.pin = pin;
    }
    
    
    public String getName() {
        
        String fullName;
        
        fullName = this.firstName.concat(" ");
        fullName = fullName.concat(lastName);
        return fullName;
        
    }
    
    public String getDesignation() {
        
        return designationOfEmployee;
        
    }
    
    public int getPin() {
        return pin; 
    }
    
    public int getEmployeeId() {
        return empId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setDesignation(String designationOfEmployee) {
        
        this.designationOfEmployee = designationOfEmployee;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setPin(int pin) {
        this.pin = pin;
    }
    
    public void promoteEmployee(EmployeeDatabase database) {
        
    }
    public void displayEmployeesUnderMe(EmployeeDatabase database) {
        
    }
    
    public void displayMyMentor(EmployeeDatabase database) {
        
    }
    
    public void addMyMentor(EmployeeDatabase database) {
        
    }
    
    public void addEmployeeUnderMe(EmployeeDatabase database) {
        
    }
    
}
