/*
--------------------------------------------------
Project: CP317 Grade Formatter
File:    FileProcessor.java
Author:  Mehdi Al-heloo
Version: 2025-07-10
--------------------------------------------------
*/

package gradeFormatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Handles file input and constructs Student objects with course records.
 */
public class FileProcessor {

    /**
     * Loads student data from the given name and course files.
     *
     * @param nameFile   path to the file containing student IDs and names
     * @param courseFile path to the file containing student course records
     * @return a TreeMap where keys are student IDs and values are Student objects
     */
    public static Map<String, Student> loadStudentData(String nameFile, String courseFile) {
        if (nameFile == null || nameFile.isBlank()) {
            throw new IllegalArgumentException("nameFile path is null or empty");
        }
        if (courseFile == null || courseFile.isBlank()) {
            throw new IllegalArgumentException("courseFile path is null or empty");
        }
        Map<String, Student> studentMap = new TreeMap<>();

        //Step 1: Read name file and create Student objects
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String line;
            
         //Read each line from NameFile.txt
            while ((line = reader.readLine()) != null) {
                //Split line by comma and optional whitespace (e.g., "123, John Doe")
                String[] parts = line.split(",\\s*");

                //Expect exactly 2 parts: ID and Name
                assert parts.length == 2 : "Invalid format in name file: " + line;

                //Extract student ID and name
                String id = parts[0];
                String name = parts[1];

                //Create new Student and add to the map
                studentMap.put(id, new Student(id, name));
            }

        } catch (IOException e) {
            //Handle error if file can't be opened or read
            throw new RuntimeException("Failed to read name file: " + nameFile, e);
        }

        //Step 2: Read course file and add StudentCourse to corresponding students
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFile))) {
            String line;
            
         //Read each line from CourseFile.txt
            while ((line = reader.readLine()) != null) {
                //Each line should have 6 parts: ID, course code, 3 tests, and final exam
                String[] parts = line.split(",\\s*");

                assert parts.length == 6 : "Invalid format in course file: " + line;

                //Extract student ID and course code
                String id = parts[0];
                String courseCode = parts[1];
                //Parse grades from strings to integers
                int t1 = Integer.parseInt(parts[2]);
                int t2 = Integer.parseInt(parts[3]);
                int t3 = Integer.parseInt(parts[4]);
                int exam = Integer.parseInt(parts[5]);

                //Create a StudentCourse object with the course and grades
                StudentCourse course = new StudentCourse(courseCode, t1, t2, t3, exam);

                //Add course to the correct student (if student exists)
                Student student = studentMap.get(id);
                assert student != null : "Student ID not found in name file: " + id;
                   
                student.addCourse(course);
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Failed to read or parse course file: " + courseFile, e);
        }

        return studentMap;
    }
