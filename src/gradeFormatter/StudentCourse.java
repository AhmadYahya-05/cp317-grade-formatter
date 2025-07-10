/*
--------------------------------------------------
Project: CP317 Grade Formatter
File:    StudentCourse.java
Author:  Mehdi Al-heloo
Version: 2025-07-07
--------------------------------------------------
*/

package gradeFormatter;

/**
 * Represents a single course taken by a student, including grades.
 */
public class StudentCourse {

    private String courseCode;
    private int test1, test2, test3, finalExam;

    /**
     * Constructs a StudentCourse with the course code and all four marks.
     *
     * @param courseCode the course code (e.g., CP317)
     * @param test1 the mark for test 1
     * @param test2 the mark for test 2
     * @param test3 the mark for test 3
     * @param finalExam the mark for the final exam
     */
    public StudentCourse(String courseCode, int test1, int test2, int test3, int finalExam) {
        this.courseCode = courseCode;
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.finalExam = finalExam;
    }

    /**
     * Calculates the final grade using weights:
     * 20% for each test, 40% for the final exam.
     *
     * @return the final grade rounded to one decimal place
     */
    public double calculateFinalGrade() {
        double finalGrade = 0.2 * test1 + 0.2 * test2 + 0.2 * test3 + 0.4 * finalExam;
        return Math.round(finalGrade * 10.0) / 10.0;
    }

    /**
     * Gets the course code.
     *
     * @return the course code as a String
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Returns the mark for test 1.
     *
     * @return an integer mark
     */
    public int getTest1() {
        return test1;
    }

    /**
     * Returns the mark for test 2.
     *
     * @return an integer mark
     */
    public int getTest2() {
        return test2;
    }

    /**
     * Returns the mark for test 3.
     *
     * @return an integer mark
     */
    public int getTest3() {
        return test3;
    }

    /**
     * Returns the mark for the final exam.
     *
     * @return an integer mark
     */
    public int getFinalExam() {
        return finalExam;
    }
}
