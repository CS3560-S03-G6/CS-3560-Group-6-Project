import java.util.ArrayList;
import java.util.List;

public class MissionController extends Employee {
    private int authorityLevel;

    // Constructor that takes in all necessary fields
    public MissionController(int employeeID, String name, String role, String eMail, int phoneNumber, String location, int authorityLevel){
        super(employeeID, name, role, eMail, phoneNumber, location);
        this.authorityLevel = authorityLevel;
    }

    // Getter for authorityLevel
    public int getAuthorityLevel(){
        return authorityLevel;
    }

    // Setter for authorityLevel
    public void setAuthorityLevel(int authorityLevel){
        this.authorityLevel = authorityLevel;
    }

    // Submits a new mission to the system
    // Parameters: Mission object containing all required mission details
    public void enterMissionInfo(Mission mission) {
        // TODO: Add logic to store the new mission in the system
    }

    // Updates the details of an existing mission
    // Parameters: missionID of the mission to update & updated Mission object
    public void updateMissionInfo(String missionID, Mission updatedMission) {
        // TODO: Add logic to find and update the mission with new info
    }

    // Looks up missions based on keywords like mission name, etc.
    // Parameters: search keyword(s)
    // Returns: list of matching Mission objects (empty list if no matches)
    public List<Mission> searchMission(String query) {
        // TODO: Add search logic here
        // If no matches, return empty list so UI can handle the other steps, like prompting the user
        return new ArrayList<>();
    }

    // Retrieves detailed information about a specific mission
    // Parameters: missionID of the selected mission
    // Returns: full Mission object with associated data like maneuvers, spacecraft, etc.
    public Mission retrieveMissionDetails(String missionID) {
        // TODO: Add logic to fetch mission and its associated data
        return null; // just a placeholder
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

    // Updates an existing maneuver for a given mission
    // Parameters: maneuverID and updated Maneuver object
    public void updateManeuver(String maneuverID, Maneuver updatedManeuver) {
        // TODO: Add logic to find and update a specific maneuver
    }

    // Terminates an active mission
    // Parameters: missionID of the mission to terminate
    public void terminateMission(String missionID) {
        // TODO: Update mission status to "Terminated" and archive the mission
    }
} 
