
package ems.girnarsoft.emsproject.beans;

import ems.giranarsoft.emsproject.database.EmployeeDatabase;

public interface IEmployee {
    
    public void displayEmployeesUnderMe(EmployeeDatabase database);
    public void displayMyMentor(EmployeeDatabase database);
    public int getEmployeeId();
}
