// this class represents a flight director, who is a specialized type of employee
public class FlightDirector extends Employee {
    private int yearsOfExperience;

    // constructor for flight director
    public FlightDirector(int employeeID, String name, String userName, String password, String workEmail, int phoneNumber, String location, int yearsOfExperience) {
        super(employeeID, name, userName, password, workEmail, phoneNumber, location);
        this.yearsOfExperience = yearsOfExperience;
    }

    // getter for years of experience
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    // setter for years of experience
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}