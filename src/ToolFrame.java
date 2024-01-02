package src;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ToolFrame extends JFrame {
    private Transcript transcript;
    private HashMap<String, JLabel> someLabels;
    private HashMap<String, JTextField> allTextFields;
    private HashMap<String, JComboBox<String>> allDropDowns;

    /**
     * A constructor method to create a "500x500" unresizable JFrame.
     */
    public ToolFrame(Transcript transcript) {
        ImageIcon icon = new ImageIcon("./src/media/frame-icon.png");

        someLabels = new HashMap<>();
        allTextFields = new HashMap<>();
        allDropDowns = new HashMap<>();
        this.transcript = transcript;
        this.setTitle("My Transcript");
        this.setIconImage(icon.getImage());
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(162, 210, 223));

        buildLabels();
        buildInteracticeFields();
        buildButtons();
    }

    /**
     * This function is used to turn on the JFrame.
     */
    public void launchFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * A private function used by the constructor to build all text labels.
     */
    private void buildLabels() {
        JLabel newCourseLabel = new JLabel("Add New Courses");
        JLabel courseNameLabel = new JLabel("Course Name:");
        JLabel courseGradeLabel = new JLabel("Course Grade:");
        JLabel courseCreditsLabel = new JLabel("Course Units:");
        JLabel printTranscriptLabel = new JLabel("Print Transcript");
        JLabel printTypeLabel = new JLabel("Print Type:");
        JLabel readFileLabel = new JLabel("Read File");
        JLabel filePathLabel = new JLabel("File Path:");
        JLabel importLabel = new JLabel("Import Values");
        JLabel importGradePnts = new JLabel("Grade Points:");
        JLabel importGradedUnits = new JLabel("Graded Units:");
        JLabel importTotalUnits = new JLabel("Total Units:");
        JLabel gpaLabel = new JLabel("GPA: 0.0");
        JLabel unitsLabel = new JLabel("Total Units: 0.0");
        Font titleFont = new Font("Consolas", Font.BOLD, 18);
        Font itemFont = new Font("Arial", Font.BOLD, 14);
        Font displayFont = new Font("Candara", Font.ITALIC, 14);
        Color titleColor = Color.BLUE;
        Color displayColor = new Color(120, 30, 200);

        newCourseLabel.setBounds(20, 20, 180, 30);
        newCourseLabel.setFont(titleFont);
        newCourseLabel.setForeground(titleColor);
        courseNameLabel.setBounds(20, 50, 120, 25);
        courseNameLabel.setFont(itemFont);
        courseGradeLabel.setBounds(20, 85, 120, 25);
        courseGradeLabel.setFont(itemFont);
        courseCreditsLabel.setBounds(20, 120, 120, 25);
        courseCreditsLabel.setFont(itemFont);

        printTranscriptLabel.setBounds(280, 20, 180, 30);
        printTranscriptLabel.setFont(titleFont);
        printTranscriptLabel.setForeground(titleColor);
        printTypeLabel.setBounds(280, 50, 100, 25);
        printTypeLabel.setFont(itemFont);

        readFileLabel.setBounds(20, 210, 180, 30);
        readFileLabel.setFont(titleFont);
        readFileLabel.setForeground(titleColor);
        filePathLabel.setBounds(20, 240, 80, 25);
        filePathLabel.setFont(itemFont);

        importLabel.setBounds(280, 210, 180, 30);
        importLabel.setFont(titleFont);
        importLabel.setForeground(titleColor);
        importGradePnts.setBounds(280, 240, 120, 25);
        importGradePnts.setFont(itemFont);
        importGradedUnits.setBounds(280, 275, 120, 25);
        importGradedUnits.setFont(itemFont);
        importTotalUnits.setBounds(280, 310, 120, 25);
        importTotalUnits.setFont(itemFont);

        gpaLabel.setBounds(20, 400, 80, 20);
        gpaLabel.setFont(displayFont);
        gpaLabel.setForeground(displayColor);
        unitsLabel.setBounds(20, 430, 100, 20);
        unitsLabel.setFont(displayFont);
        unitsLabel.setForeground(displayColor);
        someLabels.put("gpa", gpaLabel);
        someLabels.put("units", unitsLabel);

        this.add(newCourseLabel);
        this.add(courseNameLabel);
        this.add(courseGradeLabel);
        this.add(courseCreditsLabel);
        this.add(printTranscriptLabel);
        this.add(printTypeLabel);
        this.add(readFileLabel);
        this.add(filePathLabel);
        this.add(importLabel);
        this.add(importGradePnts);
        this.add(importGradedUnits);
        this.add(importTotalUnits);
        this.add(gpaLabel);
        this.add(unitsLabel);
    }

    /**
     * A private function used by the constructor to build all interactive fields.
     */
    private void buildInteracticeFields() {
        JTextField courseNameInput = new JTextField();
        JTextField courseGradeInput = new JTextField();
        JTextField courseCreditsInput = new JTextField();
        JTextField filePathInput = new JTextField();
        JTextField gradePntsInput = new JTextField();
        JTextField gradedUnitsInput = new JTextField();
        JTextField totalUnitsInput = new JTextField();
        JComboBox<String> printTypeDropDown = new JComboBox<>();

        courseNameInput.setBounds(130, 50, 80, 25);
        courseGradeInput.setBounds(130, 85, 30, 25);
        courseCreditsInput.setBounds(130, 120, 30, 25);
        allTextFields.put("course name", courseNameInput);
        allTextFields.put("course grade", courseGradeInput);
        allTextFields.put("course credits", courseCreditsInput);

        filePathInput.setBounds(90, 240, 150, 25);
        allTextFields.put("file path", filePathInput);

        gradePntsInput.setBounds(380, 240, 45, 25);
        gradedUnitsInput.setBounds(380, 275, 45, 25);
        totalUnitsInput.setBounds(380, 310, 45, 25);
        allTextFields.put("grade points", gradePntsInput);
        allTextFields.put("graded units", gradedUnitsInput);
        allTextFields.put("total units", totalUnitsInput);

        String[] printTypes = {"gpa", "grade level", "grade scale", "transcript"};
        for (String s : printTypes) printTypeDropDown.addItem(s);
        printTypeDropDown.setBounds(370, 50, 100, 25);
        allDropDowns.put("print", printTypeDropDown);

        this.add(courseNameInput);
        this.add(courseGradeInput);
        this.add(courseCreditsInput);
        this.add(filePathInput);
        this.add(gradePntsInput);
        this.add(gradedUnitsInput);
        this.add(totalUnitsInput);
        this.add(printTypeDropDown);
    }

    /**
     * A private function used by the constructor to build buttons.
     */
    private void buildButtons() {
        JButton addButton = new JButton("Add");
        JButton printButton = new JButton("Print");
        JButton clrButton = new JButton("Clear CMD");
        JButton readButton = new JButton("Read");
        JButton importButton = new JButton("Import");
        JButton rstButton = new JButton("Reset");

        // Button to add new courses to the transcript and clear respective text fields.
        addButton.setBounds(20, 150, 60, 30);
        addButton.setFocusable(false);
        addButton.addActionListener(e -> {
            try {
                String course = allTextFields.get("course name").getText();
                String grade = allTextFields.get("course grade").getText();
                Double units = Double.valueOf(allTextFields.get("course credits").getText());
                allTextFields.get("course name").setText("");
                allTextFields.get("course grade").setText("");
                allTextFields.get("course credits").setText("");

                if (!transcript.contains(course)) System.out.println(course + " was successfully added.");
                transcript.addGrade(course, grade, units);
                updateInfo();
            } catch (Exception c) {
                System.out.println("Unable to add this course because of invalid inputs.");
            }
        });

        // Button to print transcript information based on a selected print type.
        printButton.setBounds(280, 80, 70, 30);
        printButton.setFocusable(false);
        printButton.addActionListener(e -> {
            int printType = allDropDowns.get("print").getSelectedIndex();

            if (printType == 0) transcript.printGrades("gpa");
            else if (printType == 1) transcript.printGrades("lvl");
            else if (printType == 2) transcript.printGrades("scale");
            else transcript.printGrades("all");
        });

        // Button to clear the command line for better readability.
        clrButton.setBounds(370, 80, 100, 30);
        clrButton.setFocusable(false);
        clrButton.setForeground(new Color(255, 20, 20));
        clrButton.addActionListener(e -> {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        });

        // Button to read a file and clear the read input field.
        readButton.setBounds(20, 275, 70, 30);
        readButton.setFocusable(false);
        readButton.addActionListener(e -> {
            String filePath = allTextFields.get("file path").getText();
            allTextFields.get("file path").setText("");

            int cnt = transcript.getCourseList().size();
            transcript.gradeScan(false, filePath);
            updateInfo();

            // A rigid count can be an indicator of an invalid file path.
            if (cnt == transcript.getCourseList().size()) {
                System.out.println("For reference, you're currently in " + "\"" + System.getProperty("user.dir") + "\"");
            }
        });

        // Button to import values and clear the used input fields.
        importButton.setBounds(280, 340, 80, 30);
        importButton.setFocusable(false);
        importButton.addActionListener(e -> {
            try {
                Double gradePnts = Double.valueOf(allTextFields.get("grade points").getText());
                Double gradedUnits = Double.valueOf(allTextFields.get("graded units").getText());
                Double totalUnits = Double.valueOf(allTextFields.get("total units").getText());
                allTextFields.get("grade points").setText("");
                allTextFields.get("graded units").setText("");
                allTextFields.get("total units").setText("");

                transcript.importGrades(gradePnts, gradedUnits, totalUnits);
                updateInfo();
                System.out.println("Import successful.");
            } catch (Exception c) {
                System.out.println("Unable to import values because of invalid inputs.");
            }
        });

        // Button to reset the transcript and start over.
        rstButton.setBounds(400, 420, 70, 30);
        rstButton.setFocusable(false);
        rstButton.setForeground(new Color(255, 20, 20));
        rstButton.addActionListener(e -> {
            clrButton.doClick();
            transcript.resetGrades();
            updateInfo();
            System.out.println("Your transcript has been cleared!");
        });

        this.add(addButton);
        this.add(printButton);
        this.add(clrButton);
        this.add(rstButton);
        this.add(importButton);
        this.add(readButton);
    }

    /**
     * A private function to update the gpa and units label.
     */
    private void updateInfo() {
        someLabels.get("gpa").setText("GPA: " + transcript.getGPA());
        someLabels.get("units").setText("Total Units: " + transcript.getCredits());
    }
}