
import com.sun.jdi.connect.spi.Connection;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.sql.*;

public class SystemInterface extends JFrame {

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new TestGUI());

        SystemInterface example = new SystemInterface("Spacecraft Mission Control System");

    }

    private List<Mission> missions;

    String title = "Untitled";
    JFrame frame;

    SystemInterface() {
        JFrame frame = new JFrame(title);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 300);

        frame.setVisible(true);
    }

    SystemInterface(String newTitle) {

        this.frame = new JFrame();
        this.title = newTitle;

        try {
            missions = SQLDatabase.getAllMissions(); // ✅ Load mission data here
        } catch (Exception e) {
            System.out.println("❌ Failed to load missions: " + e.getMessage());
            missions = new ArrayList<>(); // fallback to avoid null errors
        }

        frame = new JFrame(newTitle);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new FlowLayout());

        JButton button = new JButton("Test Button!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Test Button was pressed!!!");
            }
        });
        frame.add(button);

        JMenuBar menuBar = new JMenuBar();

        JMenu missionMenu = new JMenu("Mission");
        JMenu maneuverMenu = new JMenu("Maneuver");
        JMenu crewMenu = new JMenu("Crew");
        JMenu spacecraftMenu = new JMenu("Spacecraft");

        menuBar.add(missionMenu);
        menuBar.add(maneuverMenu);
        menuBar.add(crewMenu);
        menuBar.add(spacecraftMenu);

        JMenuItem addMission = new JMenuItem("Add a new mission");
        JMenuItem updateMission = new JMenuItem("Update a mission");
        JMenuItem searchMission = new JMenuItem("Search a mission");
        JMenuItem terminateMission = new JMenuItem("Terminate a mission");
        JMenuItem scheduleImmediateManeuver = new JMenuItem("Schedule an immediate maneuver");
        JMenuItem scheduleFutureManeuver = new JMenuItem("Schedule a future maneuver");

        missionMenu.add(addMission);
        missionMenu.add(updateMission);
        missionMenu.add(searchMission);
        missionMenu.add(terminateMission);
        maneuverMenu.add(scheduleImmediateManeuver);
        maneuverMenu.add(scheduleFutureManeuver);
        updateMission.setEnabled(false);
        terminateMission.setEnabled(false);
        scheduleImmediateManeuver.setEnabled(false);
        scheduleFutureManeuver.setEnabled(false);

        addMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMissionDialog();
            }
        });

        searchMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMissionDialog();
            }
        });

        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    /**
 * Displays a modal dialog for adding a new mission.
 * Includes form fields for all mission attributes and performs validation.
 * - Checks for duplicate mission names.
 * - Validates employee ID existence in the database.
 * - Delegates database insertion to SQLDatabase.insertMission().
 * - Refreshes mission list upon success.
 */
private void addMissionDialog() {
    JDialog dialog = new JDialog(this, "Add a Mission", true);
    dialog.setSize(400, 450);
    dialog.setLocationRelativeTo(frame);
    dialog.setLayout(new BorderLayout());
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    // ========== Label + Input Panels ==========
    JPanel labelPanel = new JPanel(new GridLayout(0, 1));
    JPanel fieldPanel = new JPanel(new GridLayout(0, 1));

    // Labels and text field definitions for mission attributes
    String[] fieldLabels = {
        "Employee ID:", "Mission Name:", "Mission Type:", "Launch Date (YYYY-MM-DD):",
        "Status:", "Objectives:", "Initial Fuel Level:", "Initial Location:", "Termination Date (YYYY-MM-DD):"
    };

    JTextField empIDField = new JTextField();
    JTextField missionNameField = new JTextField();
    JTextField missionTypeField = new JTextField();
    JTextField launchDateField = new JTextField();
    JTextField statusField = new JTextField();
    JTextField objectivesField = new JTextField();
    JTextField fuelLevelField = new JTextField();
    JTextField locationField = new JTextField();
    JTextField terminationDateField = new JTextField();

    // Group fields into array for easy iteration
    JTextField[] fields = {
        empIDField, missionNameField, missionTypeField, launchDateField,
        statusField, objectivesField, fuelLevelField, locationField, terminationDateField
    };

    for (String label : fieldLabels) {
        labelPanel.add(new JLabel(label));
    }
    for (JTextField field : fields) {
        fieldPanel.add(field);
    }

    JPanel centerPanel = new JPanel(new GridLayout(0, 2));
    centerPanel.add(labelPanel);
    centerPanel.add(fieldPanel);
    dialog.add(centerPanel, BorderLayout.CENTER);

    // ========== Button Panel ==========
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
    JButton submitButton = new JButton("Submit");
    JButton exitButton = new JButton("Exit");

    exitButton.addActionListener(e -> dialog.dispose());

    // ===== Submit Button Logic =====
    submitButton.addActionListener(e -> {
        try {
            int employeeID = Integer.parseInt(empIDField.getText().trim());
            String missionName = missionNameField.getText().trim();

            // Check for duplicate mission name
            boolean exists = missions.stream()
                    .anyMatch(m -> m.getMissionName().equalsIgnoreCase(missionName));

            if (exists) {
                JOptionPane.showMessageDialog(dialog, "A mission with that name already exists.", "Duplicate Mission", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Extract other inputs
            String missionType = missionTypeField.getText().trim();
            String launchDate = launchDateField.getText().trim();
            String status = statusField.getText().trim();
            String objectives = objectivesField.getText().trim();
            int fuelLevel = Integer.parseInt(fuelLevelField.getText().trim());
            String location = locationField.getText().trim();
            String terminationDate = terminationDateField.getText().trim();

            // Insert mission via SQLDatabase helper
            boolean success = SQLDatabase.insertMission(
                    employeeID, missionName, missionType, launchDate, status,
                    objectives, fuelLevel, location, terminationDate
            );

            if (success) {
                missions = SQLDatabase.getAllMissions(); // Refresh mission list
                JOptionPane.showMessageDialog(dialog, "Mission successfully added!");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Employee ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Employee ID and Fuel Level must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    buttonPanel.add(submitButton);
    buttonPanel.add(exitButton);
    dialog.add(buttonPanel, BorderLayout.SOUTH);
    dialog.setVisible(true);
}



    /**
     * Displays a modal dialog allowing the user to search for missions by name,
     * view all available missions, and select one to view full mission details.
     */
    public void searchMissionDialog() {
        // Create the modal dialog window
        JDialog dialog = new JDialog(this, "Search a Mission", true);
        dialog.setSize(600, 300);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // ================= Top Panel =================
        // Contains: "Search Mission(s):" label, input text field, Search button, View All button
        JPanel upperPanel = new JPanel(new GridLayout(1, 0));
        JLabel searchLabel = new JLabel("Search Mission(s): ");
        JTextField searchTextField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton viewAll = new JButton("View All");

        upperPanel.add(searchLabel);
        upperPanel.add(searchTextField);
        upperPanel.add(searchButton);
        upperPanel.add(viewAll);

        // ================= Middle Panel =================
        // Displays a scrollable list of missions using DefaultListModel and JList
        JPanel middlePanel = new JPanel(new BorderLayout());
        DefaultListModel<Mission> listContents = new DefaultListModel<>();
        JList<Mission> list = new JList<>(listContents);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one mission selectable at a time
        JScrollPane scrollPane = new JScrollPane(list);
        middlePanel.add(scrollPane);

        // Initially load all missions from the 'missions' list into the list view
        for (Mission m : missions) {
            listContents.addElement(m);
        }

        // ================= Search Button Logic =================
        // Filters missions based on whether their name contains the search text
        searchButton.addActionListener(e -> {
            String searchKey = searchTextField.getText().toLowerCase();
            listContents.clear(); // Clear previous list results
            boolean found = false;

            for (Mission m : missions) {
                if (m.getMissionName().toLowerCase().contains(searchKey)) {
                    listContents.addElement(m); // Match found, add to display list
                    found = true;
                }
            }

            // If nothing was found, notify user and reset the list to show all
            if (!found) {
                JOptionPane.showMessageDialog(null, "Search returned no results!");
                viewAll.doClick(); // Show all again
            }
        });

        // ================= View All Button Logic =================
        // Clears any search filter and shows all missions again
        viewAll.addActionListener(e -> {
            listContents.clear();
            for (Mission m : missions) {
                listContents.addElement(m);
            }
        });

        // ================= Bottom Panel =================
        // Contains "Select Entry" and "Cancel" buttons
        JPanel lowerButtonPanel = new JPanel(new GridLayout(1, 0));
        JButton selectButton = new JButton("Select Entry");
        JButton cancelButton = new JButton("Cancel");

        // Select Entry action — shows full details of the selected mission
        selectButton.addActionListener(e -> {
            Mission selected = list.getSelectedValue();
            if (selected != null) {
                JOptionPane.showMessageDialog(this,
                        selected.displayFullMissionDetails(),
                        "Mission Details",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(this, "Please select a mission from the list.");
            }
        });

        // Cancel button action — closes the dialog
        cancelButton.addActionListener(e -> dialog.setVisible(false));

        lowerButtonPanel.add(selectButton);
        lowerButtonPanel.add(cancelButton);

        // ================= Final Assembly =================
        dialog.add(upperPanel, BorderLayout.NORTH);
        dialog.add(middlePanel, BorderLayout.CENTER);
        dialog.add(lowerButtonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true); // Show the modal dialog
    }
}
