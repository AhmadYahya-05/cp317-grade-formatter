/*
--------------------------------------------------
Project: CP317 Grade Formatter
File:    FileProcessor.java
Author:  Mehdi Al-heloo
Version: 2025-07-07
--------------------------------------------------
*/

package gradeFormatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Handles reading and parsing input files:
 * - NameFile.txt: Contains student IDs and names
 * - CourseFile.txt: Contains student IDs, course codes, and marks
 * 
 * Builds a map of Student objects keyed by student ID.
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
        Map<String, Student> studentMap = new TreeMap<>();

        //Step 1: Read name file and create Student objects
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String line;
            
         //Read each line from NameFile.txt
            while ((line = reader.readLine()) != null) {
                //Split line by comma and optional whitespace (e.g., "123, John Doe")
                String[] parts = line.split(",\\s*");

                //Expect exactly 2 parts: ID and Name
                if (parts.length != 2) {
                    System.out.println("Invalid line in name file: " + line);
                    continue;
                }

                //Extract student ID and name
                String id = parts[0];
                String name = parts[1];

                //Create new Student and add to the map
                studentMap.put(id, new Student(id, name));
            }

        } catch (IOException e) {
            //Handle error if file can't be opened or read
            System.out.println("Error reading name file: " + e.getMessage());
        }

        //Step 2: Read course file and add StudentCourse to corresponding students
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFile))) {
            String line;
            
         //Read each line from CourseFile.txt
            while ((line = reader.readLine()) != null) {
                //Each line should have 6 parts: ID, course code, 3 tests, and final exam
                String[] parts = line.split(",\\s*");

                if (parts.length != 6) {
                    System.out.println("Invalid line in course file: " + line);
                    continue;
                }

                //Extract student ID and course code
                String id = parts[0];
                String courseCode = parts[1];

                try {
                    //Parse grades from strings to integers
                    int t1 = Integer.parseInt(parts[2]);
                    int t2 = Integer.parseInt(parts[3]);
                    int t3 = Integer.parseInt(parts[4]);
                    int exam = Integer.parseInt(parts[5]);

                    //Create a StudentCourse object with the course and grades
                    StudentCourse course = new StudentCourse(courseCode, t1, t2, t3, exam);

                    //Add course to the correct student (if student exists)
                    if (studentMap.containsKey(id)) {
                        studentMap.get(id).addCourse(course);
                    } else {
                        //If student ID wasn’t in NameFile.txt, log a warning
                        System.out.println("Warning: student ID not found → " + id);
                    }

                } catch (NumberFormatException e) {
                    //Handle the case where test/exam marks are not valid integers
                    System.out.println("Error parsing numbers in course file: " + line);
                }
            }

        } catch (IOException e) {
            //Handle error if course file can’t be opened or read
            System.out.println("Error reading course file: " + e.getMessage());
        }

        //Return the complete map of students and their course records
        return studentMap;
    }
}
