package src;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ToolFrame extends JFrame {
    public static void main(String[] args) {
        Transcript transcript = new Transcript();
        ToolFrame tools = new ToolFrame(transcript);
        tools.launchFrame();
    }
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

    public void launchFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void buildLabels() {
        JLabel newCourseLabel = new JLabel("Add New Courses");
        JLabel courseNameLabel = new JLabel("Course Name:");
        JLabel courseGradeLabel = new JLabel("Course Grade:");
        JLabel courseCreditsLabel = new JLabel("Course Units:");
        JLabel printTranscriptLabel = new JLabel("Print Transcript");
        JLabel printTypeLabel = new JLabel("Print Type:");
        JLabel readFileLabel = new JLabel("Read File");
        JLabel filePathLabel = new JLabel("File Path:");
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

        readFileLabel.setBounds(20, 220, 180, 30);
        readFileLabel.setFont(titleFont);
        readFileLabel.setForeground(titleColor);
        filePathLabel.setBounds(20, 250, 80, 25);
        filePathLabel.setFont(itemFont);

        gpaLabel.setBounds(20, 370, 80, 20);
        gpaLabel.setFont(displayFont);
        gpaLabel.setForeground(displayColor);
        unitsLabel.setBounds(20, 400, 100, 20);
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
        this.add(gpaLabel);
        this.add(unitsLabel);
    }

    private void buildInteracticeFields() {
        JTextField courseNameInput = new JTextField();
        JTextField courseGradeInput = new JTextField();
        JTextField courseCreditsInput = new JTextField();
        JTextField filePathInput = new JTextField();
        JComboBox<String> printTypeDropDown = new JComboBox<>();

        courseNameInput.setBounds(130, 50, 80, 25);
        courseGradeInput.setBounds(130, 85, 30, 25);
        courseCreditsInput.setBounds(130, 120, 30, 25);
        allTextFields.put("name", courseNameInput);
        allTextFields.put("grade", courseGradeInput);
        allTextFields.put("units", courseCreditsInput);

        filePathInput.setBounds(100, 250, 200, 25);
        allTextFields.put("file", filePathInput);

        String[] printTypes = {"gpa", "grade level", "grade scale", "transcript"};
        for (String s : printTypes) printTypeDropDown.addItem(s);
        printTypeDropDown.setBounds(370, 50, 100, 25);
        allDropDowns.put("print", printTypeDropDown);

        this.add(courseNameInput);
        this.add(courseGradeInput);
        this.add(courseCreditsInput);
        this.add(filePathInput);
        this.add(printTypeDropDown);
    }

    private void buildButtons() {
        JButton addButton = new JButton("Add");
        JButton printButton = new JButton("Print");
        JButton clrButton = new JButton("Clear CMD");
        JButton readButton = new JButton("Read");
        JButton rstButton = new JButton("Reset");

        addButton.setBounds(20, 150, 60, 30);
        addButton.setFocusable(false);
        addButton.addActionListener(e -> {
            try {
                String course = allTextFields.get("name").getText();
                String grade = allTextFields.get("grade").getText();
                Double units = Double.valueOf(allTextFields.get("units").getText());

                if (!transcript.contains(course)) System.out.println(course + " was successfully added.");
                transcript.addGrade(course, grade, units);
                updateInfo();
            } catch (Exception c) {
                System.out.println("Unable to add this course because of invalid inputs.");
            }
        });

        printButton.setBounds(280, 80, 70, 30);
        printButton.setFocusable(false);
        printButton.addActionListener(e -> {
            int printType = allDropDowns.get("print").getSelectedIndex();

            if (printType == 0) transcript.printGrades("gpa");
            else if (printType == 1) transcript.printGrades("lvl");
            else if (printType == 2) transcript.printGrades("scale");
            else transcript.printGrades("all");
        });

        clrButton.setBounds(370, 80, 100, 30);
        clrButton.setFocusable(false);
        clrButton.setForeground(new Color(255, 20, 20));
        clrButton.addActionListener(e -> {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        });

        readButton.setBounds(20, 285, 80, 30);
        readButton.setFocusable(false);
        readButton.addActionListener(e -> {
            String filePath = allTextFields.get("file").getText();

            int cnt = transcript.getCourseList().size();
            transcript.gradeScan(false, filePath);
            updateInfo();

            // A rigid count can be an indicator of an invalid file path.
            if (cnt == transcript.getCourseList().size()) {
                System.out.println("For reference, you're currently in " + "\"" + System.getProperty("user.dir") + "\"");
            }
        });

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
        this.add(readButton);
    }

    private void updateInfo() {
        someLabels.get("gpa").setText("GPA: " + transcript.getGPA());
        someLabels.get("units").setText("Total Units: " + transcript.getCredits());
    }
}