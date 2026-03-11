/*
    Name: Joshua Calderon
    Date: 03/10/2026
    Program: Course Grades Analyzer

*/


import java.utils.ArrayList

public class course{
    private String courseName;
    private ArrayList<Integer> courseGrades;

    // constructor
    public Course()
    this.courseName = " ";
    this.courseGrades = " ";

    // constructor
    public Course(String courseName, ArrayList<Integer> courseGrades){
        this.courseName = courseName;
        this.courseGrades = courseGrades;
    }

    // courseName getter
    public String getCourseName() {
        return courseName;
    }

    // courseName setter
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // grades getter
    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }

    // setter for grades
    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        this.courseGrades = courseGrades;
    }

    // return grade total integer
    public int getTotalGrades(){
        int total = 0;
        for (int value : courseGrades) {
            total += value;
        }
        return total;
    }

    // return double percent of grades
    public double getAPercent(){
        int total = getTotalGrades();
        if (total ==0 ) {
            return 0.0;
        }
        return (double) courseGrades.get(0) / total * 100.0;
    }

    // toString method
    public String toString() {
        return courseName + " " + courseGrades.toString();
    }
}