
public class Employee {
    private int employeeID;
    private String name;
    private String role;
    private String eMail;
    private int phoneNumber;
    private String location;

    //Constructor that takes in all necessary fields
    public Employee(int employeeID, String name, String role, String eMail, int phoneNumber, String location){
        this.employeeID = employeeID;
        this.name = name;
        this.role = role;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    //Getter for employeeID
    public int getEmployeeID(){
        return employeeID;
    }

    //Setter for employeeID
    public void setEmployeeID(int employeeID){
        this.employeeID = employeeID;
    }

    //Getter for name
    public String getName() {
        return name;
    }

    //Setter for name
    public void setName(String name) {
        this.name = name;
    }
    
    //Getter for role
    public String getRole() {
        return role;
    }

    //Setter for role
    public void setRole(String role) {
        this.role = role;
    }
    
    //Getter for eMail
    public String getEMail() {
        return eMail;
    }

    //Setter for eMail
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }
    
    //Getter for phoneNumber
    public int getPhoneNumber() {
        return phoneNumber;
    }

    //Setter for phoneNumber
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    //Getter for location
    public String getLocation() {
        return location;
    }

    //Setter for location
    public void setLocation(String location) {
        this.location = location;
    }
}