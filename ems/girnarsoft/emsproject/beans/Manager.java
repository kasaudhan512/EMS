package ems.girnarsoft.emsproject.beans;

import ems.giranarsoft.emsproject.database.EmployeeDatabase;
import ems.girnarsoft.emsproject.service.ServiceClass;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Manager extends Employee implements IEmployee {
    
    public Manager(String firstName, String lastName, String designationOfEmployee, int pin, int employeeId) {
        super(firstName, lastName, designationOfEmployee, pin, employeeId);
    }
    
    public Manager(int employeeId, String firstName, String lastName, String designationOfEmployee, int mPIN) {
        super(employeeId, firstName, lastName, designationOfEmployee, mPIN);
    }
    
    public void promoteEmployee(EmployeeDatabase database) {
        
        ServiceClass serviceObject = new ServiceClass();
        try {
            serviceObject.promoteEmployee(database, super.getEmployeeId());
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayEmployeesUnderMe(EmployeeDatabase database) {
        
        ServiceClass serviceObject = new ServiceClass();
        try {
            serviceObject.displayEmployeesUnderMe(database, super.getEmployeeId());
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void displayMyMentor(EmployeeDatabase database) {
        
        ServiceClass serviceObject = new ServiceClass();
        try {
            serviceObject.displayMyMentor(database, super.getEmployeeId());
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addMyMentor(EmployeeDatabase database) {
        
        ServiceClass serviceObject = new ServiceClass();
        try {
            serviceObject.addMyMentor(database, super.getEmployeeId());
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addEmployeeUnderMe(EmployeeDatabase database) {
        
        ServiceClass serviceObject = new ServiceClass();
        try {
            serviceObject.addEmployeeUnderMe(database, super.getEmployeeId());
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
