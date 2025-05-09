public class MissionController extends Employee {
    private int authorityLevel;

    // constructor that takes all necessary fields from Employee and authorityLevel
    public MissionController(int employeeID, String name, String userName, String password, String workEmail, int phoneNumber, String location, int authorityLevel) {
        super(employeeID, name, userName, password, workEmail, phoneNumber, location);
        this.authorityLevel = authorityLevel;
    }

    // getter for authorityLevel
    public int getAuthorityLevel() {
        return authorityLevel;
    }

    // setter for authorityLevel
    public void setAuthorityLevel(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    // this class doesn’t really need any special methods
    // mission controllers interact with maneuvers using the UI
    // they are tracked in the Maneuver table using their employeeID
    // so we just store their info here for identity/role purposes
}  