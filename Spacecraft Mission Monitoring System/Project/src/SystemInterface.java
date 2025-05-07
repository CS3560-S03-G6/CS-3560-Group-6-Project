
import com.sun.jdi.connect.spi.Connection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.sql.*;

public class SystemInterface extends JFrame {

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

        try {
            missions = SQLDatabase.getAllMissions(); // ✅ Load mission data here
        } catch (Exception e) {
            System.out.println("❌ Failed to load missions: " + e.getMessage());
            missions = new ArrayList<>(); // fallback to avoid null errors
        }

        this.frame = new JFrame();
        this.title = newTitle;

        frame = new JFrame(newTitle);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new FlowLayout());

        // === IMAGE PANEL ===
        try {
//            ImageIcon imageIcon = new ImageIcon("space_img.png"); // path to your image file
            // Load the original image
            ImageIcon originalIcon = new ImageIcon("space_img.png");

            // Scale it to fit the frame size
            int frameWidth = 500;
            int frameHeight = 400;

            Image scaledImage = originalIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            frame.setLayout(new BorderLayout());
            frame.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("ERROR: Failed to load image: " + e.getMessage());
        }

        JMenuBar menuBar = new JMenuBar();

        JMenu missionMenu = new JMenu("Mission");
        JMenu maneuverMenu = new JMenu("Maneuver");
        JMenu employeeMenu = new JMenu("Employee");
        JMenu systemMenu = new JMenu("System");

        menuBar.add(missionMenu);
        menuBar.add(maneuverMenu);
        menuBar.add(employeeMenu);
        menuBar.add(systemMenu);

        JMenuItem addMission = new JMenuItem("Add a new mission");
        JMenuItem updateMission = new JMenuItem("Update a mission");
        JMenuItem searchMission = new JMenuItem("Search a mission");
        JMenuItem terminateMission = new JMenuItem("Terminate a mission");
        JMenuItem scheduleImmediateManeuver = new JMenuItem("Schedule an immediate maneuver");
        JMenuItem scheduleFutureManeuver = new JMenuItem("Schedule a future maneuver");
        JMenuItem updateManeuver = new JMenuItem("Update a maneuver");
        JMenuItem logPastManeuver = new JMenuItem("Log past maneuver");
        JMenuItem viewEmployees = new JMenuItem("View employees");
        JMenuItem updateEmployee = new JMenuItem("Update employee");
        JMenuItem addEmployee = new JMenuItem("Add employee");
        JMenuItem removeEmployee = new JMenuItem("Remove employee");
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem("Exit");

        missionMenu.add(addMission);
        missionMenu.add(updateMission);
        missionMenu.add(searchMission);
        missionMenu.add(terminateMission);
        maneuverMenu.add(scheduleImmediateManeuver);
        maneuverMenu.add(scheduleFutureManeuver);
        maneuverMenu.add(updateManeuver);
        maneuverMenu.add(logPastManeuver);
        employeeMenu.add(viewEmployees);
        employeeMenu.add(updateEmployee);
        employeeMenu.add(addEmployee);
        employeeMenu.add(removeEmployee);

        addMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMissionDialog();
            }
        });

        updateMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMissionDialog();
            }
        });

        searchMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMissionDialog();
            }
        });

        terminateMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminateMissionDialog();
            }
        });

        scheduleImmediateManeuver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleImmediateManeuverDialog();
            }
        });

        scheduleFutureManeuver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleFutureManeuverDialog();
            }
        });

        updateManeuver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateManeuverDialog();
            }
        });
        logPastManeuver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logPastManeuverDialog();
            }
        });

        frame.setJMenuBar(menuBar);

        frame.setVisible(true);

    }

    /**
     * Displays a modal dialog for adding a new mission. Includes form fields
     * for all mission attributes and performs validation. - Checks for
     * duplicate mission names. - Validates employee ID existence in the
     * database. - Delegates database insertion to SQLDatabase.insertMission().
     * - Refreshes mission list upon success.
     */
    private void addMissionDialog() {
        JDialog dialog = new JDialog(this, "Add a Mission", true);
        dialog.setSize(600, 500);
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
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> dialog.dispose());

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
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * Displays a modal dialog that allows users to select a mission from a
     * list, edit its fields, and update the database record. A search bar is
     * included to filter the mission list by name.
     */
    private void updateMissionDialog() {
        JDialog dialog = new JDialog(this, "Update a Mission", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        // ===== Top Panel: Search + Mission List =====
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new GridLayout(1, 2));
        JLabel searchLabel = new JLabel("Search Mission(s): ");
        JTextField searchTextField = new JTextField();
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);

        DefaultListModel<Mission> listModel = new DefaultListModel<>();
        JList<Mission> missionList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(missionList);

        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        dialog.add(topPanel, BorderLayout.NORTH);

        // Load initial mission list
        for (Mission m : missions) {
            listModel.addElement(m);
        }

        // Search filter functionality
        searchTextField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterList();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterList();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterList();
            }

            private void filterList() {
                String searchKey = searchTextField.getText().toLowerCase();
                listModel.clear();
                for (Mission m : missions) {
                    if (m.getMissionName().toLowerCase().contains(searchKey)) {
                        listModel.addElement(m);
                    }
                }
            }
        });

        // ===== Center Panel: Form to Edit =====
        JPanel labelPanel = new JPanel(new GridLayout(0, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(0, 1));

        String[] labels = {
            "Mission Name:", "Mission Type:", "Launch Date:", "Status:",
            "Objectives:", "Fuel Level:", "Location:", "Termination Date:"
        };

        JTextField nameField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField launchField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField objectivesField = new JTextField();
        JTextField fuelField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField terminationField = new JTextField();

        JTextField[] fields = {
            nameField, typeField, launchField, statusField,
            objectivesField, fuelField, locationField, terminationField
        };

        for (String l : labels) {
            labelPanel.add(new JLabel(l));
        }
        for (JTextField f : fields) {
            fieldPanel.add(f);
        }

        JPanel centerPanel = new JPanel(new GridLayout(0, 2));
        centerPanel.add(labelPanel);
        centerPanel.add(fieldPanel);
        dialog.add(centerPanel, BorderLayout.CENTER);

        // Populate fields on selection
        missionList.addListSelectionListener(e -> {
            Mission selected = missionList.getSelectedValue();
            if (selected != null) {
                nameField.setText(selected.getMissionName());
                typeField.setText(selected.getMissionType());
                launchField.setText(selected.getLaunchDate());
                statusField.setText(selected.getMissionStatus());
                objectivesField.setText(selected.getMissionObjectives());
                fuelField.setText(String.valueOf(selected.getInitialFuelLevel()));
                locationField.setText(selected.getInitialLocation());
                terminationField.setText(selected.getTerminationDate());
            }
        });

        // ===== Bottom Panel: Submit/Cancel Buttons =====
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            Mission selected = missionList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(dialog, "Please select a mission to update.");
                return;
            }

            try {
                String updatedName = nameField.getText().trim();
                String updatedType = typeField.getText().trim();
                String updatedLaunch = launchField.getText().trim();
                String updatedStatus = statusField.getText().trim();
                String updatedObjectives = objectivesField.getText().trim();
                int updatedFuel = Integer.parseInt(fuelField.getText().trim());
                String updatedLocation = locationField.getText().trim();
                String updatedTermination = terminationField.getText().trim();

                boolean success = SQLDatabase.updateMissionByID(
                        selected.getMissionID(),
                        updatedName, updatedType, updatedLaunch, updatedStatus,
                        updatedObjectives, updatedFuel, updatedLocation, updatedTermination
                );

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Mission updated successfully.");
                    missions = SQLDatabase.getAllMissions();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Update failed. Please try again.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Fuel level must be a valid number.");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

private void searchMissionDialog() {
    JDialog dialog = new JDialog(this, "Search a Mission", true);
    dialog.setSize(600, 500);
    dialog.setLocationRelativeTo(frame);
    dialog.setLayout(new BorderLayout());
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    JPanel upperPanel = new JPanel(new GridLayout(1, 0));
    JLabel searchLabel = new JLabel("Search Mission(s): ");
    JTextField searchTextField = new JTextField();
    JButton searchButton = new JButton("Search");
    JButton viewAll = new JButton("View All");

    upperPanel.add(searchLabel);
    upperPanel.add(searchTextField);
    upperPanel.add(searchButton);
    upperPanel.add(viewAll);

    JPanel middlePanel = new JPanel(new BorderLayout());
    DefaultListModel<Mission> listContents = new DefaultListModel<>();
    JList<Mission> list = new JList<>(listContents);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(list);
    middlePanel.add(scrollPane);

    for (Mission m : missions) {
        listContents.addElement(m);
    }

    searchButton.addActionListener(e -> {
        String searchKey = searchTextField.getText().toLowerCase();
        listContents.clear();
        boolean found = false;
        for (Mission m : missions) {
            if (m.getMissionName().toLowerCase().contains(searchKey)) {
                listContents.addElement(m);
                found = true;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Search returned no results!");
            viewAll.doClick();
        }
    });

    viewAll.addActionListener(e -> {
        listContents.clear();
        for (Mission m : missions) {
            listContents.addElement(m);
        }
    });

    JPanel lowerButtonPanel = new JPanel(new GridLayout(1, 0));
    JButton selectButton = new JButton("Select Entry");
    JButton cancelButton = new JButton("Cancel");

    selectButton.addActionListener(e -> {
        Mission selected = list.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a mission from the list.");
            return;
        }

        // Build mission details text
        StringBuilder details = new StringBuilder();
        details.append("Mission Details:\n");
        details.append("MissionID: ").append(selected.getMissionID()).append("\n");
        details.append("Name: ").append(selected.getMissionName()).append("\n");
        details.append("Type: ").append(selected.getMissionType()).append("\n");
        details.append("Status: ").append(selected.getMissionStatus()).append("\n");
        details.append("Launch Date: ").append(selected.getLaunchDate()).append("\n");
        details.append("Objectives: ").append(selected.getMissionObjectives()).append("\n");
        details.append("Initial Fuel: ").append(selected.getInitialFuelLevel()).append(" units\n");
        details.append("Initial Location: ").append(selected.getInitialLocation()).append("\n");
        details.append("Termination Date: ").append(selected.getTerminationDate()).append("\n");

        JTextArea textArea = new JTextArea(details.toString());
        textArea.setEditable(false);
        textArea.setBackground(new JLabel().getBackground());

        JScrollPane scrollPaneInner = new JScrollPane(textArea);
        scrollPaneInner.setPreferredSize(new Dimension(450, 200));

        JButton exportButton = new JButton("Export Report");
        exportButton.addActionListener(ev -> {
            try {
                MissionReport report = SQLDatabase.generateMissionReport(selected);
                report.exportReport(); // Save default file

                JOptionPane.showMessageDialog(this,
                        "Report exported successfully for Mission ID: " + selected.getMissionID(),
                        "Export Successful", JOptionPane.INFORMATION_MESSAGE);

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new java.io.File("report_mission_" + selected.getMissionID() + ".txt"));
                int option = fileChooser.showSaveDialog(dialog);

                if (option == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileChooser.getSelectedFile();
                    java.nio.file.Files.copy(
                            java.nio.file.Paths.get("report_mission_" + selected.getMissionID()+ ".txt"),
                            file.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                    JOptionPane.showMessageDialog(this, "Report saved successfully.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to export report: " + ex.getMessage(),
                        "Export Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.add(scrollPaneInner, BorderLayout.CENTER);
        detailsPanel.add(exportButton, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, detailsPanel, "Mission Details", JOptionPane.INFORMATION_MESSAGE);
    });

    cancelButton.addActionListener(e -> dialog.setVisible(false));

    lowerButtonPanel.add(selectButton);
    lowerButtonPanel.add(cancelButton);

    dialog.add(upperPanel, BorderLayout.NORTH);
    dialog.add(middlePanel, BorderLayout.CENTER);
    dialog.add(lowerButtonPanel, BorderLayout.SOUTH);
    dialog.setVisible(true);
}


    /**
     * Displays a modal dialog that allows the user to search for active
     * missions (not terminated) and terminate a selected mission by
     * automatically assigning today's date as the termination date.
     */
    private void terminateMissionDialog() {
        JDialog dialog = new JDialog(this, "Terminate a Mission", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // ===== Top Panel: Search Bar =====
        JPanel upperPanel = new JPanel(new GridLayout(1, 0));
        JLabel searchLabel = new JLabel("Search Mission(s): ");
        JTextField searchTextField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton viewAll = new JButton("View All");
        upperPanel.add(searchLabel);
        upperPanel.add(searchTextField);
        upperPanel.add(searchButton);
        upperPanel.add(viewAll);

        // ===== Middle Panel: Mission List =====
        JPanel middlePanel = new JPanel(new BorderLayout());
        DefaultListModel<Mission> listContents = new DefaultListModel<>();
        JList<Mission> list = new JList<>(listContents);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        middlePanel.add(scrollPane);

        // Load only missions that are not terminated (terminationDate is null or empty)
        listContents.clear();
        for (Mission m : missions) {
            if (m.getTerminationDate() == null || m.getTerminationDate().trim().isEmpty()) {
                listContents.addElement(m);
            }
        }

        // ===== Bottom Buttons =====
        JPanel lowerButtonPanel = new JPanel(new GridLayout(1, 0));
        JButton terminateButton = new JButton("Terminate");
        JButton cancelButton = new JButton("Cancel");
        lowerButtonPanel.add(terminateButton);
        lowerButtonPanel.add(cancelButton);

        // ===== Search Logic =====
        searchButton.addActionListener(e -> {
            String searchKey = searchTextField.getText().toLowerCase();
            listContents.clear();
            boolean found = false;
            for (Mission m : missions) {
                if ((m.getTerminationDate() == null || m.getTerminationDate().trim().isEmpty())
                        && m.getMissionName().toLowerCase().contains(searchKey)) {
                    listContents.addElement(m);
                    found = true;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Search returned no results!");
                viewAll.doClick();
            }
        });

        viewAll.addActionListener(e -> {
            listContents.clear();
            for (Mission m : missions) {
                if (m.getTerminationDate() == null || m.getTerminationDate().trim().isEmpty()) {
                    listContents.addElement(m);
                }
            }
        });

        // ===== Terminate Button Logic =====
        terminateButton.addActionListener(e -> {
            Mission selected = list.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(dialog, "Please select a mission to terminate.");
                return;
            }

            // Get today's date in YYYY-MM-DD format
            String today = java.time.LocalDate.now().toString();

            boolean success = SQLDatabase.updateTerminationDate(selected.getMissionID(), today);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Mission marked as terminated on: " + today);
                missions = SQLDatabase.getAllMissions(); // refresh list
                dialog.dispose();
            } // This is there in case the mission already have a termination date on it.
            else {
                JOptionPane.showMessageDialog(dialog, "ERROR: Failed to update mission termination date.");
            }
        });

        cancelButton.addActionListener(e -> dialog.setVisible(false));

        // Assemble dialog
        dialog.add(upperPanel, BorderLayout.NORTH);
        dialog.add(middlePanel, BorderLayout.CENTER);
        dialog.add(lowerButtonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void scheduleImmediateManeuverDialog() {
        JDialog dialog = new JDialog(frame, "Schedule Immediate Maneuver", true);
        dialog.setSize(600, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);

        JPanel labelPanel = new JPanel(new GridLayout(0, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(0, 1));

        String[] labels = {
            "Mission ID:", "Employee ID (optional):", "Crew ID (optional):",
            "Maneuver Type:", "Maneuver Details:",
            "Fuel Cost:", "Status:"
        };

        JTextField missionIDField = new JTextField();
        JTextField empIDField = new JTextField();
        JTextField crewIDField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField detailField = new JTextField();
        JTextField fuelCostField = new JTextField();
        JTextField statusField = new JTextField();

        JTextField[] fields = {
            missionIDField, empIDField, crewIDField, typeField, detailField,
            fuelCostField, statusField
        };

        for (String l : labels) {
            labelPanel.add(new JLabel(l));
        }
        for (JTextField f : fields) {
            fieldPanel.add(f);
        }

        JPanel centerPanel = new JPanel(new GridLayout(0, 2));
        centerPanel.add(labelPanel);
        centerPanel.add(fieldPanel);
        dialog.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");

        cancel.addActionListener(e -> dialog.dispose());

        submit.addActionListener(e -> {
            try {
                String missionIDText = missionIDField.getText().trim();
                String empText = empIDField.getText().trim();
                String crewText = crewIDField.getText().trim();

                // Check conflict
                // Validate required missionID
                if (missionIDText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Mission ID must be entered.");
                    return;
                }
                int missionID = Integer.parseInt(missionIDText);

                if (!empText.isEmpty() && !crewText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter either Employee ID or Crew ID, not both.");
                    return;
                }

                if (empText.isEmpty() && crewText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "You must enter either an Employee ID or a Crew ID.");
                    return;
                }

                Integer empID = empText.isEmpty() ? null : Integer.parseInt(empText);
                Integer crewID = crewText.isEmpty() ? null : Integer.parseInt(crewText);
                String maneuverType = typeField.getText().trim();
                String details = detailField.getText().trim();
                int fuelCost = Integer.parseInt(fuelCostField.getText().trim());
                String status = statusField.getText().trim();

                // Use system-generated timestamps and logger info
                boolean success = SQLDatabase.insertImmediateManeuver(
                        missionID, empID, crewID, maneuverType, details,
                        fuelCost, status
                );

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Maneuver scheduled successfully.");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to schedule maneuver.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Numeric fields must be valid integers.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(submit);
        buttonPanel.add(cancel);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void scheduleFutureManeuverDialog() {
        JDialog dialog = new JDialog(frame, "Schedule Future Maneuver", true);
        dialog.setSize(600, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);

        JPanel labelPanel = new JPanel(new GridLayout(0, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(0, 1));

        String[] labels = {
            "Mission ID:", "Employee ID (optional):", "Crew ID (optional):",
            "Maneuver Type:", "Maneuver Details:", "Execution Time (YYYY-MM-DD HH:MM PST):",
            "Fuel Cost:", "Status:"
        };

        JTextField missionIDField = new JTextField();
        JTextField empIDField = new JTextField();
        JTextField crewIDField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField detailField = new JTextField();
        JTextField execTimeField = new JTextField();
        JTextField fuelCostField = new JTextField();
        JTextField statusField = new JTextField();

        JTextField[] fields = {
            missionIDField, empIDField, crewIDField, typeField, detailField,
            execTimeField, fuelCostField, statusField
        };

        for (String l : labels) {
            labelPanel.add(new JLabel(l));
        }
        for (JTextField f : fields) {
            fieldPanel.add(f);
        }

        JPanel centerPanel = new JPanel(new GridLayout(0, 2));
        centerPanel.add(labelPanel);
        centerPanel.add(fieldPanel);
        dialog.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");

        cancel.addActionListener(e -> dialog.dispose());

        submit.addActionListener(e -> {
            try {
                String missionIDText = missionIDField.getText().trim();
                String empText = empIDField.getText().trim();
                String crewText = crewIDField.getText().trim();

                if (missionIDText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Mission ID must be entered.");
                    return;
                }

                int missionID = Integer.parseInt(missionIDText);

                if (!empText.isEmpty() && !crewText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter either Employee ID or Crew ID, not both.");
                    return;
                }

                if (empText.isEmpty() && crewText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "You must enter either an Employee ID or a Crew ID.");
                    return;
                }

                Integer empID = empText.isEmpty() ? null : Integer.parseInt(empText);
                Integer crewID = crewText.isEmpty() ? null : Integer.parseInt(crewText);
                String maneuverType = typeField.getText().trim();
                String details = detailField.getText().trim();
                String execTime = execTimeField.getText().trim();
                int fuelCost = Integer.parseInt(fuelCostField.getText().trim());
                String status = statusField.getText().trim();

                boolean success = SQLDatabase.insertFutureManeuver(
                        missionID, empID, crewID, maneuverType, details,
                        execTime, fuelCost, status
                );

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Future maneuver scheduled successfully.");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to schedule future maneuver.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Numeric fields must be valid integers.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(submit);
        buttonPanel.add(cancel);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void updateManeuverDialog() {
        JDialog dialog = new JDialog(frame, "Update a Maneuver", true);
        dialog.setSize(600, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);

        JPanel topPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        topPanel.add(new JLabel("Search by manuver ID:"), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        DefaultListModel<Maneuver> maneuverModel = new DefaultListModel<>();
        JList<Maneuver> maneuverList = new JList<>(maneuverModel);
        JScrollPane scroll = new JScrollPane(maneuverList);
        topPanel.add(scroll, BorderLayout.SOUTH);
        dialog.add(topPanel, BorderLayout.NORTH);

        List<Maneuver> allManeuvers = SQLDatabase.getAllManeuvers();
        List<Mission> allMissions = SQLDatabase.getAllMissions();
        for (Maneuver m : allManeuvers) {
            maneuverModel.addElement(m);
        }

        // Search filter
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            private void filter() {
                String keyword = searchField.getText().trim();
                maneuverModel.clear();
                for (Maneuver m : allManeuvers) {
                    if (String.valueOf(m.getManeuverID()).startsWith(keyword)) {
                        maneuverModel.addElement(m);
                    }
                }
            }
        });

        // === Form fields
        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        JTextField empIDField = new JTextField();
        JTextField crewIDField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField detailField = new JTextField();
        JTextField execTimeField = new JTextField();
        JTextField fuelCostField = new JTextField();
        JTextField statusField = new JTextField();

        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(empIDField);
        formPanel.add(new JLabel("Crew ID:"));
        formPanel.add(crewIDField);
        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeField);
        formPanel.add(new JLabel("Details:"));
        formPanel.add(detailField);
        formPanel.add(new JLabel("Execution Time (YYYY-MM-DD HH:MM PST):"));
        formPanel.add(execTimeField);
        formPanel.add(new JLabel("Fuel Cost:"));
        formPanel.add(fuelCostField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusField);

        dialog.add(formPanel, BorderLayout.CENTER);

        maneuverList.addListSelectionListener(e -> {
            Maneuver selected = maneuverList.getSelectedValue();
            if (selected != null) {
                empIDField.setText(selected.getEmployeeID() != null ? String.valueOf(selected.getEmployeeID()) : "");
                crewIDField.setText(selected.getCrewID() != null ? String.valueOf(selected.getCrewID()) : "");
                typeField.setText(selected.getManeuverType());
                detailField.setText(selected.getManeuverDescription());
                execTimeField.setText(selected.getExecutionTime());
                fuelCostField.setText(String.valueOf(selected.getFuelCost()));
                statusField.setText(selected.getStatus());
            }
        });

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            Maneuver selected = maneuverList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(dialog, "Please select a maneuver to update.");
                return;
            }
            try {
                String empText = empIDField.getText().trim();
                String crewText = crewIDField.getText().trim();

                if (!empText.isEmpty() && !crewText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please enter either Employee ID or Crew ID, not both.");
                    return;
                }

                if (empText.isEmpty() && crewText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "You must enter either an Employee ID or a Crew ID.");
                    return;
                }

                Integer empID = empText.isEmpty() ? null : Integer.parseInt(empText);
                Integer crewID = crewText.isEmpty() ? null : Integer.parseInt(crewText);
                String type = typeField.getText().trim();
                String details = detailField.getText().trim();
                String execTime = execTimeField.getText().trim();
                int fuel = Integer.parseInt(fuelCostField.getText().trim());
                String stat = statusField.getText().trim();

                boolean success = SQLDatabase.updateManeuverByID(
                        selected.getManeuverID(), selected.getMissionID(),
                        empID, crewID, type, details, execTime, fuel, stat
                );

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Maneuver updated successfully.");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Update failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Fuel cost and IDs must be numeric.");
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(submit);
        dialog.add(btnPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void logPastManeuverDialog() {
        JDialog dialog = new JDialog(frame, "Log Past Maneuver", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);

        DefaultListModel<Maneuver> maneuverModel = new DefaultListModel<>();
        JList<Maneuver> maneuverList = new JList<>(maneuverModel);
        JScrollPane scrollPane = new JScrollPane(maneuverList);

        List<Maneuver> maneuvers = SQLDatabase.getAllManeuvers();
        for (Maneuver m : maneuvers) {
            maneuverModel.addElement(m);
        }

        JButton submit = new JButton("Log Time");
        submit.addActionListener(e -> {
            Maneuver selected = maneuverList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(dialog, "Please select a maneuver to log.");
                return;
            }

            boolean success = SQLDatabase.updateLoggedTime(selected.getManeuverID());
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Logged time updated successfully.");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update logged time.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(scrollPane, BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.add(submit);
        dialog.add(bottom, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }


    private void viewEmployeesDialog() {
        JDialog dialog = new JDialog(this, "Search a Mission", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel upperPanel = new JPanel(new GridLayout(1, 0));
        JLabel searchLabel = new JLabel("Search Mission(s): ");
        JTextField searchTextField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton viewAll = new JButton("View All");

        upperPanel.add(searchLabel);
        upperPanel.add(searchTextField);
        upperPanel.add(searchButton);
        upperPanel.add(viewAll);

        JPanel middlePanel = new JPanel(new BorderLayout());
        DefaultListModel<Mission> listContents = new DefaultListModel<>();
        JList<Mission> list = new JList<>(listContents);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        middlePanel.add(scrollPane);

        for (Mission m : missions) {
            listContents.addElement(m);
        }

        searchButton.addActionListener(e -> {
            String searchKey = searchTextField.getText().toLowerCase();
            listContents.clear();
            boolean found = false;
            for (Mission m : missions) {
                if (m.getMissionName().toLowerCase().contains(searchKey)) {
                    listContents.addElement(m);
                    found = true;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Search returned no results!");
                viewAll.doClick();
            }
        });

        viewAll.addActionListener(e -> {
            listContents.clear();
            for (Mission m : missions) {
                listContents.addElement(m);
            }
        });

        JPanel lowerButtonPanel = new JPanel(new GridLayout(1, 0));
        JButton selectButton = new JButton("Select Entry");
        JButton cancelButton = new JButton("Cancel");

        selectButton.addActionListener(e -> {
            Mission selected = list.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a mission from the list.");
                return;
            }

            // Build mission details text
            StringBuilder details = new StringBuilder();
            details.append("Mission Details:\n");
            details.append("MissionID: ").append(selected.getMissionID()).append("\n");
            details.append("Name: ").append(selected.getMissionName()).append("\n");
            details.append("Type: ").append(selected.getMissionType()).append("\n");
            details.append("Status: ").append(selected.getMissionStatus()).append("\n");
            details.append("Launch Date: ").append(selected.getLaunchDate()).append("\n");
            details.append("Objectives: ").append(selected.getMissionObjectives()).append("\n");
            details.append("Initial Fuel: ").append(selected.getInitialFuelLevel()).append(" units\n");
            details.append("Initial Location: ").append(selected.getInitialLocation()).append("\n");
            details.append("Termination Date: ").append(selected.getTerminationDate()).append("\n");

            JTextArea textArea = new JTextArea(details.toString());
            textArea.setEditable(false);
            textArea.setBackground(new JLabel().getBackground());

            JScrollPane scrollPaneInner = new JScrollPane(textArea);
            scrollPaneInner.setPreferredSize(new Dimension(450, 200));

            JButton exportButton = new JButton("Export Report");
            exportButton.addActionListener(ev -> {
                try {
                    MissionReport report = SQLDatabase.generateMissionReport(selected);
                    report.exportReport(); // Save default file

                    JOptionPane.showMessageDialog(this,
                            "Report exported successfully for Mission ID: " + selected.getMissionID(),
                            "Export Successful", JOptionPane.INFORMATION_MESSAGE);

                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setSelectedFile(new java.io.File("report_mission_" + selected.getMissionID() + ".txt"));
                    int option = fileChooser.showSaveDialog(dialog);

                    if (option == JFileChooser.APPROVE_OPTION) {
                        java.io.File file = fileChooser.getSelectedFile();
                        java.nio.file.Files.copy(
                                java.nio.file.Paths.get("report_mission_" + selected.getMissionID()+ ".txt"),
                                file.toPath(),
                                java.nio.file.StandardCopyOption.REPLACE_EXISTING
                        );
                        JOptionPane.showMessageDialog(this, "Report saved successfully.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to export report: " + ex.getMessage(),
                            "Export Failed", JOptionPane.ERROR_MESSAGE);
                }
            });

            JPanel detailsPanel = new JPanel(new BorderLayout());
            detailsPanel.add(scrollPaneInner, BorderLayout.CENTER);
            detailsPanel.add(exportButton, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(this, detailsPanel, "Mission Details", JOptionPane.INFORMATION_MESSAGE);
        });

        cancelButton.addActionListener(e -> dialog.setVisible(false));

        lowerButtonPanel.add(selectButton);
        lowerButtonPanel.add(cancelButton);

        dialog.add(upperPanel, BorderLayout.NORTH);
        dialog.add(middlePanel, BorderLayout.CENTER);
        dialog.add(lowerButtonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {

        SystemInterface example = new SystemInterface("Spacecraft Mission Control System");

    }
}
