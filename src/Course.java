package src;

public class Course {
    private String courseName, courseGrade;
    private Double courseUnits;

    Course(String courseName, String courseGrade, Double courseUnits) {
        this.courseName = courseName;
        this.courseGrade = courseGrade;
        this.courseUnits = courseUnits;
    }

    public String getName() {
        return courseName;
    }

    public String getGrade() {
        return courseGrade;
    }

    public Double getUnits() {
        return courseUnits;
    }
}
