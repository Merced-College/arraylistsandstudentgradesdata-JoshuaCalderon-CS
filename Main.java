/*
    Name: Joshua Calderon
    Date: 03/10/2026
    Program: Course Grades Analyzer

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<course> courses = new ArrayList<>();

        try {
            File file = new File("courseAndGradesData.csv");
            Scanner input = new Scanner(file);

            // Skip the two header lines in this CSV export.
            if (input.hasNextLine()) {
                input.nextLine();
            }
            if (input.hasNextLine()) {
                input.nextLine();
            }

            while (input.hasNextLine()) {
                
                String line = input.nextLine();
                String[] parts = line.split(",");

                if (parts.length < 6) {
                    continue;
                }

                String courseName = parts[0];
                ArrayList<Integer> grades = new ArrayList<>();
                boolean validRow = true;

                for (int i = 1; i < parts.length; i++) {
                    try {
                        int count = Integer.parseInt(parts[i].trim());
                        grades.add(count);
                    } catch (NumberFormatException e) {
                        validRow = false;
                        break;
                    }
                }

                if (!validRow || grades.size() < 5) {
                    continue;
                }

                course courseData = new course(courseName, grades);
                courses.add(courseData);
            }
            input.close();
        } catch ( FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        }

        if (courses.isEmpty()) {
            System.out.println("No course data found.");
            return;
        }

        //print table header
        System.out.printf("%-10s %5s %5s %5s %5s %5s %8s %8s\n",
                         "Course", "A", "B", "C", "D", "F", "Total", "A%");

        for (course c : courses) {

            ArrayList<Integer> g = c.getCourseGrades();

            System.out.printf("%-10s %5d %5d %5d %5d %5d %8d %8.2f\n", 
            c.getCourseName(),
            g.get(0),
            g.get(1), 
            g.get(2), 
            g.get(3), 
            g.get(4), 
            c.getTotalGrades(), 
            c.getAPercent());

        }

        //find highest A%
        course best = courses.get(0);

        for (course c : courses) {
            if (c.getAPercent() > best.getAPercent()) {
                best = c;
            }
        }

        System.out.println("\nCourse with highest A%: ");
        System.out.println(best.getCourseName() + " with A%: " + best.getAPercent());
        System.out.printf("A%%: %.2f Total: %d\n", 
                            best.getAPercent(), 
                            best.getTotalGrades());

        // Linear search
        Scanner console = new Scanner(System.in);
        System.out.print("\nEnter course name to search: ");
        String search = console.nextLine();
        boolean found = false;

        for (course c : courses) {
            if (c.getCourseName().equalsIgnoreCase(search)) {
                System.out.println("Course found:");
                System.out.println("Course: " + c.getCourseName());
                System.out.println("Total grades: " + c.getTotalGrades());
                System.out.printf("A%%: %.2f\n", c.getAPercent());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
        }

        console.close();
        
    }   
}