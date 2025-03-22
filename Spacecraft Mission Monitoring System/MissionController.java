
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

    // Additional methods

    /**
     * Records basic mission info in the system.
     */
    public void enterMissionInformation(Mission mission) {
        // Logic to store new mission information
    }

    /**
     * Logs a non-emergency maneuver.
     */
    public void logScheduledManeuver(Maneuver maneuver) {
        // Logic to store scheduled maneuver
    }

    /**
     * Logs a real-time or emergency maneuver.
     */
    public void logImmediateManeuver(Maneuver maneuver) {
        // Logic to store immediate maneuver
    }

    /**
     * Terminates a mission, specifying a reason.
     */
    public void terminateMission(int missionID, String reason) {
        // Logic to terminate mission
    }
}
