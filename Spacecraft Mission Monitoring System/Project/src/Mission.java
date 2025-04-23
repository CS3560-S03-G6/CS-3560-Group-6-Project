
import java.util.ArrayList;
import java.util.List;


public class Mission {
    private int missionID;
    private String missionName;
    private String missionType;
    private String launchDate;
    private String missionStatus;
    private String missionObjectives;
    private int initialFuelLevel;
    private String initialLocation;
    private String terminationDate;

    // mission is tied to one spacecraft
    private Spacecraft spacecraft;

    // list of reports for this mission
    private List<MissionReport> reports;

    // constructor for mission
    public Mission(String missionName, String missionType, String launchDate, String missionStatus,
                   String missionObjectives, int initialFuelLevel, String initialLocation, String terminationDate) {
        this.missionName = missionName;
        this.missionType = missionType;
        this.launchDate = launchDate;
        this.missionStatus = missionStatus;
        this.missionObjectives = missionObjectives;
        this.initialFuelLevel = initialFuelLevel;
        this.initialLocation = initialLocation;
        this.terminationDate = terminationDate;
        this.reports = new ArrayList<>(); // start with empty report list
    }

    // mission id getter
    public int getMissionID() {
        return missionID;
    }

    // mission id setter
    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    // mission name getter
    public String getMissionName() {
        return missionName;
    }

    // mission name setter
    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    // mission type getter
    public String getMissionType() {
        return missionType;
    }

    // mission type setter
    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    // launch date getter
    public String getLaunchDate() {
        return launchDate;
    }

    // launch date setter
    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    // mission status getter
    public String getMissionStatus() {
        return missionStatus;
    }

    // mission status setter
    public void setMissionStatus(String missionStatus) {
        this.missionStatus = missionStatus;
    }

    // mission objectives getter
    public String getMissionObjectives() {
        return missionObjectives;
    }

    // mission objectives setter
    public void setMissionObjectives(String missionObjectives) {
        this.missionObjectives = missionObjectives;
    }

    // initial fuel level getter
    public int getInitialFuelLevel() {
        return initialFuelLevel;
    }

    // initial fuel level setter
    public void setInitialFuelLevel(int initialFuelLevel) {
        this.initialFuelLevel = initialFuelLevel;
    }

    // initial location getter
    public String getInitialLocation() {
        return initialLocation;
    }

    // initial location setter
    public void setInitialLocation(String initialLocation) {
        this.initialLocation = initialLocation;
    }

    // termination date getter
    public String getTerminationDate() {
        return terminationDate;
    }

    // termination date setter
    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    // set the spacecraft that's tied to this mission
    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    // get the spacecraft for this mission
    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    // add a report to this mission's report list
    public void addMissionReport(MissionReport report) {
        this.reports.add(report);
    }

    // return the list of reports
    public List<MissionReport> getReports() {
        return reports;
    }

    // lets user update mission details like name, type, etc
    public void updateMissionDetails(String newName, String newType, String newObjective,
                                     int newFuelLevel, String newLocation) {
        this.missionName = newName;
        this.missionType = newType;
        this.missionObjectives = newObjective;
        this.initialFuelLevel = newFuelLevel;
        this.initialLocation = newLocation;
    }

    // ends the mission and sets termination date
    public void terminateMission(String date) {
        this.terminationDate = date;
        this.missionStatus = "Terminated";
    }

    // checks if fuel is getting dangerously low (less than 15%)
    public boolean isFuelCritical(int currentFuelRemaining) {
        return currentFuelRemaining < (initialFuelLevel * 0.15);
    }

    // gives a short summary of the mission for quick viewing/searches
    public String displayMissionSummary() {
        return "Mission ID: " + missionID +
               ", Name: " + missionName +
               ", Type: " + missionType +
               ", Status: " + missionStatus +
               ", Fuel: " + initialFuelLevel + " units";
    }

    // shows all mission details + spacecraft + reports
    public String displayFullMissionDetails() {
        StringBuilder sb = new StringBuilder();

        // mission basic info
        sb.append("Mission Details:\n");
        sb.append("MissionID: ").append(missionID).append("\n");
        sb.append("Name: ").append(missionName).append("\n");
        sb.append("Type: ").append(missionType).append("\n");
        sb.append("Status: ").append(missionStatus).append("\n");
        sb.append("Launch Date: ").append(launchDate).append("\n");
        sb.append("Objectives: ").append(missionObjectives).append("\n");
        sb.append("Initial Fuel: ").append(initialFuelLevel).append(" units\n");
        sb.append("Initial Location: ").append(initialLocation).append("\n");

        // if there's spacecraft tied to this mission, show it
        if (spacecraft != null) {
            sb.append("\nSpacecraft Info:\n");
            sb.append(spacecraft.displaySpacecraftDetails()).append("\n");
        }

        // if this mission has reports, show them all
        if (reports != null && !reports.isEmpty()) {
            sb.append("\nMission Reports:\n");
            for (MissionReport r : reports) {
                sb.append(r.displayReportSummary()).append("\n");
            }
        } else {
            sb.append("\nNo reports available for this mission.\n");
        }

        return sb.toString();
    }
    
    @Override
    public String toString() {
        return missionName;
}

}