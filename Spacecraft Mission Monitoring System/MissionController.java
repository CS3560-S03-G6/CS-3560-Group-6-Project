
public class MissionController extends Employee {
    private int authorityLevel;

    //Constructor that takes in all necessary fields
    public MissionController(int employeeID, String name, String role, String eMail, int phoneNumber, String location, int authorityLevel){
        super(employeeID, name, role, eMail, phoneNumber, location);
        this.authorityLevel = authorityLevel;
    }

    //Getter for authorityLevel
    public int getAuthorityLevel(){
        return authorityLevel;
    }

    //Setter for authorityLevel
    public void setAuthorityLevel(int authorityLevel){
        this.authorityLevel = authorityLevel;
    }
}
