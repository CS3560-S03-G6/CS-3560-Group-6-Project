
import java.util.List;

public class Maneuver {
    private int maneuverID;
    private int missionID;
    private Integer employeeID; // can be null if logged by a crew member
    private Integer crewID;     // can be null if logged by an employee

    private String maneuverType;
    private String maneuverDescription;
    private String executionTime; // stored as string for simplicity
    private int fuelCost;
    private String status;
    private String loggedTime;
    private String loggedBy;

    // constructor
    public Maneuver(int maneuverID, int missionID, Integer employeeID, Integer crewID,
                    String maneuverType, String maneuverDescription, String executionTime,
                    int fuelCost, String status, String loggedTime, String loggedBy) {
        this.maneuverID = maneuverID;
        this.missionID = missionID;
        this.employeeID = employeeID;
        this.crewID = crewID;
        this.maneuverType = maneuverType;
        this.maneuverDescription = maneuverDescription;
        this.executionTime = executionTime;
        this.fuelCost = fuelCost;
        this.status = status;
        this.loggedTime = loggedTime;
        this.loggedBy = loggedBy;
    }

    // getter and setter for maneuverID
    public int getManeuverID() {
        return maneuverID;
    }

    public void setManeuverID(int maneuverID) {
        this.maneuverID = maneuverID;
    }

    // getter and setter for missionID
    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    // getter and setter for employeeID
    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    // getter and setter for crewID
    public Integer getCrewID() {
        return crewID;
    }

    public void setCrewID(Integer crewID) {
        this.crewID = crewID;
    }

    // getter and setter for maneuverType
    public String getManeuverType() {
        return maneuverType;
    }

    public void setManeuverType(String maneuverType) {
        this.maneuverType = maneuverType;
    }

    // getter and setter for maneuverDescription
    public String getManeuverDescription() {
        return maneuverDescription;
    }

    public void setManeuverDescription(String maneuverDescription) {
        this.maneuverDescription = maneuverDescription;
    }

    // getter and setter for executionTime
    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    // getter and setter for fuelCost
    public int getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(int fuelCost) {
        this.fuelCost = fuelCost;
    }

    // getter and setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // getter and setter for loggedTime
    public String getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(String loggedTime) {
        this.loggedTime = loggedTime;
    }

    // getter and setter for loggedBy
    public String getLoggedBy() {
        return loggedBy;
    }

    public void setLoggedBy(String loggedBy) {
        this.loggedBy = loggedBy;
    }

    // shows a short summary of the maneuver info
    public void displayManeuverSummary() {
        System.out.println("maneuver id: " + maneuverID);
        System.out.println("type: " + maneuverType);
        System.out.println("description: " + maneuverDescription);
        System.out.println("execution time: " + executionTime);
        System.out.println("fuel cost: " + fuelCost);
        System.out.println("status: " + status);
    }
    
    public String getManeuverDetails(){
          return "Maneuver ID: " + maneuverID + "\n"
         + "Type: " + maneuverType + "\n"
         + "Description: " + maneuverDescription + "\n"
         + "Execution Time: " + executionTime + "\n"
         + "Fuel Cost: " + fuelCost + "\n"
         + "Status: " + status;
    }
    
    public String toDisplayString(List<Mission> allMissions) {
    String missionName = "Unknown";
    for (Mission m : allMissions) {
        if (m.getMissionID() == this.missionID) {
            missionName = m.getMissionName();
            break;
        }
    }
    return "#" + this.maneuverID + " (Mission " + missionName + ") - " + this.maneuverType + ", " + this.executionTime;
}
    @Override
    public String toString(){
        return "Maneuver #" + this.maneuverID;
    }

    // lets someone update a maneuver's key details
    public void updateManeuver(String newDescription, String newTime, int newFuelCost, String newStatus) {
        this.maneuverDescription = newDescription;
        this.executionTime = newTime;
        this.fuelCost = newFuelCost;
        this.status = newStatus;
    }
    
}