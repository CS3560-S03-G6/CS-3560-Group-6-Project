
public class FlightDirector extends Employee {
    private int authorityLevel;

    //Constructor that takes in all necessary fields
    public FlightDirector(int employeeID, String name, String role, String eMail, int phoneNumber, String location, int authorityLevel){
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
     * Logs a scheduled maneuver under the flight director's authorization.
     */
    public void logScheduledManeuver(Maneuver maneuver) {
        // Logic to store scheduled maneuver
    }

    /**
     * Logs an immediate maneuver authorized by the flight director.
     */
    public void logImmediateManeuver(Maneuver maneuver) {
        // Logic to store emergency maneuver
    }

    /**
     * Terminates a mission based on status or critical decision.
     */
    public void terminateMission(int missionID, String reason) {
        // Logic to terminate mission
    }
}
