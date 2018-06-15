/**
* This Class is menat for handling the Database of EMS System.
* It contains Methods which reads From the Files (puts into map from file, Initialize).
* It contains Method which writes into the files.
* 
*/
package ems.giranarsoft.emsproject.database;
import ems.girnarsoft.emsproject.beans.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDatabase {
    
    private final  Map<Integer,Employee> employeeDetailsMap;
    private final  Map<Integer,Integer> employeeLoginDetailsMap;
    private final  Map<Integer,String> employeeDesignationDetailsMap;
    private final  Map<Integer,LinkedList<Integer>> employeeUnderMeMap;
    private final  Map<Integer,LinkedList<Integer>> myMentorMap;

    private BufferedReader employeeDetailsReader = null;
    private BufferedReader employeeLoginDetailsReader = null;
    private BufferedReader employeeDesignationDetailsReader = null;
    private BufferedReader employeeUnderMeReader = null;
    private BufferedReader myMentorReader = null;
           
    
    public EmployeeDatabase() throws IOException {
        employeeDetailsMap = new HashMap<>();
        employeeLoginDetailsMap = new HashMap<>();
        employeeDesignationDetailsMap = new HashMap<>();
        employeeUnderMeMap = new HashMap<>();
        myMentorMap = new HashMap<>();
        try {
            employeeDetailsReader = new BufferedReader(new FileReader("/home/shubham/Desktop/employeeDetails.txt"));
            employeeDesignationDetailsReader = new BufferedReader(new FileReader("/home/shubham/Desktop/employeeDesignationDetails.txt"));
            employeeLoginDetailsReader = new BufferedReader(new FileReader("/home/shubham/Desktop/employeeLoginDetails.txt"));
            employeeUnderMeReader = new BufferedReader(new FileReader("/home/shubham/Desktop/employeeUnderMe.txt"));
            myMentorReader = new BufferedReader(new FileReader("/home/shubham/Desktop/myMentor.txt"));
            
            
        } catch(IOException e) {
            System.out.println("File Exception in Reading EmployeeDetails File");
        }
    }
    
    /**
    * This Method will read the information from employeeLoginDetails.txt File.
    * And will put all the information into employeeLoginDetailsMap. 
    */
    public void initializeEmployeeLoginDetailsMap() throws IOException {
        
        String line;
        int employeeID = -1, pin = -1;
        try {
            
            while ((line = employeeLoginDetailsReader.readLine()) != null) {     
            
              //System.out.println(line + line.length());
              String data = "";
              int countPipeInALine = 0;
              for(int indexOfLine = 0; indexOfLine < line.length(); indexOfLine++)
              {
                  if(line.charAt(indexOfLine) == '|')
                  {
                      if(countPipeInALine==0)
                      {
                          employeeID = Integer.parseInt(data);
                          data = "";
                          countPipeInALine++;
                          continue;
                      }
                  }
                  else
                  {
                      data += line.charAt(indexOfLine);
                      if(indexOfLine == line.length()-1)
                      {
                          pin = Integer.parseInt(data);
                          data = "";
                      }
                  }
              }
              //System.out.println(employeeID + " " +  pin );
              employeeLoginDetailsMap.put(employeeID, pin);
              
          }
            employeeLoginDetailsReader.close();
        } catch(IOException e) {
            System.out.println("File Exception in Reading EmployeeDetails File");
        }
        
    }
    
    /**
    * This Method will read the information from employeeDetails.txt File.
    * And will put all the information into employeeDetailsMap. 
    */
    public void initializeEmployeeDetailsMap() throws IOException {
        
        String line,firstName = "", lastName = "", designationOfEmployee = "";
        int mPIN = -1, employeeID = -1;
        try {
          
              while ((line = employeeDetailsReader.readLine()) != null) {     
              String data = "";
              int countPipeInALine = 0;
              for(int indexOfLine = 0; indexOfLine < line.length(); indexOfLine++)
              {
                  if(line.charAt(indexOfLine) == '|')
                  {
                      if(countPipeInALine==0)
                      {
                          employeeID = Integer.parseInt(data);
                          data = "";
                          countPipeInALine++;
                          continue;
                      }
                      if(countPipeInALine == 1)
                      {
                          firstName = data;
                          data = "";
                          countPipeInALine++;
                          continue;
                      }
                      if(countPipeInALine == 2)
                      {
                          lastName = data;
                          data = "";
                          countPipeInALine++;
                          continue;
                      }
                      if(countPipeInALine == 3)
                      {
                          designationOfEmployee = data;
                          data = "";
                          countPipeInALine++;
                          continue;
                      }
                  }
                  else
                  {
                      data += line.charAt(indexOfLine);
                      if(indexOfLine == line.length()-1)
                      {
                          mPIN = Integer.parseInt(data);
                          data = "";
                      }
                  }
              }
              //System.out.println(employeeID + " " + firstName + " " + lastName + " " + designationOfEmployee + " " + mPIN);
              Employee employee = null;
              if(designationOfEmployee.equals("CEO"))
              {
                  employee = new Ceo(employeeID, firstName, lastName, designationOfEmployee, mPIN);
              }
              else if(designationOfEmployee.equals("Director"))
              {
                  employee = new Director(employeeID, firstName, lastName, designationOfEmployee, mPIN);
              }
              else if(designationOfEmployee.equals("HR"))
              {
                  employee = new Hr(employeeID, firstName, lastName, designationOfEmployee, mPIN);
              }
              else if(designationOfEmployee.equals("Manager"))
              {
                  employee = new Manager(employeeID, firstName, lastName, designationOfEmployee, mPIN);
              }
              else if(designationOfEmployee.equals("New Joiner")) 
              {
                  employee = new NewJoiner(employeeID, firstName, lastName, designationOfEmployee, mPIN);
              }
              employeeDetailsMap.put(employeeID,employee);
              //line = read.readLine();
              
          }
          employeeDetailsReader.close();
        } catch(IOException e) {
            System.out.println("File Exception in Reading EmployeeDetails File");
        }

    }
    
    /**
    * This Method will read the information from employeeDesignationDetails.txt File.
    * And will put all the information into employeeDesignationDetailsMap. 
    */
    public void initializeEmployeeDesignationDetailsMap() throws IOException {
        
        String line, designationOfEmployee = "";
        int employeeID = -1;
        try {
            
            while ((line = employeeDesignationDetailsReader.readLine()) != null) {     
            
              //System.out.println(line + line.length());
              String data = "";
              int countPipeInALine = 0;
              for(int indexOfLine = 0; indexOfLine < line.length(); indexOfLine++)
              {
                  if(line.charAt(indexOfLine) == '|')
                  {
                      if(countPipeInALine==0)
                      {
                          employeeID = Integer.parseInt(data);
                          data = "";
                          countPipeInALine++;
                          continue;
                      }
                  }
                  else
                  {
                      data += line.charAt(indexOfLine);
                      if(indexOfLine == line.length()-1)
                      {
                          designationOfEmployee = data;
                          data = "";
                      }
                  }
              }
              //System.out.println(employeeID + " " +  designationOfEmployee );
              employeeDesignationDetailsMap.put(employeeID, designationOfEmployee);
              
          }
            employeeDesignationDetailsReader.close();
        } catch(IOException e) {
            System.out.println("File Exception in Reading EmployeeDetails File");
        }
        
    }
    
    /**
    * This Method will read the information from employeeUnderMe.txt File.
    * And will put all the information into employeeUnderMeMap. 
    */
    public void initializeEmployeeUnderMeMap() throws IOException {
        String line;
        int employeeID = -1;
        LinkedList<Integer> listOfUnderEmployee;
        try {
            while((line = employeeUnderMeReader.readLine()) != null) {
                listOfUnderEmployee = new LinkedList<>();
                String data = "";
                int countPipeInALine = 0;
                for(int indexOfLine = 0; indexOfLine < line.length(); indexOfLine++)
                {
                    if(line.charAt(indexOfLine) == '|')
                    {
                        if(countPipeInALine==0)
                        {
                            employeeID = Integer.parseInt(data);
                            data = "";
                            countPipeInALine++;
                            continue;
                        }
                        else
                        {
                            listOfUnderEmployee.add(Integer.parseInt(data));
                            data = "";
                        }
                    }
                    else
                    {
                        data += line.charAt(indexOfLine);
                        if(indexOfLine == line.length()-1)
                        {
                            listOfUnderEmployee.add(Integer.parseInt(data));
                            data = "";
                        }
                    }
                }
                employeeUnderMeMap.put(employeeID,listOfUnderEmployee);
            }
            employeeUnderMeReader.close();
        } catch (IOException e) {
             System.out.println("File Exception in Reading employeeUnderMe File");
        }
        
    }
    
    /**
    * This Method will read the information from myMentor.txt File.
    * And will put all the information into myMentorMap. 
    */
    public void initalizeMyMentorMap() throws IOException {
        String line;
        int employeeID = -1;
        LinkedList<Integer> listOfMyMentor;
        try {
            while((line = myMentorReader.readLine()) != null) {
                listOfMyMentor = new LinkedList<>();
                String data = "";
                int countPipeInALine = 0;
                for(int indexOfLine = 0; indexOfLine < line.length(); indexOfLine++)
                {
                    if(line.charAt(indexOfLine) == '|')
                    {
                        if(countPipeInALine==0)
                        {
                            employeeID = Integer.parseInt(data);
                            data = "";
                            countPipeInALine++;
                            continue;
                        }
                        else
                        {
                            listOfMyMentor.add(Integer.parseInt(data));
                            data = "";
                        }
                    }
                    else
                    {
                        data += line.charAt(indexOfLine);
                        if(indexOfLine == line.length()-1)
                        {
                            listOfMyMentor.add(Integer.parseInt(data));
                            data = "";
                        }
                    }
                }
                myMentorMap.put(employeeID,listOfMyMentor);
            }
            myMentorReader.close();
        } catch (IOException e) {
             System.out.println("File Exception in Reading employeeUnderMe File");
        }
        
    }
    
    public Map<Integer,Integer> getEmployeeLoginDetailsMap() {
        return employeeLoginDetailsMap;
    }
    
    public Map<Integer,Employee> getEmployeeDetailsMap() {
        return employeeDetailsMap;
    }
    
    public Map<Integer,String> getEmployeeDesignationDetailsMap() {
        return employeeDesignationDetailsMap;
    }
    
    public Map<Integer, LinkedList<Integer>> getEmployeeUnderMeMap() {
        return employeeUnderMeMap;
    }
    
    public Map<Integer, LinkedList<Integer>> getMyMentorMap() {
        return myMentorMap;
    }
    
    /**
    * This Method will get the information from employeeDetailsMap.
    * And will write the information into employeeDetails.txt 
    */
    public void printEmployeeDetails(Map<Integer,Employee> employeeDetailsMap) {
        
        PrintWriter employeeDetailsWrite = null;
        FileWriter fileWrite = null;
        try{
            fileWrite = new FileWriter("/home/shubham/Desktop/employeeDetails.txt");
            employeeDetailsWrite = new PrintWriter(fileWrite);
            for(Map.Entry<Integer,Employee> myMap : employeeDetailsMap.entrySet())
            {
                employeeDetailsWrite.print(myMap.getKey() + "|" + myMap.getValue().getFirstName() + "|" + myMap.getValue().getLastName() + "|" + myMap.getValue().getDesignation() + "|" + myMap.getValue().getPin() + "\n");
            }
            //System.out.println(employeeId + "|" + firstName + "|" + lastName + "|" + designation + "|" + pin);
            
            employeeDetailsWrite.close();
            fileWrite.close();
        } catch(IOException e) {
            System.out.println("reading file error");
        }
        
    }
    
    /**
    * This Method will get the information from employeeLoginDetailsMap.
    * And will write the information into employeeLoginDetails.txt 
    */
    public void printEmployeeLoginDetails(Map<Integer, Integer> employeeLoginDetailsMap) {
        
        PrintWriter employeeLoginDetailsWrite = null;
        FileWriter fileWrite = null;
        try {
            fileWrite = new FileWriter("/home/shubham/Desktop/employeeLoginDetails.txt");
            employeeLoginDetailsWrite = new PrintWriter(fileWrite);
            for(Map.Entry<Integer,Integer> myMap : employeeLoginDetailsMap.entrySet()) 
            {
                employeeLoginDetailsWrite.print(myMap.getKey() + "|" + myMap.getValue() + "\n");
            }
            fileWrite.close();
            employeeLoginDetailsWrite.close();
            
            
        } catch(IOException e) {
            System.out.println("reading file error");
        }
    }
    
    /**
    * This Method will get the information from employeeDesignationDetailsMap.
    * And will put write the information into employeeDesignationDetails.txt 
    */
    public void printEmployeeDesignationDetails(Map<Integer, String> employeeDesignationDetailsMap) {
        
        PrintWriter employeeDesignationDetailsWrite = null;
        FileWriter fileWrite = null;
        try {
            fileWrite = new FileWriter("/home/shubham/Desktop/employeeDesignationDetails.txt");
            employeeDesignationDetailsWrite = new PrintWriter(fileWrite);
            for(Map.Entry<Integer,String> myMap : employeeDesignationDetailsMap.entrySet()) 
            {
                employeeDesignationDetailsWrite.print(myMap.getKey() + "|" + myMap.getValue() + "\n");
            }
            
            fileWrite.close();
            employeeDesignationDetailsWrite.close();
            
            
        } catch(IOException e) {
            System.out.println("reading file error");
        }
    }
    
    
    /**
    * This Method will get the information from employeeUnderMeMap.
    * And will put write the information into employeeUnderMe.txt 
    */  
    public void printEmployeeUnderMe(Map<Integer,LinkedList<Integer>> employeeUnderMeMap) {
        PrintWriter employeeUnderMeWrite = null;
        FileWriter fileWrite = null;
        try {
            fileWrite = new FileWriter("/home/shubham/Desktop/employeeUnderMe.txt");
            employeeUnderMeWrite = new PrintWriter(fileWrite);
            //employeeDesignationDetailsWrite.print(employeeId + "|" + designationOfEmployee + "\n");
            for(Map.Entry<Integer, LinkedList<Integer>> myMap : employeeUnderMeMap.entrySet()) 
            {
                employeeUnderMeWrite.print(myMap.getKey());
                LinkedList list = myMap.getValue();
                for(int index = 0; index < list.size(); index++) 
                {
                    employeeUnderMeWrite.print("|" + list.get(index));
                }
                employeeUnderMeWrite.print("\n");
            }
            fileWrite.close();
            employeeUnderMeWrite.close();
            
            
        } catch(IOException e) {
            System.out.println("reading file error");
        }
    }
    
    /**
    * This Method will get the information from myMentorMap.
    * And will put write the information into myMentortxt 
    */
    public void printMyMentor(Map<Integer,LinkedList<Integer>> myMentorMap) {
        
        PrintWriter myMentorWrite = null;
        FileWriter fileWrite = null;
        try {
            fileWrite = new FileWriter("/home/shubham/Desktop/myMentor.txt");
            myMentorWrite = new PrintWriter(fileWrite);
            //employeeDesignationDetailsWrite.print(employeeId + "|" + designationOfEmployee + "\n");
            for(Map.Entry<Integer, LinkedList<Integer>> myMap : myMentorMap.entrySet()) 
            {
                myMentorWrite.print(myMap.getKey());
                LinkedList list = myMap.getValue();
                for(int index = 0; index < list.size(); index++) 
                {
                    myMentorWrite.print("|" + list.get(index));
                }
                myMentorWrite.print("\n");
            }
            fileWrite.close();
            myMentorWrite.close();
            
            
        } catch(IOException e) {
            System.out.println("reading file error");
        }
    }
    
    /**
    * This Method will put all the info into map just in one go.
    */
    public void initializeDatabase() {
        try {
            initalizeMyMentorMap();
            initializeEmployeeDesignationDetailsMap();
            initializeEmployeeDetailsMap();
            initializeEmployeeLoginDetailsMap();
            initializeEmployeeUnderMeMap();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
    * This Method will write all the info into File just in one go.
    */
    public void printDatabase()
    {
        printEmployeeDetails(employeeDetailsMap);
        printEmployeeLoginDetails(employeeLoginDetailsMap);
        printEmployeeDesignationDetails(employeeDesignationDetailsMap);
        printEmployeeUnderMe(employeeUnderMeMap);
        printMyMentor(myMentorMap);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       /*public void initialize() throws IOException {
        Employee employee;
        
        employee = new Ceo("Amit", "Jain", "CEO", 1234);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "CEO");
        
        employee = new Director("Rajesh", "Gupta", "Director", 5678);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Director");
        
        employee = new Director("Shashank", "Gupta", "Director", 7728);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Director");
        
        employee = new Director("Shubham", "Kasaudhan", "Director", 6789);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Director");
        
        employee = new Director("Manish", "Jain", "Director", 4567);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Director");
        
        employee = new Hr("Ishita", "Kalra", "HR", 2345);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "HR");
        
        employee = new Hr("Meghna", "Gupta", "HR", 3456);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "HR");
        
        employee = new Hr("Pooja", "pilania", "HR", 9876);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "HR");
        
        employee = new Manager("Shubham", "Mittal", "Manager", 8765);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Manager");
        
        employee = new Manager("Lokesh", "Kaushik", "Manager", 7654);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Manager");
        
        employee = new Manager("Yashokirti", "Soni", "Manager", 6543);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "Manager");
        
        employee = new NewJoiner("Sanaa", "Wadood", "New Joiner", 5432);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "New Joiner");
        
        employee = new NewJoiner("Vijay", "Sharma", "New Joiner", 4321);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "New Joiner");
        
        employee = new NewJoiner("Akash", "Basudevan", "New Joiner", 3210);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "New Joiner");
        
        employee = new NewJoiner("Akshay", "Modi", "New Joiner", 2100);
        getEmployeeDetailsMap().put(employee.getEmployeeId(), employee);
        getEmployeeLoginDetailsMap().put(employee.getEmployeeId(), employee.getPin());
        getEmployeeDesignationDetailsMap().put(employee.getEmployeeId(), "New Joiner");
        
        putInFile();
    }
    public void putInFile() throws IOException{
        
        FileWriter employeeDetailsWrite = null;
        FileWriter employeeLoginDetailsWrite = null;
        FileWriter employeeDesignationDetailsWrite = null;
        FileWriter employeeUnderMeWrite = null;
        FileWriter myMentorWrite = null;
        
        try {
            employeeDetailsWrite = new FileWriter("/home/shubham/Desktop/employeeDetails.txt",true);
            employeeLoginDetailsWrite = new FileWriter("/home/shubham/Desktop/employeeLoginDetails.txt",true);
            employeeDesignationDetailsWrite = new FileWriter("/home/shubham/Desktop/employeeDesignationDetails.txt",true);
            employeeUnderMeWrite =  new FileWriter("/home/shubham/Desktop/employeeUnderMe.txt",true);
            myMentorWrite = new FileWriter("/home/shubham/Desktop/myMentor.txt",true);
            for (Map.Entry<Integer,Employee> myMap : getEmployeeDetailsMap().entrySet())
            {
                employeeDetailsWrite.write(myMap.getKey() + "|" + myMap.getValue().getFirstName() + "|" + myMap.getValue().getLastName() + "|" + myMap.getValue().getDesignation() + "|" + myMap.getValue().getPin() + "\n");
            }
            for(Map.Entry<Integer,Integer> myMap : getEmployeeLoginDetailsMap().entrySet()) 
            {
                employeeLoginDetailsWrite.write(myMap.getKey() + "|" + myMap.getValue() + "\n");
            }
            for(Map.Entry<Integer,String> myMap : getEmployeeDesignationDetailsMap().entrySet())
            {
                employeeDesignationDetailsWrite.write(myMap.getKey() + "|" + myMap.getValue() + "\n");
            }
            for(Map.Entry<Integer,LinkedList<Integer>> myMap : getEmployeeUnderMeMap().entrySet())
            {
                LinkedList<Integer> list = myMap.getValue();
                System.out.println("mnvfb");
                employeeUnderMeWrite.write(myMap.getKey() + "|");
                for(int index = 0; index < list.size(); index++)
                {
                    employeeUnderMeWrite.write(list.get(index) + "|");
                }
                employeeUnderMeWrite.write("\n");
            }
            for(Map.Entry<Integer,LinkedList<Integer>> myMap : getMyMentorMap().entrySet())
            {
                System.out.println("Here");
                LinkedList<Integer> list = myMap.getValue();
                myMentorWrite.write(myMap.getKey() + "|");
                for(int index = 0; index < list.size(); index++)
                {
                    myMentorWrite.write(list.get(index) + "|");
                }
                myMentorWrite.write("\n");
            }
    
        } catch(IOException e) {
            System.out.print("File Exception");
        }
        finally {
         
         if (employeeDetailsWrite != null) {
            employeeDetailsWrite.close();
         }
         if(employeeDesignationDetailsWrite != null) {
             employeeDesignationDetailsWrite.close();
         }
         if(employeeLoginDetailsWrite != null) {
             employeeLoginDetailsWrite.close();
         }
         if(employeeUnderMeWrite != null) 
         {
             employeeUnderMeWrite.close();
         }
         if(myMentorWrite != null)
         {
             myMentorWrite.close();
         }
      }       
        
    }*/
    
    
}
