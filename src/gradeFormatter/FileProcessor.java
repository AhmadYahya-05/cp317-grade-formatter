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
	 * Loads student data from name and course files.
	 *
	 * @param nameFile path to file with student IDs and names
	 * @param courseFile path to file with student course records
	 * @return map of student ID to Student object
	 */
	public static Map<String, Student> loadStudentData(String nameFile, String courseFile) {
		Map<String, Student> studentMap = new TreeMap<>();

		// Step 1: Read name file
		try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
			String line;
			int lineNum = 0;

			while ((line = reader.readLine()) != null) {
				lineNum++;
				String[] parts = line.split(",\\s*");

				if (parts.length != 2) {
					System.out.println("Invalid line in name file (line " + lineNum + "): " + line);
					continue;
				}

				String id = parts[0];
				String name = parts[1];

				if (studentMap.containsKey(id)) {
					System.out.println("Warning: duplicate student ID in name file → " + id);
				} else {
					try {
						studentMap.put(id, new Student(id, name));
					} catch (IllegalArgumentException e) {
						System.out.println("Error creating student from line " + lineNum + ": " + e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading name file: " + e.getMessage());
		}

		// Step 2: Read course file
		try (BufferedReader reader = new BufferedReader(new FileReader(courseFile))) {
			String line;
			int lineNum = 0;

			while ((line = reader.readLine()) != null) {
				lineNum++;
				String[] parts = line.split(",\\s*");

				if (parts.length != 6) {
					System.out.println("Invalid line in course file (line " + lineNum + "): " + line);
					continue;
				}

				String id = parts[0];
				String courseCode = parts[1];

				try {
					int t1 = Integer.parseInt(parts[2]);
					int t2 = Integer.parseInt(parts[3]);
					int t3 = Integer.parseInt(parts[4]);
					int exam = Integer.parseInt(parts[5]);

					StudentCourse course = new StudentCourse(courseCode, t1, t2, t3, exam);

					if (studentMap.containsKey(id)) {
						studentMap.get(id).addCourse(course);
					} else {
						System.out.println("Warning: student ID in course file not found in name file → " + id);
					}
				} catch (NumberFormatException e) {
					System.out.println("Error parsing numbers on line " + lineNum + ": " + line);
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid grade data on line " + lineNum + ": " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading course file: " + e.getMessage());
		}

		return studentMap;
	}
}
