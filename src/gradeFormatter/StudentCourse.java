/*
--------------------------------------------------
Project: CP317 Grade Formatter
File:    StudentCourse.java
Author:  Mehdi Al-heloo
Version: 2025-07-10
--------------------------------------------------
*/

package gradeFormatter;

/**
 * Represents a single course a student is enrolled in,
 * including grades for 3 tests and a final exam.
 */
public class StudentCourse {

	private String courseCode;
	private int test1, test2, test3, finalExam;

	/**
	 * Constructs a StudentCourse object with a course code and grades.
	 *
	 * @param courseCode the code of the course (e.g., CP317)
	 * @param test1 grade for Test 1
	 * @param test2 grade for Test 2
	 * @param test3 grade for Test 3
	 * @param finalExam grade for the Final Exam
	 */
	public StudentCourse(String courseCode, int test1, int test2, int test3, int finalExam) {
		if (courseCode == null || courseCode.isEmpty()) {
			throw new IllegalArgumentException("Course code cannot be null or empty.");
		}
		this.courseCode = courseCode;
		this.test1 = validateMark(test1);
		this.test2 = validateMark(test2);
		this.test3 = validateMark(test3);
		this.finalExam = validateMark(finalExam);
	}

	/**
	 * Validates that a mark is within 0 to 100.
	 *
	 * @param mark integer grade to check
	 * @return valid mark if in range
	 * @throws IllegalArgumentException if mark is out of range
	 */
	private int validateMark(int mark) {
		if (mark < 0 || mark > 100) {
			throw new IllegalArgumentException("Invalid grade: " + mark + ". Must be between 0 and 100.");
		}
		return mark;
	}

	/**
	 * Calculates the final grade using weighted average.
	 *
	 * @return final grade rounded to 1 decimal place
	 */
	public double calculateFinalGrade() {
		double finalGrade = 0.2 * test1 + 0.2 * test2 + 0.2 * test3 + 0.4 * finalExam;
		return Math.round(finalGrade * 10.0) / 10.0;
	}

	/** Returns the course code */
	public String getCourseCode() {
		return courseCode;
	}

	/** Returns the grade for Test 1 */
	public int getTest1() {
		return test1;
	}

	/** Returns the grade for Test 2 */
	public int getTest2() {
		return test2;
	}

	/** Returns the grade for Test 3 */
	public int getTest3() {
		return test3;
	}

	/** Returns the grade for the Final Exam */
	public int getFinalExam() {
		return finalExam;
	}
}
