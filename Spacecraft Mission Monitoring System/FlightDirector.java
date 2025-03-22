public class FlightDirector extends Employee {
    private int yearsOfExperience;

    // Constructor that takes in all necessary fields
    public FlightDirector(int employeeID, String name, String role, String eMail, int phoneNumber, String location, int yearsOfExperience){
        super(employeeID, name, role, eMail, phoneNumber, location);
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getter for yearsOfExperience
    public int getYearsOfExperience(){
        return yearsOfExperience;
    }

    // Setter for yearsOfExperience
    public void setYearsOfExperience(int yearsOfExperience){
        this.yearsOfExperience = yearsOfExperience;
    }

    // Schedules a future maneuver for a given mission
    // Parameters: missionID and a Maneuver object with future execution details
    public void scheduleFutureManeuver(String missionID, Maneuver futureManeuver) {
        // TODO: Add logic to add a scheduled maneuver to the mission
    }
    
    // Schedules an immediate maneuver for a given mission
    // Parameters: missionID and a Maneuver object with immediate execution details
    public void scheduleImmediateManeuver(String missionID, Maneuver immediateManeuver) {
        // TODO: Add logic to notify external system and log this as an immediate maneuver
    }
}
