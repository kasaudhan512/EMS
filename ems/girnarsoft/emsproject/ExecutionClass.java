/**
* This class is meant for the starting the Application.
* It Contains Main Method, and some basic features Like,
* SignUp, LogIn, and choosing Activity
*/
package ems.girnarsoft.emsproject;
import ems.giranarsoft.emsproject.database.EmployeeDatabase;
import ems.girnarsoft.emsproject.beans.*;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class ExecutionClass {
    
    public static void main (String[] args) throws IOException {
       Scanner scanner = new Scanner(System.in);
       System.out.println("*************************************");
       System.out.println("Welcome to Employee Management System");
       EmployeeDatabase database = new EmployeeDatabase();
       database.initializeDatabase();
       boolean isTrue = true;
       while(isTrue)
       {
            
                System.out.println("Press 1 - SignUp");
                System.out.println("Press 2 - Login");
                System.out.println("Press 3 - Exit");
                int userChoice;
                userChoice = validateInteger();
                switch (userChoice) {
                case 1:
                    signUp(database); 
                    break;

                case 2:
                    signIn(database);    
                    break;

                case 3:
                    isTrue = false;
                    /**
                    * Write into file after terminating the program
                    */
                    database.printDatabase();  
                    break;
                }
                
       }
       
       
       
   }
    /**
    * SignUp Method is meant for the Adding a new Employee into the database(files).
    */
    public static void signUp(EmployeeDatabase database) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        String firstName,lastName,designationOfEmpployee;
        int mPIN;
        boolean flag = true;
        System.out.println("Please Enter your First Name");
        firstName = validateString();
        System.out.println("Please Enter your Last Name");
        lastName = validateString();
        System.out.println("Please choose your mPIN, Only Integer value is allowed");
        mPIN = validateInteger();
        
        while(flag) {
            
            System.out.println("Please choose your designation");
            System.out.println("1- CEO");
            System.out.println("2- Director");
            System.out.println("3- HR");
            System.out.println("4- Manager");
            System.out.println("5- New Joiner");
            System.out.println("6- Exit");
            int designationChoice = validateInteger();
            //IEmployee employee = null;
            Employee employee;
            /**
            * These 5 cases are meant to different type of employee.
            * case 1 - For Creating employee of type CEO.
            * case 2 - FOr Creating employee of type Director.
            * case 3 - FOr Creating employee of type HR.
            * case 4 - FOr Creating employee of type Manager
            * case 5- FOr Creating employee of type New Joiner.
            */
            switch(designationChoice) {
                case 1:
                    if(!isCeoExist(database))
                    {
                        employee = new Ceo(firstName, lastName, "CEO", mPIN, database.getEmployeeDetailsMap().size());
                        database.getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
                        database.getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
                        database.getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "CEO");
                        System.out.println("You have Successfully Signed Up !!");
                        System.out.println("Your auto generated Employee Id is : " + employee.getEmployeeId());
                        System.out.println("Please Remember your Employee Id and Password");
                        System.out.println("");
                        System.out.println("");
                        flag = false;
                    }
                    else
                    {
                        System.out.println("CEO Already Exist in the Company Database. You can not sign up as a CEO");
                    }
                    break;

                case 2:
                    employee = new Director(firstName, lastName, "Director", mPIN, database.getEmployeeDetailsMap().size());
                    database.getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
                    database.getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
                    database.getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Director");
                    System.out.println("You have Successfully Signed Up !!");
                    System.out.println("Your auto generated Employee Id is : " + employee.getEmployeeId());
                    System.out.println("Please Remember your Employee Id and Password");
                    System.out.println("");
                    flag = false;
                    break;

                case 3:
                    employee = new Hr(firstName, lastName, "HR", mPIN, database.getEmployeeDetailsMap().size());
                    database.getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
                    database.getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
                    database.getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "HR");
                    System.out.println("You have Successfully Signed Up !!");
                    System.out.println("Your auto generated Employee Id is : " + employee.getEmployeeId());
                    System.out.println("Please Remember your Employee Id and Password");
                    System.out.println("");
                    flag = false;
                    break;

                case 4:
                    employee = new Manager(firstName, lastName, "Manager", mPIN, database.getEmployeeDetailsMap().size());
                    database.getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
                    database.getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
                    database.getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Manager");
                    System.out.println("You have Successfully Signed Up !!");
                    System.out.println("Your auto generated Employee Id is : " + employee.getEmployeeId());
                    System.out.println("Please Remember your Employee Id and Password");
                    System.out.println("");
                    flag = false;
                    break;

                case 5:
                    employee = new NewJoiner(firstName, lastName, "New Joiner", mPIN, database.getEmployeeDetailsMap().size());
                    database.getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
                    database.getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
                    database.getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "New Joiner");
                    System.out.println("You have Successfully Signed Up !!");
                    System.out.println("Your auto generated Employee Id is : " + employee.getEmployeeId());
                    System.out.println("Please Remember your Employee Id and Password");
                    System.out.println("");
                    flag = false;
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Please Choose Only given option");
                    break;

            }
        }
        
    }
    /**
    * LogIn Method is meant for the validating employees credencial from the database.
    * Giving Access of EMS System by validating employee.
    */
    private static void signIn(EmployeeDatabase database) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter your Employee ID");
        int employeeID = validateInteger();
        
        if(database.getEmployeeLoginDetailsMap().containsKey(employeeID)) {
            System.out.println("Please Enter your mPIN");
            int mpin = validateInteger();
            //System.out.println(database.getEmployeeLoginDetailsMap().get(employeeID));
            if(database.getEmployeeLoginDetailsMap().get(employeeID) == mpin) {
                Employee employee = database.getEmployeeDetailsMap().get(employeeID);
                System.out.println("Welcome to EMS System "+employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getDesignation());
                activity(employee,database);
                
            } else {
                System.out.println("You Entered wrong mPIN");
            }
            
            
        } else {
            System.out.println("You entered wrong Employee ID");
        }
        
    }
    /**
    * An Authorized Employee will Able to perform Activity.
    * Activity Like, Promote a Employee.
    * Add your mentor
    * Add Employee Under whom you work.
    * And display all the feasible Information to the user.
    */
    public static void activity(Employee employee,EmployeeDatabase database) {
        
        boolean isTrue = true;
        while(isTrue) {
            
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        System.out.println("Choose a option that you want to do");
        System.out.println("1- Promote a Employee");
        System.out.println("2- Display Employees Who are working under you");
        System.out.println("3- Display Your Mentor");
        System.out.println("4- Add Employee who is working under you");
        System.out.println("5- Add your Mentor");
        System.out.println("6- Exit");
        System.out.println("");
        int activityChoice = validateInteger();
        switch(activityChoice) {
            case 1:
                employee.promoteEmployee(database);
                break;
                
            case 2:
                employee.displayEmployeesUnderMe(database);
                break;
                
            case 3:
                employee.displayMyMentor(database);
                break;
                
            case 4:
                employee.addEmployeeUnderMe(database);
                break;
                
            case 5:
                employee.addMyMentor(database);
                break;
            case 6:
                isTrue = false;
                break;
            default:
                System.out.println("Please choose only given option");
                       
        }
        
        }
           
    }
    
    public static boolean isCeoExist(EmployeeDatabase database) {
        
        for(Map.Entry<Integer,String> myMap : database.getEmployeeDesignationDetailsMap().entrySet())
        {
            if(myMap.getValue().equals("CEO"))
            {
                return true;
            }
        }
        return false;
    }
    
    public static int validateInt() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a  Valid number!");
            System.out.println("Please Enter Only Valid Integer");
            System.out.println("");
            scanner.next(); // this is important!
        }
        return scanner.nextInt();
    }
    
    public static int validateInteger() {
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
    
    public static String validateString() {
        
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        String input="";
        while(flag)
        {
            input = scanner.nextLine();
            char[] ch=input.toCharArray();
            for(int index = 0; index < input.length();index++)
            {
                if((ch[index]>=65 && ch[index]<=90) || (ch[index]>=97 && ch[index]<=122))
                {
                    flag = false;
                }         
                else
                {
                    System.out.println("String Can not Have a special Charater or NUmbers");
                    System.out.println("Please Enter Valid Detail");
                    flag = true;
                    break;
                }
            }
            
        }
        return input;
        
    }
}
