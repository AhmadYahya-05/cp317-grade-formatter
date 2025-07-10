/*
--------------------------------------------------
Project: CP317 Grade Formatter
File:    OutputProcessor.java
Author:  Manraj Kalra
Version: 2025-07-10
--------------------------------------------------
*/

package gradeFormatter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Handles generating and writing the output file containing:
 * Student ID, Student Name, Course Code, Final Grade
 * sorted by Student ID.
 */
public class OutputProcessor {

    /**
     * Writes the formatted student course records with calculated final grades
     * to the specified output file.
     *
     * @param studentMap a TreeMap of StudentID to Student objects
     * @param outputFile the name of the output file to generate
     */
    public static void writeOutput(Map<String, Student> studentMap, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            
            //Iterate over each student (automatically sorted by TreeMap)
            for (Student student : studentMap.values()) {
                //For each course the student is enrolled in
                for (StudentCourse course : student.getCourses()) {
                    double finalGrade = course.calculateFinalGrade();

                    //Format output line
                    String line = String.format("%s, %s, %s, %.1f",
                            student.getStudentID(),
                            student.getName(),
                            course.getCourseCode(),
                            finalGrade);

                    //Write line to file
                    writer.write(line);
                    writer.newLine();
                }
            }

            System.out.println("Output file generated: " + outputFile);

        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
        }
    }
}
