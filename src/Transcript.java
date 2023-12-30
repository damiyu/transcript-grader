package src;
import java.io.*;
import java.util.*;

public class Transcript {
    private PriorityQueue<Course> transcript = new PriorityQueue<>((a, b) -> { return a.getName().compareTo(b.getName()); });
    private HashMap<String, Double> gradeVals = new HashMap<>();
    private HashSet<String> courseList = new HashSet<>();
    private double gradePnts, gradeUnits, totUnits;

    /**
     * This is the default contructor and it sets the default grade point scale and initializes all values to zero.
     */
    public Transcript() {
        gradeVals.put("A+", 4.0);
        gradeVals.put("A", 4.0);
        gradeVals.put("A-", 3.7);
        gradeVals.put("B+", 3.3);
        gradeVals.put("B", 3.0);
        gradeVals.put("B-", 2.7);
        gradeVals.put("C+", 2.3);
        gradeVals.put("C", 2.0);
        gradeVals.put("C-", 1.7);
        gradeVals.put("D", 1.0);
        gradeVals.put("F", 0.0);

        gradePnts = 0.0;
        gradeUnits = 0.0;
        totUnits = 0.0;
    }

    /**
     * This contructor applies a custom grade point scale and initializes all values to zero.
     * 
     * @param customGradeVals The custom grade point scale being used in this transcript
     */
    public Transcript(HashMap<String, Double> customGradeVals) {
        gradeVals.putAll(customGradeVals);

        gradePnts = 0.0;
        gradeUnits = 0.0;
        totUnits = 0.0;
    }

    /**
     * This constructor sets the default grade point scale and initializes the corresponding values.
     * 
     * @param gradePnts The points used to calculate gpa
     * @param gradeUnits The units used to calculate gpa
     * @param totUnits The total number of units
     */
    public Transcript(double gradePnts, double gradeUnits, double totUnits) {
        gradeVals.put("A+", 4.0);
        gradeVals.put("A", 4.0);
        gradeVals.put("A-", 3.7);
        gradeVals.put("B+", 3.3);
        gradeVals.put("B", 3.0);
        gradeVals.put("B-", 2.7);
        gradeVals.put("C+", 2.3);
        gradeVals.put("C", 2.0);
        gradeVals.put("C-", 1.7);
        gradeVals.put("D", 1.0);
        gradeVals.put("F", 0.0);

        this.gradePnts = gradePnts;
        this.gradeUnits = gradeUnits;
        this.totUnits = totUnits;
    }

    /**
     * This constructor applies a custom grade point scale and initializes the corresponding values.
     * 
     * @param customGradeVals The custom grade point scale being used in this transcript
     * @param gradePnts The points used to calculate gpa
     * @param gradeUnits The units used to calculate gpa
     * @param totUnits The total number of units
     */
    public Transcript(HashMap<String, Double> customGradeVals, double gradePnts, double gradeUnits, double totUnits) {
        gradeVals.putAll(customGradeVals);

        this.gradePnts = gradePnts;
        this.gradeUnits = gradeUnits;
        this.totUnits = totUnits;
    }

    /**
     * An import function to import only grade points, graded units, and total units.
     * 
     * @param newGradePnts Grade points imported to the transcript
     * @param newgradeUnits Graded units imported to the transcript
     * @param newUnits Total units imported to the transcript
     */
    public void importGrades(double newGradePnts, double newgradeUnits, double newUnits) {
        gradePnts += newGradePnts;
        gradeUnits += newgradeUnits;
        totUnits += newUnits;
    }

    /**
     * An import function to import a list of courses. This function will affect the
     * number of grade points, graded units, and total units.
     * 
     * @param newTranscript The list of courses being appended to the transcript
     */
    public void importGrades(List<Course> newTranscript) {
        for (Course c : newTranscript) {
            if (!courseList.contains(c.getName())) {
                addGrade(c.getName(), c.getGrade(), c.getUnits());
            } else {
                System.out.println("\"" + c.getName() + "\" is already in your transcript!");
            }
        }
    }

    /**
     * This function adds a course to the transcript. Repeat course titles are ignored
     * and returned with a warning message. Grades not specified in the grade scale are
     * accepted, but are treated as P/NP or S/US units.
     * 
     * @param courseName The name of the course being added to the transcript
     * @param letterGrade The grade associated with the course
     * @param units The number of units the course is worth
     */
    public void addGrade(String courseName, String letterGrade, Double units) {
        if (courseList.contains(courseName)) {
            System.out.println("\"" + courseName + "\" already exists in your transcript!");
            return;
        }

        transcript.add(new Course(courseName, letterGrade, units));
        courseList.add(courseName);
        gradePnts += gradeVals.containsKey(letterGrade) ? gradeVals.get(letterGrade) * units : 0;
        gradeUnits += gradeVals.containsKey(letterGrade) ? units : 0;
        totUnits += units;
    }

    /**
     * A print function used to print out information about the transcript. The printed
     * information depends on the specific input. This function can print just the GPA,
     * GPA + student level, GPA + student level + grade scale, or the whole transcript.
     * When the course list is empty, or if there are no graded units, the function gives
     * a warning message at that section of output.
     * 
     * @param printType Acceptable strings are "gpa", "lvl", "scale", or "all"
     */
    public void printGrades(String printType) {
        switch (printType) {
            case "all":
                System.out.println("\nYour Course Transcript:");

                // Get the transcript and print the courses in alphabetical order by course name.
                PriorityQueue<Course> sortedCourses = new PriorityQueue<>((a, b) -> { return a.getName().compareTo(b.getName()); });
                sortedCourses.addAll(transcript);
                while (!sortedCourses.isEmpty()) {
                    Course c = sortedCourses.poll();
                    System.out.println(c.getName() + ", Grade Received: " + c.getGrade() + ", Units: " + c.getUnits());
                }
                System.out.println();
            case "scale":
                System.out.println("\nGrade Scale:");

                // Retrieve the key-value pairs of the grade scale and store it in a 2D String array.
                String[][] preSort = new String[gradeVals.size()][2];
                int i = 0;
                for (String s : gradeVals.keySet()) {
                    preSort[i][0] = s;
                    preSort[i++][1] = Double.toString(gradeVals.get(s));
                }

                // Sort the grade scale through the point values in descending order.
                Arrays.sort(preSort, (a, b) -> {
                    // If point values are the same, sort by the reverse letter grade.
                    if (a[1].compareTo(b[1]) == 0) return -a[0].compareTo(b[0]);
                    return -a[1].compareTo(b[1]);
                });

                // Print the sorted grade scale (A+ : 4.0 \n A : 4.0 \n A- : 3.7 \n etc.)
                for (String[] s : preSort) System.out.println(s[0] + " : " + s[1]);
                System.out.println();
            case "lvl":
                int gradeLvl = (int) totUnits / 45;
                gradeLvl = gradeLvl > 3 ? 3 : gradeLvl;
                
                String[] level = {"Freshman", "Sophomore", "Junior", "Senior"};
                System.out.println("\nYour Grade Level: " + level[gradeLvl]);
            case "gpa":
                double GPA = 0.0;
                try {
                    GPA = gradePnts / gradeUnits;
                } catch (Exception e) {
                    System.out.println("Cannot divide by zero, unable to print GPA because you don't have any graded units!");
                    return;
                };

                GPA = Math.round(GPA * 1000) / 1000.0;
                System.out.println("\nYour GPA: " + GPA + "\nTotal Units: " + totUnits + "");
                break;
            default:
                System.out.println("\"" + printType + "\" is a invalid printing type, please try again!");
                break;
        }
    }


    /**
     * A function used to change the current grade scale of the transcript.
     * 
     * @param customGradeVals The new grade scale being used for the transcript
     */
    public void setGradeScale(HashMap<String, Double> customGradeVals) {
        gradeVals.clear();
        gradeVals.putAll(customGradeVals);
    }

    /**
     * A function to retrieve the course list of the transcript.
     * 
     * @return Course list of the transcript object
     */
    public HashSet<String> getCourseList() {
        return courseList;
    }

    /**
     * A get function that retrieves the GPA of the transcript.
     * 
     * @return The grade point average of the transcript
     */
    public Double getGPA() {
        double GPA = 0.0;
        try {
            GPA = gradePnts / gradeUnits;
        } catch (Exception e) {
            return 0.0;
        };

        GPA = Math.round(GPA * 1000) / 1000.0;
        return GPA;
    }

    /**
     * This get function is used to get the total units of the transcript.
     * 
     * @return Total units of the transcript
     */
    public Double getCredits() {
        return totUnits;
    }

    /**
     * A function to check whether or not a specific course title is already exists.
     * 
     * @param courseName The course name being checked
     * @return A boolean stating if the course is already in the transcript
     */
    public boolean contains(String courseName) {
        return courseList.contains(courseName);
    }

    /**
     * This function is used to clear the transcript and zero all points/units.
     * The only thing preserved is the grade scale.
     */
    public void resetGrades() {
        transcript.clear();
        courseList.clear();

        gradePnts = 0.0;
        totUnits = 0.0;
        gradeUnits = 0.0;
    }

    /**
     * This scanning function allows a transcript object to be modified through
     * the command line and/or through a text file. Invalid modifications would
     * be caught and responded to with an error message. The scanning function
     * only supports the following functions: import (values only), add, print,
     * reset. The 'read' command will scan another text file, and end this scan
     * instance before exiting. Input the command 'exit' to leave this function.
     * 
     * @param userInput A boolean to specify command line use or text file scan
     * @param file The path to the text file, not used if 'userInput' is true
     */
    public void gradeScan(Boolean userInput, String file) {
        Scanner cmdScan = new Scanner(System.in);

        try {
            if (!userInput) {
                cmdScan.close();
                File readFile = new File(file);
                cmdScan = new Scanner(readFile);
            }
        } catch(Exception e) {
            System.out.println("Invalid file path, please try again!");
            return;
        }

        if (userInput) System.out.println("\nAwaiting user input:");
        while (cmdScan.hasNextLine()) {
            String[] cmdArgs = cmdScan.nextLine().split(" ");
            int argCnt = cmdArgs.length;

            String cmd = cmdArgs[0].toLowerCase();
            if (cmd == "") continue;
            switch (cmd) {
                case "import":
                    if (argCnt == 4) {
                        try {
                            importGrades(Double.valueOf(cmdArgs[1]), Double.valueOf(cmdArgs[2]), Double.valueOf(cmdArgs[3]));
                        } catch (Exception e) {
                            System.out.println("Invalid import values, try again!");
                        }
                    }
                    break;
                case "add":
                    if (argCnt == 4) {
                        try {
                            addGrade(cmdArgs[1], cmdArgs[2], Double.valueOf(cmdArgs[3]));
                        } catch (Exception e) {
                            System.out.println("Invalid add values, try again!");
                        }
                    }
                    break;
                case "print":
                    if (argCnt == 2) {
                        printGrades(cmdArgs[1]);
                    }
                    break;
                case "reset":
                    if (argCnt == 1) {
                        resetGrades();
                    }
                    break;
                case "read":
                    if (argCnt == 2) {
                        gradeScan(false, cmdArgs[1]);
                    }
                    cmdScan.close();
                    return;
                case "exit":
                    cmdScan.close();
                    return;
                default:
                    System.out.println("\"" + String.join(" ", cmdArgs) + "\" is an invalid command, try again!");
                    break;
            }

            if (userInput) System.out.println("\nAwaiting user input:");
        }

        cmdScan.close();
    }
}
