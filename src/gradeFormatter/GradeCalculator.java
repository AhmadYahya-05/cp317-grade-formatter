package gradeFormatter;

/**
 * Interface for grade calculation functionality.
 * Demonstrates ABSTRACTION by defining a contract for grade calculation
 * without specifying the implementation details.
 */
public interface GradeCalculator {
    
    /**
     * Calculates the final grade based on individual test and exam scores.
     * 
     * @return the calculated final grade
     */
    double calculateFinalGrade();
    
    /**
     * Validates that a grade is within acceptable range.
     * 
     * @param grade the grade to validate
     * @return true if grade is valid, false otherwise
     */
    boolean isValidGrade(int grade);
} 
