package com.university.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseServiceImpl implements CourseService {

    // A map where key = courseName and value = a set of enrolled student IDs
    private Map<String, Set<String>> courses = new HashMap<>();

    @Override
    public boolean addCourse(String courseName) {
        if (courses.containsKey(courseName)) {
            System.out.println("Course " + courseName + " already exists.");
            return false;
        }
        courses.put(courseName, new HashSet<>());
        System.out.println("Course " + courseName + " added successfully.");
        return true;
    }

    @Override
    public boolean removeCourse(String courseName) {
        if (!courses.containsKey(courseName)) {
            System.out.println("Course " + courseName + " does not exist.");
            return false;
        }
        courses.remove(courseName);
        System.out.println("Course " + courseName + " removed successfully.");
        return true;
    }

    @Override
    public boolean enrollStudent(String studentID, String courseName) {
        if (!courses.containsKey(courseName)) {
            System.out.println("Course " + courseName + " does not exist. Please add the course first.");
            return false;
        }
        Set<String> enrolledStudents = courses.get(courseName);
        if (enrolledStudents.contains(studentID)) {
            System.out.println("Student " + studentID + " is already enrolled in " + courseName + ".");
            return false;
        }
        enrolledStudents.add(studentID);
        System.out.println("Student " + studentID + " enrolled in " + courseName + " successfully.");
        return true;
    }

    @Override
    public boolean removeStudent(String studentID, String courseName) {
        if (!courses.containsKey(courseName)) {
            System.out.println("Course " + courseName + " does not exist.");
            return false;
        }
        Set<String> enrolledStudents = courses.get(courseName);
        if (!enrolledStudents.contains(studentID)) {
            System.out.println("Student " + studentID + " is not enrolled in " + courseName + ".");
            return false;
        }
        enrolledStudents.remove(studentID);
        System.out.println("Student " + studentID + " removed from " + courseName + " successfully.");
        return true;
    }

    @Override
    public List<String> getEnrolledCourses(String studentID) {
        List<String> enrolledCourses = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : courses.entrySet()) {
            if (entry.getValue().contains(studentID)) {
                enrolledCourses.add(entry.getKey());
            }
        }
        return enrolledCourses;
    }
    
    @Override
    public List<String> getAllCourses() {
        return new ArrayList<>(courses.keySet());
    }
    
    @Override
    public boolean updateCourse(String oldCourseName, String newCourseName) {
        if (!courses.containsKey(oldCourseName)) {
            System.out.println("Course " + oldCourseName + " does not exist.");
            return false;
        }
        if (courses.containsKey(newCourseName)) {
            System.out.println("Course " + newCourseName + " already exists.");
            return false;
        }
        Set<String> enrolledStudents = courses.remove(oldCourseName);
        courses.put(newCourseName, enrolledStudents);
        System.out.println("Course updated from " + oldCourseName + " to " + newCourseName + " successfully.");
        return true;
    }
}
