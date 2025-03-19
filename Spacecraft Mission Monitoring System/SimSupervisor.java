
public class SimSupervisor extends Employee {
    private int yearsOfExperience;

    //Constructor that takes in all necessary fields
    public SimSupervisor(int employeeID, String name, String role, String eMail, int phoneNumber, String location, int yearsOfExperience){
        super(employeeID, name, role, eMail, phoneNumber, location);
        this.yearsOfExperience = yearsOfExperience;
    }

    //Getter for yearsOfExperience
    public int getYearsOfExperience(){
        return yearsOfExperience;
    }

    //Setter for yearsOfExperience
    public void setYearsOfExperience(int yearsOfExperience){
        this.yearsOfExperience = yearsOfExperience;
    }
}
