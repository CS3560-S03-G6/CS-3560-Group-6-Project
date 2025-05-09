
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

public class SystemInterface extends JFrame {

    private List<Mission> missions;
    private List<Employee> employees;
    private static int employeeID = -1; // need to make it static to be used in static methods and references

    String title = "Untitled";
    JFrame frame;

    SystemInterface() {
    }

    public static void showLoginFrame() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 300);
        loginFrame.setLayout(new GridLayout(5, 1));
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginFrame.add(new JLabel("Username:"));
        loginFrame.add(usernameField);
        loginFrame.add(new JLabel("Password:"));
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            employeeID = SQLDatabase.authenticateUser(username, password);

            if (employeeID != -1) {
                loginFrame.dispose();
                new SystemInterface("Spacecraft Mission Control System");
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid login credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.setVisible(true);
    }

    SystemInterface(String newTitle) {

        try {
            missions = SQLDatabase.getAllMissions(); // ✅ Load mission data here
        } catch (Exception e) {
            System.out.println("Error: Failed to load missions: " + e.getMessage());
            missions = new ArrayList<>(); // fallback to avoid null errors
        }

        try {
            this.employees = SQLDatabase.getAllEmployees(); // ✅ Load employee data here
        } catch (Exception e) {
            System.out.println("Error: Failed to load missions: " + e.getMessage());
            employees = new ArrayList<>(); // fallback to avoid null errors
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
        JMenuItem logout = new JMenuItem("Log Out");

        missionMenu.add(addMission);
        missionMenu.add(updateMission);
        missionMenu.add(searchMission);
        missionMenu.add(terminateMission);
        maneuverMenu.add(scheduleImmediateManeuver);
        maneuverMenu.add(scheduleFutureManeuver);
        maneuverMenu.add(updateManeuver);
        maneuverMenu.add(logPastManeuver);
        employeeMenu.add(viewEmployees);
        systemMenu.add(about);
        systemMenu.add(logout);

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

        viewEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEmployeesDialog();
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "CS 3560 Final Project - Space operation subsystem\n\nGroup 6 Members: \n  -Joshua Boucher\n  -Julianna Arzola\n  -Nicholas Magtangob\n  -Sonia Pandey\n -Thong Nguyen");
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();         // Close the main application window
                showLoginFrame();       // Reopen login window
            }
        });

        // This is where the roles accessibility begins:
        boolean isController = SQLDatabase.isMissionController(employeeID);
        boolean isDirector = SQLDatabase.isFlightDirector(employeeID);
        boolean isCommander = SQLDatabase.isCrewCommander(employeeID);
        boolean isCrew = SQLDatabase.isCrewMember(employeeID);

        if (isDirector || isCommander || isCrew) {
            addMission.setEnabled(false);
            updateMission.setEnabled(false);
            searchMission.setEnabled(false);
            terminateMission.setEnabled(false);
            updateManeuver.setEnabled(false);
        }

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
        JDialog dialog = new JDialog((JFrame) null, "Add Mission", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 5, 5));

        JTextField missionNameField = new JTextField();
        JTextField missionTypeField = new JTextField();
        JTextField launchDateField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField objectivesField = new JTextField();
        JTextField fuelLevelField = new JTextField();
        JTextField locationField = new JTextField();

        List<Spacecraft> availableSpacecraft = SQLDatabase.getAvailableSpacecraft();
        JComboBox<Spacecraft> spacecraftComboBox = new JComboBox<>(availableSpacecraft.toArray(new Spacecraft[0]));

        formPanel.add(new JLabel("Mission Name:"));
        formPanel.add(missionNameField);
        formPanel.add(new JLabel("Mission Type:"));
        formPanel.add(missionTypeField);
        formPanel.add(new JLabel("Launch Date:"));
        formPanel.add(launchDateField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusField);
        formPanel.add(new JLabel("Objectives:"));
        formPanel.add(objectivesField);
        formPanel.add(new JLabel("Spacecraft:"));
        formPanel.add(spacecraftComboBox);
        formPanel.add(new JLabel("Fuel Level:"));
        formPanel.add(fuelLevelField);
        formPanel.add(new JLabel("Initial Location:"));
        formPanel.add(locationField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submit = new JButton("Add Mission");
        JButton cancel = new JButton("Cancel");
        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        // Add panels to dialog
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        submit.addActionListener((ActionEvent e) -> {
            try {
                String name = missionNameField.getText().trim();
                String type = missionTypeField.getText().trim();
                String launch = launchDateField.getText().trim();
                String status = statusField.getText().trim();
                String obj = objectivesField.getText().trim();
                int fuel = Integer.parseInt(fuelLevelField.getText().trim());
                String loc = locationField.getText().trim();
                Spacecraft selected = (Spacecraft) spacecraftComboBox.getSelectedItem();
                Integer spacecraftID = selected != null ? selected.getSpacecraftID() : null;

                int missionID = SQLDatabase.insertMissionReturnID(
                        employeeID, name, type, launch, status, obj, fuel, loc, spacecraftID
                );

                if (spacecraftID != null && missionID != -1) {
                    SQLDatabase.assignSpacecraftToMission(spacecraftID, missionID);
                }

                JOptionPane.showMessageDialog(dialog, "Mission added successfully!");
                dialog.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Failed to add mission.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancel.addActionListener(e -> dialog.dispose());

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
        
        missions = SQLDatabase.getAllMissions();
        
        for (Mission m : missions) {
            listModel.addElement(m);
        }

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

        // ===== Center Panel: Form =====
        JPanel labelPanel = new JPanel(new GridLayout(0, 1));
        JPanel fieldPanel = new JPanel(new GridLayout(0, 1));

        JTextField nameField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField launchField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField objectivesField = new JTextField();
        JTextField fuelField = new JTextField();
        JTextField locationField = new JTextField();
        JComboBox<Spacecraft> spacecraftComboBox = new JComboBox<>();

        labelPanel.add(new JLabel("Mission Name:"));
        fieldPanel.add(nameField);
        labelPanel.add(new JLabel("Mission Type:"));
        fieldPanel.add(typeField);
        labelPanel.add(new JLabel("Launch Date:"));
        fieldPanel.add(launchField);
        labelPanel.add(new JLabel("Status:"));
        fieldPanel.add(statusField);
        labelPanel.add(new JLabel("Objectives:"));
        fieldPanel.add(objectivesField);
        labelPanel.add(new JLabel("Spacecraft:"));
        fieldPanel.add(spacecraftComboBox);
        labelPanel.add(new JLabel("Fuel Level:"));
        fieldPanel.add(fuelField);
        labelPanel.add(new JLabel("Location:"));
        fieldPanel.add(locationField);

        JPanel centerPanel = new JPanel(new GridLayout(0, 2));
        centerPanel.add(labelPanel);
        centerPanel.add(fieldPanel);
        dialog.add(centerPanel, BorderLayout.CENTER);

        // ===== Populate fields on selection =====
        missionList.addListSelectionListener(e -> {
            Mission selected = missionList.getSelectedValue();
            if (selected != null) {
                // Load spacecraft list
                spacecraftComboBox.removeAllItems();
                List<Spacecraft> updatedList = SQLDatabase.getAvailableSpacecraft();
                spacecraftComboBox.removeAllItems();
                for (Spacecraft s : updatedList) {
                    spacecraftComboBox.addItem(s);
                }

                // Only add the assigned spacecraft if it's not already in the list
                Spacecraft current = selected.getSpacecraft();
                if (current != null && updatedList.stream().noneMatch(s -> s.getSpacecraftID() == current.getSpacecraftID())) {
                    spacecraftComboBox.addItem(current);
                }
                spacecraftComboBox.setSelectedItem(current);
                for (Spacecraft s : updatedList) {
                    spacecraftComboBox.addItem(s);
                }

                // Fill fields
                nameField.setText(selected.getMissionName());
                typeField.setText(selected.getMissionType());
                launchField.setText(selected.getLaunchDate());
                statusField.setText(selected.getMissionStatus());
                objectivesField.setText(selected.getMissionObjectives());
                fuelField.setText(String.valueOf(selected.getInitialFuelLevel()));
                locationField.setText(selected.getInitialLocation());

                // Select assigned spacecraft
                spacecraftComboBox.setSelectedItem(null);
                for (int i = 0; i < spacecraftComboBox.getItemCount(); i++) {
                    if (selected.getSpacecraft() != null
                            && spacecraftComboBox.getItemAt(i).getSpacecraftID() == selected.getSpacecraft().getSpacecraftID()) {
                        spacecraftComboBox.setSelectedIndex(i);
                    }
                }
            }
        });

        // ===== Bottom Panel: Buttons =====
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
                Spacecraft newSpacecraft = (Spacecraft) spacecraftComboBox.getSelectedItem();
                Integer newSpacecraftID = newSpacecraft != null ? newSpacecraft.getSpacecraftID() : null;

                // Unassign old spacecraft
                SQLDatabase.unassignSpacecraftFromMission(selected.getMissionID());

                // Update mission
                boolean success = SQLDatabase.updateMissionByID(
                        selected.getMissionID(),
                        updatedName, updatedType, updatedLaunch, updatedStatus,
                        updatedObjectives, updatedFuel, updatedLocation,
                        newSpacecraftID, employeeID
                );

                // Assign new spacecraft
                if (success && newSpacecraftID != null) {
                    SQLDatabase.assignSpacecraftToMission(newSpacecraftID, selected.getMissionID());
                }

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

        searchButton.addActionListener(e -> {
            String searchKey = searchTextField.getText().toLowerCase();
            listContents.clear();

            missions = SQLDatabase.getAllMissions();
            boolean found = false;
            for (Mission m : missions) {
                if (m.getMissionName().toLowerCase().contains(searchKey)) {
                    listContents.addElement(m);
                    found = true;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(dialog, "Search returned no results!");
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

            StringBuilder details = new StringBuilder();
            details.append("Mission Details:\n");
            details.append("MissionID: ").append(selected.getMissionID()).append("\n");
            details.append("Name: ").append(selected.getMissionName()).append("\n");
            details.append("Type: ").append(selected.getMissionType()).append("\n");
            details.append("Status: ").append(selected.getMissionStatus()).append("\n");
            details.append("Launch Date: ").append(selected.getLaunchDate()).append("\n");
            details.append("Objectives: ").append(selected.getMissionObjectives()).append("\n");

            if (selected.getSpacecraft() != null) {
                Spacecraft sc = selected.getSpacecraft();
                details.append("Spacecraft: #")
                        .append(sc.getSpacecraftID())
                        .append(" - ")
                        .append(sc.getSpacecraftName())
                        .append(" (").append(sc.getSpacecraftType()).append(")\n");
            } else {
                details.append("Spacecraft: None Assigned\n");
            }

            details.append("Initial Fuel: ").append(selected.getInitialFuelLevel()).append(" units\n");
            details.append("Initial Location: ").append(selected.getInitialLocation()).append("\n");
            details.append("Termination Date: ").append(selected.getTerminationDate()).append("\n");

            JTextArea textArea = new JTextArea(details.toString());
            textArea.setEditable(false);
            textArea.setBackground(new JLabel().getBackground());
            JScrollPane scrollPaneInner = new JScrollPane(textArea);
            scrollPaneInner.setPreferredSize(new Dimension(450, 200));

            JButton exportButton = new JButton("Export Report");

            JPanel detailsPanel = new JPanel(new BorderLayout());
            detailsPanel.add(scrollPaneInner, BorderLayout.CENTER);
            detailsPanel.add(exportButton, BorderLayout.SOUTH);

            JDialog detailsDialog = new JDialog(dialog, "Mission Details", true);
            detailsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            detailsDialog.setLayout(new BorderLayout());
            detailsDialog.add(detailsPanel, BorderLayout.CENTER);
            detailsDialog.pack();
            detailsDialog.setLocationRelativeTo(dialog);

            exportButton.addActionListener(ev -> {
                try {
                    MissionReport report = SQLDatabase.generateMissionReport(selected);
                    report.exportReport();

                    JOptionPane.showMessageDialog(this,
                            "Report exported successfully for Mission ID: " + selected.getMissionID(),
                            "Export Successful", JOptionPane.INFORMATION_MESSAGE);

                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setSelectedFile(new java.io.File("report_mission_" + selected.getMissionID() + ".txt"));
                    int option = fileChooser.showSaveDialog(dialog);

                    if (option == JFileChooser.APPROVE_OPTION) {
                        java.io.File file = fileChooser.getSelectedFile();
                        java.nio.file.Files.copy(
                                java.nio.file.Paths.get("report_mission_" + selected.getMissionID() + ".txt"),
                                file.toPath(),
                                java.nio.file.StandardCopyOption.REPLACE_EXISTING
                        );
                        JOptionPane.showMessageDialog(this, "Report saved successfully.");
                        detailsDialog.dispose();
                        dialog.dispose(); // Return to main after save
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to export report: " + ex.getMessage(),
                            "Export Failed", JOptionPane.ERROR_MESSAGE);
                }
            });

            detailsDialog.setVisible(true);
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

        // Start getting all the missions first:
        missions = SQLDatabase.getAllMissions();

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
                SQLDatabase.unassignSpacecraftFromMission(selected.getMissionID());
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
            "Mission ID:", "Maneuver Type:", "Maneuver Details:",
            "Fuel Cost:", "Status:"
        };

        JTextField missionIDField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField detailField = new JTextField();
        JTextField fuelCostField = new JTextField();
        JTextField statusField = new JTextField();

        JTextField[] fields = {
            missionIDField, typeField, detailField, fuelCostField, statusField
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
                String missionText = missionIDField.getText().trim();
                if (missionText.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Mission ID must be entered.");
                    return;
                }

                int missionID = Integer.parseInt(missionText);
                String maneuverType = typeField.getText().trim();
                String maneuverDetails = detailField.getText().trim();
                int fuelCost = Integer.parseInt(fuelCostField.getText().trim());
                String status = statusField.getText().trim();

                // === Determine Role & Set EmployeeID/CrewID ===
                Integer empID = employeeID;
                Integer crewID = null;

                if (SQLDatabase.isCrewCommander(empID) || SQLDatabase.isCrewMember(empID)) {
                    // Retrieve crewID for current employee
                    crewID = SQLDatabase.getCrewIDForEmployee(empID);
                }

                boolean success = SQLDatabase.insertImmediateManeuver(
                        missionID, empID, crewID, maneuverType, maneuverDetails, fuelCost, status
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
            "Mission ID:", "Maneuver Type:", "Maneuver Details:",
            "Execution Time:", "Fuel Cost:", "Status:"
        };

        JTextField missionIDField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField detailField = new JTextField();
        JTextField execTimeField = new JTextField();
        JTextField fuelCostField = new JTextField();
        JTextField statusField = new JTextField();

        JTextField[] fields = {
            missionIDField, typeField, detailField, execTimeField, fuelCostField, statusField
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
                int missionID = Integer.parseInt(missionIDField.getText().trim());
                String maneuverType = typeField.getText().trim();
                String maneuverDetails = detailField.getText().trim();
                String execTime = execTimeField.getText().trim();
                int fuelCost = Integer.parseInt(fuelCostField.getText().trim());
                String status = statusField.getText().trim();

                Integer empID = employeeID;
                Integer crewID = null;

                // Determine crewID if user is in a crew
                if (SQLDatabase.isCrewCommander(empID) || SQLDatabase.isCrewMember(empID)) {
                    crewID = SQLDatabase.getCrewIDForEmployee(empID);
                }

                boolean success = SQLDatabase.insertFutureManeuver(
                        missionID, empID, crewID, maneuverType, maneuverDetails,
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
        topPanel.add(new JLabel("Search by maneuver ID:"), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        DefaultListModel<Maneuver> maneuverModel = new DefaultListModel<>();
        JList<Maneuver> maneuverList = new JList<>(maneuverModel);
        JScrollPane scroll = new JScrollPane(maneuverList);
        topPanel.add(scroll, BorderLayout.SOUTH);
        dialog.add(topPanel, BorderLayout.NORTH);

        List<Maneuver> allManeuvers = SQLDatabase.getAllManeuvers();
        for (Maneuver m : allManeuvers) {
            maneuverModel.addElement(m);
        }

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

        JPanel formPanel = new JPanel(new GridLayout(0, 2));
        JTextField typeField = new JTextField();
        JTextField detailField = new JTextField();
        JTextField execTimeField = new JTextField();
        JTextField fuelCostField = new JTextField();
        JTextField statusField = new JTextField();

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
                Integer empID = employeeID;
                Integer crewID = null;

                if (SQLDatabase.isCrewCommander(empID) || SQLDatabase.isCrewMember(empID)) {
                    crewID = SQLDatabase.getCrewIDForEmployee(empID);
                }

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
                JOptionPane.showMessageDialog(dialog, "Fuel cost must be a number.");
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

            boolean success = SQLDatabase.updateLoggedTimeAndBy(selected.getManeuverID(), employeeID);
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Maneuver logged successfully");
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
        JDialog dialog = new JDialog(this, "Employees", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel upperPanel = new JPanel(new GridLayout(1, 0));
        JLabel searchLabel = new JLabel("Search Employee(s): ");
        JTextField searchTextField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton viewAll = new JButton("View All");

        upperPanel.add(searchLabel);
        upperPanel.add(searchTextField);
        upperPanel.add(searchButton);
        upperPanel.add(viewAll);

        JPanel middlePanel = new JPanel(new BorderLayout());
        DefaultListModel<Employee> listContents = new DefaultListModel<>();
        JList<Employee> list = new JList<>(listContents);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        middlePanel.add(scrollPane);

        for (Employee e : employees) {
            listContents.addElement(e);
        }

        searchButton.addActionListener(e -> {
            String searchKey = searchTextField.getText().toLowerCase();
            listContents.clear();
            boolean found = false;
            for (Employee emp : employees) {
                if (emp.getName().toLowerCase().contains(searchKey)) {
                    listContents.addElement(emp);
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
            for (Employee emp : employees) {
                listContents.addElement(emp);
            }
        });

        JPanel lowerButtonPanel = new JPanel(new GridLayout(1, 0));
        JButton selectButton = new JButton("View Details");
        JButton cancelButton = new JButton("Exit");

        selectButton.addActionListener(e -> {
            Employee selected = list.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select an employee from the list.");
                return;
            }

            // Determine role using helper methods
            String role = "Unknown";
            int id = selected.getEmployeeID();
            if (SQLDatabase.isMissionController(id)) {
                role = "Mission Controller";
            } else if (SQLDatabase.isFlightDirector(id)) {
                role = "Flight Director";
            } else if (SQLDatabase.isCrewCommander(id)) {
                role = "Crew Commander";
            } else if (SQLDatabase.isCrewMember(id)) {
                role = "Crew Member";
            }

            // Build mission details text
            StringBuilder details = new StringBuilder();
            details.append("Employee Details:\n");
            details.append("EmployeeID: ").append(selected.getEmployeeID()).append("\n");
            details.append("Name: ").append(selected.getName()).append("\n");
            details.append("Role: ").append(role).append("\n");
            details.append("Email: ").append(selected.getEMail()).append("\n");
            details.append("Phone: ").append(selected.getPhoneNumber()).append("\n");
            details.append("Location: ").append(selected.getLocation()).append("\n");

            JTextArea textArea = new JTextArea(details.toString());
            textArea.setEditable(false);
            textArea.setBackground(new JLabel().getBackground());

            JScrollPane scrollPaneInner = new JScrollPane(textArea);
            scrollPaneInner.setPreferredSize(new Dimension(450, 200));

            JPanel detailsPanel = new JPanel(new BorderLayout());
            detailsPanel.add(scrollPaneInner, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(this, detailsPanel, "Employee Details", JOptionPane.INFORMATION_MESSAGE);
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
        showLoginFrame(); // open login first
    }

}
