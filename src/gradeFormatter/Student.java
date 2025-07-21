package gradeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student and the courses they are enrolled in.
 * Stores the student ID, name, and a list of StudentCourse objects.
 */
public class Student {

	private String studentID;
	private String name;
	private List<StudentCourse> courses;

	/**
	 * Constructs a Student object with the given ID and name.
	 * Initializes an empty list of courses.
	 *
	 * @param studentID the student ID
	 * @param name the full name of the student
	 */
	public Student(String studentID, String name) {
		if (studentID == null || studentID.isEmpty() || name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Student ID and name cannot be null or empty.");
		}
		this.studentID = studentID;
		this.name = name;
		this.courses = new ArrayList<>();
	}

	/**
	 * Adds a course to the student's list of courses.
	 *
	 * @param course a valid StudentCourse object
	 */
	public void addCourse(StudentCourse course) {
		if (course != null) {
			courses.add(course);
		} else {
			System.out.println("Warning: attempted to add a null course to student " + studentID);
		}
	}

	/**
	 * Returns the student ID.
	 *
	 * @return student ID as a string
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * Returns the student's full name.
	 *
	 * @return name of the student
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the list of courses the student is enrolled in.
	 *
	 * @return list of StudentCourse objects
	 */
	public List<StudentCourse> getCourses() {
		return courses;
	}
}
