package com.university.course;

import java.util.List;

public interface CourseService {
    /**
     * Adds a new course.
     *
     * @param courseName the name of the course to add.
     * @return true if the course was added successfully; false if it already exists.
     */
    boolean addCourse(String courseName);

    /**
     * Removes an existing course.
     *
     * @param courseName the name of the course to remove.
     * @return true if the course was removed; false if it did not exist.
     */
    boolean removeCourse(String courseName);

    /**
     * Enrolls a student in a course.
     *
     * @param studentID the student’s unique identifier.
     * @param courseName the course in which to enroll the student.
     * @return true if enrollment is successful; false otherwise.
     */
    boolean enrollStudent(String studentID, String courseName);

    /**
     * Removes a student from a course.
     *
     * @param studentID the student’s unique identifier.
     * @param courseName the course from which to remove the student.
     * @return true if the removal is successful; false otherwise.
     */
    boolean removeStudent(String studentID, String courseName);
    
    /**
     * Returns a list of courses in which the student is enrolled.
     */
    List<String> getEnrolledCourses(String studentID);
    
    /**
     * Returns the list of all available courses.
     */
    List<String> getAllCourses();
    
    /**
     * Updates a course by renaming it.
     *
     * @param oldCourseName the current name of the course.
     * @param newCourseName the new name for the course.
     * @return true if the update is successful; false otherwise.
     */
    boolean updateCourse(String oldCourseName, String newCourseName);
}
