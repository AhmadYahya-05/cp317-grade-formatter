/*
--------------------------------------------------
Project: CP317 Grade Formatter
File:    Student.java
Author:  Mehdi Al-heloo
Version: 2025-07-07
--------------------------------------------------
*/

package gradeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student with an ID, name, and a list of course records.
 */
public class Student {

    private String studentID;
    private String name;
    private List<StudentCourse> courses;

    /**
     * Constructs a Student object with a student ID and name.
     *
     * @param studentID the student's ID
     * @param name the student's full name
     */
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    /**
     * Adds a course record to this student's list of courses.
     *
     * @param course the course to be added
     */
    public void addCourse(StudentCourse course) {
        courses.add(course);
    }

    /**
     * Returns the student's ID.
     *
     * @return the student ID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Returns the student's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of course records for this student.
     *
     * @return a list of StudentCourse objects
     */
    public List<StudentCourse> getCourses() {
        return courses;
    }
}
