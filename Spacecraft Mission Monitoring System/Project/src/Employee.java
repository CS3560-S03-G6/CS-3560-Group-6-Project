
public class Employee {
    private int employeeID;
    private String name;
    private String userName;
    private String password;
    private String eMail;
    private int phoneNumber;
    private String location;

    //Constructor that takes in all necessary fields
    public Employee(int employeeID, String name, String userName, String password, String eMail, int phoneNumber, String location){
        this.employeeID = employeeID;
        this.name = name;
        this.userName = userName;
        this.password = password;
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
    
    //Getter for userName
    public String getUserName() {
        return userName;
    }

    //Setter for role
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
        //Getter for password
    public String getPassword() {
        return password;
    }

    //Setter for role
    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return getName();
    }
}