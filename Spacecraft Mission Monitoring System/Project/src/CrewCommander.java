public class CrewCommander extends Employee {
    private int crewID;

    public CrewCommander(int employeeID, String name, String userName, String password,
            String workEmail, int phoneNumber, String location, int crewID) {
        super(employeeID, name, userName, password, workEmail, phoneNumber, location);
        this.crewID = crewID;
    }

    public int getCrewID() {
        return crewID;
    }

    public void setCrewID(int crewID) {
        this.crewID = crewID;
    }
}
