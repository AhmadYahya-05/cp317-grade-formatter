
package gradeFormatter;

import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        // Initialize the application
        System.out.println("Welcome to the Student Course Management System!");
        String nameFile = "../data/NameFile.txt";
        String courseFile = "../data/CourseFile.txt";
        String outputFile = "OutputFile.txt";

        // Load student and course data into a sorted map
        Map<String, Student> studentMap = FileProcessor.loadStudentData(nameFile, courseFile);

        // Generate and write the output file
        OutputProcessor.writeOutput(studentMap, outputFile);

        System.out.println("Processing complete! Output file updated: " + outputFile);
    }
}
