import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestGUI extends JFrame {
    String title = "Untitled";
    JFrame frame;

    TestGUI() {
        JFrame frame = new JFrame(title);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 300);

        frame.setVisible(true);
    }

    TestGUI(String newTitle) {
        this.frame = new JFrame();

        this.title = newTitle;
        frame = new JFrame(newTitle);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
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
        JMenu crewMenu = new JMenu("Crew");
        JMenu spacecraftMenu = new JMenu("Spacecraft");

        menuBar.add(missionMenu);
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
        missionMenu.add(scheduleImmediateManeuver);
        missionMenu.add(scheduleFutureManeuver);

        addMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMissionDialog();
            }
        });

        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    private void addMissionDialog() {
        JDialog dialog = new JDialog(this, "Add a Mission", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        //Create upper panel with exit button
        //==================================================
        JPanel upperButtonPanel = new JPanel();
        upperButtonPanel.setLayout(new GridLayout(1, 0));

        JPanel filler1 = new JPanel();
        JPanel filler2 = new JPanel();
        JPanel filler3 = new JPanel();

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        upperButtonPanel.add(filler1);
        upperButtonPanel.add(filler2);
        upperButtonPanel.add(filler3);
        upperButtonPanel.add(exitButton);

        dialog.add(upperButtonPanel, BorderLayout.NORTH);


        //Create labels and text fields
        //==================================================
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 1));
        JLabel missionName = new JLabel("Mission Name: ");
        JLabel missionType = new JLabel("Mission Type: ");
        JLabel status = new JLabel("Status: ");
        JLabel objectives = new JLabel("Objectives: ");
        JLabel terminationDate = new JLabel("Termination Date: ");
        JLabel spacecraftName = new JLabel("Spacecraft Name: ");
        JLabel spacecraftType = new JLabel("Spacecraft Type: ");
        labelPanel.add(missionName);
        labelPanel.add(missionType);
        labelPanel.add(status);
        labelPanel.add(objectives);
        labelPanel.add(terminationDate);
        labelPanel.add(spacecraftName);
        labelPanel.add(spacecraftType);


        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(0, 1));
        JTextField missionNameTextField = new JTextField();
        JTextField missionTypeTextField = new JTextField();
        JTextField statusTextField = new JTextField();
        JTextField objectivesTextField = new JTextField();
        JTextField terminationDateTextField = new JTextField();
        JTextField spacecraftNameTextField = new JTextField();
        JTextField spacecraftTypeTextField = new JTextField();
        textFieldPanel.add(missionNameTextField);
        textFieldPanel.add(missionTypeTextField);
        textFieldPanel.add(statusTextField);
        textFieldPanel.add(objectivesTextField);
        textFieldPanel.add(terminationDateTextField);
        textFieldPanel.add(spacecraftNameTextField);
        textFieldPanel.add(spacecraftTypeTextField);

        JPanel labelsAndTextFields = new JPanel();
        labelsAndTextFields.setLayout(new GridLayout(0, 2));
        labelsAndTextFields.add(labelPanel);
        labelsAndTextFields.add(textFieldPanel);

        dialog.add(labelsAndTextFields, BorderLayout.CENTER);


        //Create lower panel with Submit button
        //==================================================
        JPanel lowerButtonPanel = new JPanel();
        upperButtonPanel.setLayout(new GridLayout(1, 0));
        JPanel lowerFiller1 = new JPanel();
        JPanel lowerFiller2 = new JPanel();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String missionName = "For Mission Name, user inputted: " + missionNameTextField.getText();
                String missionType = "For Mission Type, user inputted: " + missionTypeTextField.getText();
                String status = "For Status, user inputted: " + statusTextField.getText();
                String objectives = "For Objectives, user inputted: " + objectivesTextField.getText();
                String terminationDate = "For Termination Date, user inputted: " + terminationDateTextField.getText();
                String spacecraftName = "For Spacecraft Type, user inputted: " + spacecraftNameTextField.getText();
                String spacecraftType = "For Spacecraft Type, user inputted: " + spacecraftTypeTextField.getText();

                System.out.println(missionName + "\n" + missionType + "\n" + status + "\n" + objectives + "\n" + terminationDate + "\n" + spacecraftName + "\n" + spacecraftType + "\n");
                dialog.setVisible(false);
            }
        });

        lowerButtonPanel.add(lowerFiller1);
        lowerButtonPanel.add(submitButton);
        lowerButtonPanel.add(lowerFiller2);

        dialog.add(lowerButtonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
}
