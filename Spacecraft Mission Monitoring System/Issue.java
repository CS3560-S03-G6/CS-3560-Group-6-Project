
public class Issue {
    private int issueID;
    private int missionID;
    private String issueType;
    private int detectionTime;
    private int severityLevel;
    private boolean alertTriggered;
    private String[] alertRecipients;
    private String resolutionStatus;

    //Constructor that takes in all necessary fields
    public Issue(int issueID, int missionID, String issueType, int detectionTime, int severityLevel, boolean alertTriggered, String[] alertRecipients, String resolutionStatus){
        this.issueID = issueID;
        this.missionID = missionID;
        this.issueType = issueType;
        this.detectionTime = detectionTime;
        this.severityLevel = severityLevel;
        this.alertTriggered = alertTriggered;
        this.alertRecipients = alertRecipients;
        this.resolutionStatus = resolutionStatus;
    }

    //Getter for issueID
    public int getIssueID(){
        return issueID;
    }

    //Setter for issueID
    public void setIssueID(int issueID){
        this.issueID = issueID;
    }

    //Getter for missionID
    public int getMissionID(){
        return missionID;
    }

    //Setter for missionID
    public void setMissionID(int missionID){
        this.missionID = missionID;
    }

    //Getter for issueType
    public String getIssueType(){
        return issueType;
    }

    //Setter for issueType
    public void setIssueType(String issueType){
        this.issueType = issueType;
    }

    //Getter for detectionTime
    public int getDetectionTime(){
        return detectionTime;
    }

    //Setter for detectionTime
    public void setDetectionTime(int detectionTime){
        this.detectionTime = detectionTime;
    }

    //Getter for severityLevel
    public int getSeverityLevel(){
        return severityLevel;
    }

    //Setter for severityLevel
    public void setSeverityLevel(int severityLevel){
        this.severityLevel = severityLevel;
    }

    //Getter for alertTriggered
    public boolean getAlertTriggered(){
        return alertTriggered;
    }

    //Setter for alertTriggered
    public void setAlertTriggered(boolean alertTriggered){
        this.alertTriggered = alertTriggered;
    }

    //Getter for alertRecipients
    public String[] getAlertRecipients(){
        return alertRecipients;
    }

    //Setter for alertRecipients
    public void setAlertRecipients(String[] alertRecipients){
        this.alertRecipients = alertRecipients;
    }

    //Getter for resolutionStatus
    public String getResolutionStatus(){
        return resolutionStatus;
    }

    //Setter for resolutionStatus
    public void setResolutionStatus(String resolutionStatus){
        this.resolutionStatus = resolutionStatus;
    }
}
