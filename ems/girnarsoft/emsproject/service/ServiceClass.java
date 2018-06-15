/**
* This Class is meant for the purpose of serving all the feature of EMS to all the Employee.
* This class consist services like
* Promotion of an Employee.
* Display all the Mentor of a particular Employee.
* Display all the Employee who are working under a particular Employee.
* Add Mentor for an Employee.
* Add all the Employee who are working under a particular Employee.
*/
package ems.girnarsoft.emsproject.service;

import ems.giranarsoft.emsproject.database.EmployeeDatabase;
import ems.girnarsoft.emsproject.beans.Employee;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ServiceClass {
    
    /**
    * This Method will promote an Employee just by changing their Designation in all the files.
    * 
    */
    public void promoteEmployee(EmployeeDatabase database, int promoteEmployeeID) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if(database.getEmployeeDesignationDetailsMap().get(promoteEmployeeID).equals("New Joiner"))
        {
            System.out.println("You are a newbie, you can not promote any Employee.");
        }
        else if(database.getEmployeeDesignationDetailsMap().get(promoteEmployeeID).equals("Manger")) 
        {
            boolean flag = true;
            while(flag) {
                System.out.println("Enter The Employee Id Whom you want to promote");
                int promotingEmployeeId = validateInteger();

                if(database.getEmployeeDetailsMap().get(promotingEmployeeId) == null) 
                {
                    System.out.println("No Employee Exists with given Employee ID.");
                    System.out.println("Please Enter the Existing Employee ID");
                }
                else
                {
                    flag = false;
                    if(database.getEmployeeDesignationDetailsMap().get(promotingEmployeeId).equals("New Joiner")) 
                    {
                        promoteNewJoiner(database,promotingEmployeeId);
                    }
                    else
                    {
                        System.out.println("You are not Authorized to promote The " + database.getEmployeeDetailsMap().get(promotingEmployeeId).getDesignation());
                    }
                }
            }
            
        }
        else if(database.getEmployeeDesignationDetailsMap().get(promoteEmployeeID).equals("Director") || database.getEmployeeDesignationDetailsMap().get(promoteEmployeeID).equals("HR"))
        {
            boolean flag = true;
            while(flag) {
                System.out.println("Enter The Employee Id Whom you want to promote");
                int promotingEmployeeId = validateInteger();

                if(database.getEmployeeDetailsMap().get(promotingEmployeeId) == null) 
                {
                    System.out.println("No Employee Exists with given Employee ID.");
                    System.out.println("Please Enter the Existing Employee ID");
                }
                else
                {
                    flag = false;
                    if(database.getEmployeeDesignationDetailsMap().get(promotingEmployeeId).equals("New Joiner")) 
                    {
                        promoteNewJoiner(database,promotingEmployeeId);
                    }
                    else if(database.getEmployeeDesignationDetailsMap().get(promotingEmployeeId).equals("Manager")) 
                    {
                        promoteManager(database,promotingEmployeeId);
                    }
                    else
                    {
                        System.out.println("You are not Authorized to promote The " + database.getEmployeeDetailsMap().get(promotingEmployeeId).getDesignation());
                    }
                }
            }
        }
        else if(database.getEmployeeDesignationDetailsMap().get(promoteEmployeeID).equals("CEO"))
        {
            boolean flag = true;
            while(flag) {
                System.out.println("Enter The Employee Id Whom you want to promote");
                int promotingEmployeeId = validateInteger();

                if(database.getEmployeeDetailsMap().get(promotingEmployeeId) == null) 
                {
                    System.out.println("No Employee Exists with given Employee ID.");
                    System.out.println("Please Enter the Existing Employee ID");
                }
                else
                {
                    flag = false;
                    if(database.getEmployeeDesignationDetailsMap().get(promotingEmployeeId).equals("New Joiner")) 
                    {
                        promoteNewJoiner(database,promotingEmployeeId);
                    }
                    else if(database.getEmployeeDesignationDetailsMap().get(promotingEmployeeId).equals("Manager")) 
                    {
                        promoteManager(database,promotingEmployeeId);
                    }
                    else if(database.getEmployeeDesignationDetailsMap().get(promotingEmployeeId).equals("HR"))
                    {
                        System.out.println(database.getEmployeeDetailsMap().get(promotingEmployeeId).getName() + " is HR of The Company. And HR can Not be promoted.");
                    }
                    else if(database.getEmployeeDesignationDetailsMap().get(promotingEmployeeId).equals("Director"))
                    {
                        System.out.println(database.getEmployeeDetailsMap().get(promoteEmployeeID).getName() + " is Director of The Company. And Director can Not be promoted.");
                    }
                    else
                    {
                        System.out.println(database.getEmployeeDetailsMap().get(promotingEmployeeId).getName() + " is the CEO of the Company. And CEO can not be promoted.");
                    }
                }
                
            }
        }
    }
    
    /**
    * This Method will display all the mentors of an EMployee.
    */
    public void displayMyMentor(EmployeeDatabase database, int employeeID) throws IOException {
        
        if(database.getMyMentorMap().get(employeeID) != null) 
        {
            LinkedList<Integer> listOfMyMentor = database.getMyMentorMap().get(employeeID);
            System.out.println("I am working under " + listOfMyMentor.size() + " Mentor");
            System.out.println("Employee ID  Mentor Name");
            for(int indexOfList = 0; indexOfList < listOfMyMentor.size(); indexOfList++) 
            {
                System.out.println(listOfMyMentor.get(indexOfList) + "             " + database.getEmployeeDetailsMap().get(listOfMyMentor.get(indexOfList)).getName());
            }
        }
        else
        {
            System.out.println("You don't have any Mentor.");
        }
        
    }
    
    /**
    * This Method will display all the EMployee who are working under an EMployee.
    */
    public void displayEmployeesUnderMe(EmployeeDatabase database, int employeeID) throws IOException {
        
        if(database.getEmployeeUnderMeMap().get(employeeID) != null) 
        {
            LinkedList<Integer> listOfEmployeesUnderMe = database.getEmployeeUnderMeMap().get(employeeID);
        
            System.out.println(listOfEmployeesUnderMe.size() + " Employees are working Under you.");
            System.out.println("Employee ID  Employee Name");
            for(int indexOfList = 0; indexOfList < listOfEmployeesUnderMe.size(); indexOfList++) 
            {
                System.out.println(listOfEmployeesUnderMe.get(indexOfList) + "      " + database.getEmployeeDetailsMap().get(listOfEmployeesUnderMe.get(indexOfList)).getName());
            }
        }
        else
        {
            System.out.println("There is no Employee who works under you.");
        }
        
    }
    
    /**
    * This Method will add the mentor for an Employee.
    */
    public void addMyMentor(EmployeeDatabase database, int employeeID) throws IOException {
        
        String employeeDesignation = database.getEmployeeDesignationDetailsMap().get(employeeID);
        boolean flag = true;
        while(flag) {
            System.out.println("Enter your mentor Id");
            int mentorID = validateInteger();
            if (database.getEmployeeDetailsMap().get(mentorID) == null) 
            {
                System.out.println("No Employee Exists with given Employee ID.");
                System.out.println("Please Enter the Existing Employee ID");
            }
            else
            {
                flag = false;
                String mentorDesignation = database.getEmployeeDesignationDetailsMap().get(mentorID);
                if ((employeeDesignation.equals("New Joiner") && !mentorDesignation.equals("New Joiner")) || (employeeDesignation.equals("Manager") && !mentorDesignation.equals("Manager") && !mentorDesignation.equals("New Joiner")) || (employeeDesignation.equals("Director") && !mentorDesignation.equals("Director") && !mentorDesignation.equals("Manager") && !mentorDesignation.equals("New Joiner")) || (employeeDesignation.equals("HR") && !mentorDesignation.equals("HR") && !mentorDesignation.equals("Manager") && !mentorDesignation.equals("New Joiner")) || (employeeDesignation.equals("CEO") && !mentorDesignation.equals("CEO") && !mentorDesignation.equals("Director") && !mentorDesignation.equals("HR") && !mentorDesignation.equals("Manager") && !mentorDesignation.equals("New Joiner")))
                {
                    addNewMentor(database, employeeID, mentorID);
                }
                else
                {
                    System.out.println("You can not add your colleague, Junior or Yourself as your mentor");
                    System.out.println("Employee Id " + mentorID + ", " + database.getEmployeeDetailsMap().get(mentorID).getName() + " is The " + mentorDesignation + " of the compnay.");
                    System.out.println("Thank You !!");
                }
            }
        }      
        
    }
    
    /**
    * This Method will add employee who are working under an employee.
    */
    public void addEmployeeUnderMe(EmployeeDatabase database, int employeeID) throws IOException {
        
        String employeeDesignation = database.getEmployeeDesignationDetailsMap().get(employeeID);
        boolean flag = true;
        while(flag) {
            System.out.println("Enter your Employee Id whom you want to add under you.");
            int underEmployeeID = validateInteger();
            if (database.getEmployeeDetailsMap().get(underEmployeeID) == null) 
            {
                System.out.println("No Employee Exists with given Employee ID.");
                System.out.println("Please Enter the Existing Employee ID");
            }
            else
            {
                flag = false;
                String underEmployeeDesignation = database.getEmployeeDesignationDetailsMap().get(underEmployeeID);
                if ((employeeDesignation.equals("CEO") && !underEmployeeDesignation.equals("CEO")) || (employeeDesignation.equals("HR") && !underEmployeeDesignation.equals("CEO") && !underEmployeeDesignation.equals("Director") && !underEmployeeDesignation.equals("HR")) || (employeeDesignation.equals("Director") && !underEmployeeDesignation.equals("CEO") && !underEmployeeDesignation.equals("HR") && !underEmployeeDesignation.equals("Director")) || (employeeDesignation.equals("Manager") && !underEmployeeDesignation.equals("CEO") && !underEmployeeDesignation.equals("HR") && !underEmployeeDesignation.equals("Director") && !underEmployeeDesignation.equals("Manager")) || (employeeDesignation.equals("New Joiner") && !underEmployeeDesignation.equals("CEO") && !underEmployeeDesignation.equals("Director") && !underEmployeeDesignation.equals("HR") && !underEmployeeDesignation.equals("Manager") && !underEmployeeDesignation.equals("New Joiner")))
                {
                    addNewEmployeeUnderMe(database, employeeID, underEmployeeID);
                }
                else
                {
                    System.out.println("You can not add your colleague, Senior or yourself as Employee who works under you");
                    System.out.println("Employee Id " + underEmployeeID + ", " + database.getEmployeeDetailsMap().get(underEmployeeID).getName() + " is The " + underEmployeeDesignation + " of the compnay.");
                    System.out.println("Thank You !!");
                }
            }
        }      
        
        
    }
    
    /**
    * This Method is used for the performance of the code, so that the same code should not repeate.
    */
    private void addNewEmployeeUnderMe(EmployeeDatabase database, int employeeID, int underEmployeeID) throws IOException {
        
        if(database.getEmployeeUnderMeMap().get(employeeID) == null)
        {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(underEmployeeID);
            database.getEmployeeUnderMeMap().put(employeeID, list);
            System.out.println("You have Successfully added " + database.getEmployeeDetailsMap().get(underEmployeeID).getName() + ".");
        }
        else
        {
            LinkedList<Integer> list = database.getEmployeeUnderMeMap().get(employeeID);
            list.add(underEmployeeID);
            database.getEmployeeUnderMeMap().put(employeeID, list);
            System.out.println("You have Successfully added " + database.getEmployeeDetailsMap().get(underEmployeeID).getName() + ".");
        }
    }
    
    /**
    * This Method is used for the performance of the code, so that the same code should not repeate.
    */
    private void addNewMentor(EmployeeDatabase database, int employeeID, int mentorID) throws IOException {
               
        if(database.getMyMentorMap().get(employeeID) == null)
        {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(mentorID);
            database.getMyMentorMap().put(employeeID, list);
            System.out.println("You have Successfully added " + database.getEmployeeDetailsMap().get(mentorID).getName() + ".");
        }
        else
        {
            LinkedList<Integer> list = database.getMyMentorMap().get(employeeID);
            list.add(mentorID);
            database.getMyMentorMap().put(employeeID, list);
            System.out.println("You have Successfully added " + database.getEmployeeDetailsMap().get(mentorID).getName() + ".");
        } 
    }
    
    /**
    * This Method is used for the performance of the code, so that the same code should not repeate.
    */
    private void promoteNewJoiner(EmployeeDatabase database, int promotingEmployeeId) throws IOException {
        
        System.out.println("You can promote Mr. " + database.getEmployeeDetailsMap().get(promotingEmployeeId).getName() + " as a Manager");
        System.out.println("If you want to promote him as a manager press 1 other wise 0");
        int directorChoice = validateInteger();
        if(directorChoice == 1) 
        {
            Employee employee = database.getEmployeeDetailsMap().get(promotingEmployeeId);
            employee.setDesignation("Manager");
            database.getEmployeeDetailsMap().put(promotingEmployeeId,employee);
            database.getEmployeeDesignationDetailsMap().put(promotingEmployeeId,"Manager");
            System.out.println("You Have Successfully Promoted " + database.getEmployeeDetailsMap().get(promotingEmployeeId).getName() + " as a Manager");
        }
    }
    
    /**
    * This Method is used for the performance of the code, so that the same code should not repeate.
    */
    private void promoteManager(EmployeeDatabase database, int promotingEmployeeId) throws IOException {
        
        System.out.println("You can promote Mr. " + database.getEmployeeDetailsMap().get(promotingEmployeeId).getName() + " as a Director");
        System.out.println("If you want to promote him as a Director press 1 other wise 0");
        int directorChoice = validateInteger();
        if(directorChoice == 1) {
            Employee employee = database.getEmployeeDetailsMap().get(promotingEmployeeId);
            employee.setDesignation("Director");
            database.getEmployeeDetailsMap().put(promotingEmployeeId,employee);
            database.getEmployeeDesignationDetailsMap().put(promotingEmployeeId,"Director");
            System.out.println("You Have Successfully Promoted " + database.getEmployeeDetailsMap().get(promotingEmployeeId).getName() + " as a Director");
        }
    }
    
    private int validateInt() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a  Valid number!");
            System.out.println("Please Enter Only Valid Integer");
            System.out.println("");
            scanner.next(); // this is important!
        }
        return scanner.nextInt();
    }
    
    private static int validateInteger() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean flag = true;
        while(flag)
        {
            input = scanner.nextLine();
            char[] ch = input.toCharArray();
            for(int index = 0; index < input.length(); index++) 
            {
                if(ch[index] == '0' || (ch[index] >= '1' && ch[index] <= '9'))
                {
                    flag = false;
                    continue;
                }
                else
                {
                    System.out.println("That's not a  Valid number!");
                    System.out.println("Please Enter Only Valid Integer");
                    System.out.println("");
                    flag = true;
                    break;
                }
            }
        }
        return Integer.parseInt(input);
        
    }
}
